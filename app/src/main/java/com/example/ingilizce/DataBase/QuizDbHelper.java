package com.example.ingilizce.DataBase;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.ingilizce.Quiz.QuizResult;
import java.util.ArrayList;


public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "English";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        Log.d("dbdb", "quiz  db helper");
        final String SQL_CREATE_QUESTIONS_TABLE = " CREATE TABLE " +
                Contracts.Quizzes.TABLE_NAME + " ( " +
                Contracts.Quizzes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contracts.Quizzes.COLUMN_CORRECT + " VARCHAR, " +
                Contracts.Quizzes.COLUMN_WRONG + " VARCHAR, " +
                Contracts.Quizzes.COLUMN_DATE + " TEXT " +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contracts.Quizzes.TABLE_NAME);
        onCreate(db);
    }
/*
    public void insertQuiz(QuizResult quizResult) {
        ContentValues cv = new ContentValues();
        cv.put(Contracts.Quizzes.COLUMN_CORRECT, quizResult.getCorrectCount());
        cv.put(Contracts.Quizzes.COLUMN_WRONG, quizResult.getWrongCount());
        cv.put(Contracts.Quizzes.COLUMN_DATE, getNow());
        db = getWritableDatabase();
        db.insert(Contracts.Quizzes.TABLE_NAME, null, cv);
        db.close();
    }*/

    public ArrayList<QuizResult> getQuizArrayList() {
        db = getReadableDatabase();
        ArrayList<QuizResult> quizArrayList = new ArrayList<>();

        String[] Projection = {
                Contracts.Quizzes.COLUMN_ID,
                Contracts.Quizzes.COLUMN_CORRECT,
                Contracts.Quizzes.COLUMN_WRONG,
                Contracts.Quizzes.COLUMN_DATE
        };

        Cursor c = db.query(Contracts.Quizzes.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);

        QuizResult quizResult;

        if (c.moveToFirst()) {
            do {
                quizResult = new QuizResult();

                quizResult.setId(c.getInt(c.getColumnIndex(Contracts.Quizzes.COLUMN_ID)));
                quizResult.setCorrectCount(c.getInt(c.getColumnIndex(Contracts.Quizzes.COLUMN_CORRECT)));
                quizResult.setWrongCount(c.getInt(c.getColumnIndex(Contracts.Quizzes.COLUMN_WRONG)));
                quizResult.setDate(c.getString(c.getColumnIndex(Contracts.Quizzes.COLUMN_DATE)));

                quizArrayList.add(quizResult);
            } while (c.moveToNext());

        }

        c.close();
        db.close();

        return quizArrayList;
    }
/*
    private String getNow() {
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyLocalizedPattern(" d MMM yyyy HH:mm:ss");
        return simpleDateFormat.format(date);
    }*/
}