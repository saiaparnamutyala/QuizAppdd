package com.example.finalproject;

public class QuestionsModal {

    // variables for our question,
    // option1, option2, option3, option4, answer, id.
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String answer;
    private int id;

    // creating getter and setter methods
    public String getquestion() {
        return question;
    }

    public void setquestion(String question) {
        this.question = question;
    }

    public String getoption1() {
        return option1;
    }

    public void setoption1(String option1) {
        this.option1 = option1;
    }

    public String getoption2() {
        return option2;
    }

    public void setoption2(String option2) {
        this.option2 = option2;
    }

    public String getoption3() {
        return option3;
    }

    public void setoption3(String option3) {
        this.option3 = option3;
    }

    public String getanswer() {
        return answer;
    }

    public void setanswer() {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public QuestionsModal(String question, String option1, String option2, String option3, String answer) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answer = answer;
    }
}



