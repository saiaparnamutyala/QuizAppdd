package com.example.finalproject;

import java.util.ArrayList;
import java.util.HashMap;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuestionAnswer{

    public static String question[]={
        "Which company owns the world",
            "Which one is not the prograaming language",
            "Which is the best socail media?"
    };

    public static String choices[][]= {
            {"Google", "Apple", "Nokia"},
            {"Java", "Kotlin", "Notepad"},
            {"Facebook", "Instagram", "Youtube"}
    };

    public static String correctAnswer[] = {
        "Google",
        "Notepad",
        "Youtube"
    };
}
