package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "quizapp";

    // below int is our database version
    private static final int DB_VERSION = 1;

    //Quiz Table
    // below variable is for our table name.
    private static final String TABLE_NAME = "quiztab";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    private static final String QUESTION_COL = "question";

    private static final String OPTION1_COL = "option1";

    private static final String OPTION2_COL = "option2";

    private static final String OPTION3_COL = "option3";

    private static final String ANSWER_COL = "answer";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QUESTION_COL + " TEXT,"
                + OPTION1_COL + " TEXT,"
                + OPTION2_COL + " TEXT,"
                + OPTION3_COL + " TEXT,"
                + ANSWER_COL  + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);

        //Insert Initial Data
        this.initApp(db);

    }

    // this method is use to add new question to our sqlite database.
    public void addQuestion(String question, String option1, String option2, String option3, String answer) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(QUESTION_COL, question);
        values.put(OPTION1_COL, option1);
        values.put(OPTION2_COL, option2);
        values.put(OPTION3_COL, option3);
        values.put(ANSWER_COL, answer);


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();


    }


    //Initialize app data
    // Deletes all questions in data base and insert below 10 questions
    public void initApp(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        //Insert all questions to DB
        db.execSQL("INSERT INTO quiztab ('question', 'option1', 'option2', 'option3', 'answer')  VALUES" +
                "('Who is Canadas Head Of State?', 'The Prime Minister','The Governor General', 'The Queen', '3')," +
                "('Which topping is Canada well-known for?','Mustard','Maple Syrup', 'Sweet and Sour Sauce', '2')," +
                "('How many official languages does Canada have?','Ten','Six','Two', '3')," +
                "('What animal is on the Canadian nickel?','Beaver','Moose', 'Seal', '1'), " +
                "('Which province is home to the most people who have French as a first language?', 'Quebec', 'Nunavut', 'The Northwest Territories', '1'), " +
                "('What sport was invented in Canada?','Hockey', 'Football', 'Basketball', '3')," +
                "('Who represents the Queen in Canada?', 'The Governor General', 'The Prime Minister', 'The Minister of Immigration', '1')," +
                "('How many times has Canada hosted the Olympics?', 'Ten', 'Thirty', 'Three',  '3')," +
                "('Who discovered Canada?', 'Emily Carr', 'Nellie McClung', 'Jacques Cartier',  '3')," +
                "('What sport is Canada well-known for?', 'Hockey', 'Football', 'Tennis',  '1')");

    }

    // we have created a new method for reading all the questions.
    public ArrayList<QuestionsModal> readQuestions() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorQuestions = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 5 ", null);

        // on below line we are creating a new array list.
        ArrayList<QuestionsModal> questionsModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorQuestions.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                questionsModalArrayList.add(new QuestionsModal(cursorQuestions.getString(1),
                        cursorQuestions.getString(2),
                        cursorQuestions.getString(3),
                        cursorQuestions.getString(4),
                        cursorQuestions.getString(5)));
            } while (cursorQuestions.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorQuestions.close();
        return questionsModalArrayList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}

