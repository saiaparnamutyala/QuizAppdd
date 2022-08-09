package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DBHandler dbHandler;
    private ArrayList<QuestionsModal> questionsList;

    TextView totalQuestionsTextView;
    TextView scoreView;
    TextView questionView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestions;
    int currentQuestion = 0;
    String selectAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(MainActivity.this);
        questionsList = new ArrayList<>();

        // Read 5 questions from DB randomly
        questionsList = dbHandler.readQuestions();

        totalQuestions = questionsList.size();

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#378DF2"));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        totalQuestionsTextView = findViewById(R.id.totalQuestion);
        questionView = findViewById(R.id.question);
        scoreView = findViewById(R.id.Score);
        ansA = findViewById(R.id.answer1);
        ansB = findViewById(R.id.answer2);
        ansC = findViewById(R.id.answer3);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);

        totalQuestionsTextView.setText("Question " + currentQuestion + " of " + totalQuestions);
        loadNewQuestion(questionsList);
    }


    @Override
    public void onClick(View view) {


        Button clickedButton = (Button) view;

        if(clickedButton.getId() == R.id.answer1) {
            if(questionsList.get(currentQuestion).getanswer().equals("1")) {
                ansA.setBackgroundColor(Color.GREEN);
                score++;
                currentQuestion++;
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNewQuestion(questionsList);
                    }
                }, 300);

            }else {
                ansA.setBackgroundColor(Color.RED);
                final Handler handler = new Handler(Looper.getMainLooper());
                currentQuestion++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNewQuestion(questionsList);
                    }
                }, 200);
            }

        } else if(clickedButton.getId() == R.id.answer2){
            if(questionsList.get(currentQuestion).getanswer().equals("2")) {
                ansB.setBackgroundColor(Color.GREEN);
                score++;
                currentQuestion++;
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNewQuestion(questionsList);
                    }
                }, 200);

            }else {
                ansB.setBackgroundColor(Color.RED);
                final Handler handler = new Handler(Looper.getMainLooper());
                currentQuestion++;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNewQuestion(questionsList);
                    }
                }, 200);
            }
        }else if(clickedButton.getId() == R.id.answer3) {
            if(questionsList.get(currentQuestion).getanswer().equals("3")) {
                ansC.setBackgroundColor(Color.GREEN);
                score++;
                currentQuestion++;
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNewQuestion(questionsList);
                    }
                }, 200);

            }else {
                ansC.setBackgroundColor(Color.RED);
                currentQuestion++;
                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNewQuestion(questionsList);
                    }
                }, 200);
            }
        } else {
            return;
        }

    }

    void loadNewQuestion(ArrayList<QuestionsModal> questionsList) {
        if((currentQuestion) == totalQuestions) {
            finishQuiz();
        } else {
            totalQuestionsTextView.setText("Question " + (currentQuestion+1) + " of " + totalQuestions);
            ansA.setBackgroundColor(Color.WHITE);
            ansB.setBackgroundColor(Color.WHITE);
            ansC.setBackgroundColor(Color.WHITE);
            scoreView.setText("Score: " + score);
            questionView.setText(questionsList.get(currentQuestion).getquestion());
            ansA.setText(questionsList.get(currentQuestion).getoption1());
            ansB.setText(questionsList.get(currentQuestion).getoption2());
            ansC.setText(questionsList.get(currentQuestion).getoption3());
        }


//        ansA.setText(QuestionAnswer.choices[currentQuestion][3]);
    }

    void finishQuiz() {
        String passStatus = "";
        if(score < 3) {
            passStatus = "Please try again!";
        } else if(score == 3) {
            passStatus = "Good job!";
        }
          else if(score == 4) {
              passStatus = "Excellent work!";
        }
          else if (score == 5) {
              passStatus = "You are a Genius!";
        }


        new AlertDialog.Builder(this)
            .setTitle(passStatus)
                .setMessage("Your score is" + score + " out of " + totalQuestions)
                .setPositiveButton("Restart", ((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestion = 0;

        questionsList.clear();

        dbHandler = new DBHandler(MainActivity.this);
        questionsList = new ArrayList<>();

        // Read 5 questions from DB randomly
        questionsList = dbHandler.readQuestions();

        totalQuestions = questionsList.size();

        loadNewQuestion(questionsList);
    }
}