package com.example.ingilizce.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.ingilizce.Word.Word;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WordsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Ingilizce";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public WordsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        Log.d("dbdb", "word  db helper");
        final String SQL_CREATE_QUESTIONS_TABLE = " CREATE TABLE " +
                Contracts.WordsTable.TABLE_NAME + " ( " +
                Contracts.WordsTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contracts.WordsTable.COLUMN_WORDEN + " VARCHAR, " +
                Contracts.WordsTable.COLUMN_WORDTR + " VARCHAR, " +
                Contracts.WordsTable.COLUMN_SENTENCEEN + " VARCHAR, " +
                Contracts.WordsTable.COLUMN_SENTENCETR + " VARCHAR, " +
                Contracts.WordsTable.COLUMN_DATE + " TEXT, " +
                Contracts.WordsTable.COLUMN_IMAGE + " BLOB " +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contracts.WordsTable.TABLE_NAME);
        onCreate(db);
    }

    public void insertWord(Word word) {
        ContentValues cv = new ContentValues();
        cv.put(Contracts.WordsTable.COLUMN_WORDEN, word.getWordEn());
        cv.put(Contracts.WordsTable.COLUMN_WORDTR, word.getWordTr());
        cv.put(Contracts.WordsTable.COLUMN_SENTENCEEN, word.getSentenceEn());
        cv.put(Contracts.WordsTable.COLUMN_SENTENCETR, word.getSentenceTr());
        cv.put(Contracts.WordsTable.COLUMN_DATE, getNow());
        cv.put(Contracts.WordsTable.COLUMN_IMAGE, word.getBytes());

        db = getWritableDatabase();
        db.insert(Contracts.WordsTable.TABLE_NAME, null, cv);
        db.close();
    }

    public void updateWord(Word word) {
        ContentValues cv = new ContentValues();
        cv.put(Contracts.WordsTable.COLUMN_WORDEN, word.getWordEn());
        cv.put(Contracts.WordsTable.COLUMN_WORDTR, word.getWordTr());
        cv.put(Contracts.WordsTable.COLUMN_SENTENCEEN, word.getSentenceEn());
        cv.put(Contracts.WordsTable.COLUMN_SENTENCETR, word.getSentenceTr());
        cv.put(Contracts.WordsTable.COLUMN_DATE, getNow());
        cv.put(Contracts.WordsTable.COLUMN_IMAGE, word.getBytes());

        db = getWritableDatabase();
        db.update(Contracts.WordsTable.TABLE_NAME, cv, "id = ?", new String[]{String.valueOf(word.getId())});
        db.close();
    }

    public ArrayList<Word> getWordArrayList() {

        db = getReadableDatabase();
        ArrayList<Word> wordArrayList = new ArrayList<>();

        String[] Projection = {
                Contracts.WordsTable.COLUMN_ID,
                Contracts.WordsTable.COLUMN_WORDEN,
                Contracts.WordsTable.COLUMN_WORDTR,
                Contracts.WordsTable.COLUMN_SENTENCEEN,
                Contracts.WordsTable.COLUMN_SENTENCETR,
                Contracts.WordsTable.COLUMN_DATE,
                Contracts.WordsTable.COLUMN_IMAGE
        };

        Cursor c = db.query(Contracts.WordsTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null);

        Word word;
        if (c.moveToFirst()) {
            do {
                word = new Word();

                word.setId(c.getInt(c.getColumnIndex(Contracts.WordsTable.COLUMN_ID)));
                word.setWordEn(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_WORDEN)));
                word.setWordTr(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_WORDTR)));
                word.setSentenceEn(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_SENTENCEEN)));
                word.setSentenceTr(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_SENTENCETR)));
                word.setWordDate(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_DATE)));
                word.setBytes(c.getBlob(c.getColumnIndex(Contracts.WordsTable.COLUMN_IMAGE)));

                wordArrayList.add(word);
            } while (c.moveToNext());

        }
        c.close();
        db.close();
        return wordArrayList;
    }

    public void deleteWord(Word word) {
        db = getWritableDatabase();
        db.delete(Contracts.WordsTable.TABLE_NAME, "id = " + word.getId(), null);
        db.close();
    }

    public ArrayList<Word> getWords() {
        db = getReadableDatabase();

        String[] Projection = {
                Contracts.WordsTable.COLUMN_WORDEN,
                Contracts.WordsTable.COLUMN_WORDTR,
                Contracts.WordsTable.COLUMN_SENTENCEEN,
                Contracts.WordsTable.COLUMN_SENTENCETR,
                Contracts.WordsTable.COLUMN_DATE,
                Contracts.WordsTable.COLUMN_IMAGE
        };

        ArrayList<Word> returnValue = new ArrayList<>();

        Cursor c = db.query(Contracts.WordsTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                "id DESC",
                "20");


        Word word;

        if (c.moveToFirst()) {
            do {
                word = new Word();
                word.setWordEn(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_WORDEN)));
                word.setWordTr(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_WORDTR)));
                word.setSentenceEn(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_SENTENCEEN)));
                word.setSentenceTr(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_SENTENCETR)));
                word.setWordDate(c.getString(c.getColumnIndex(Contracts.WordsTable.COLUMN_DATE)));
                word.setBytes(c.getBlob(c.getColumnIndex(Contracts.WordsTable.COLUMN_IMAGE)));

                returnValue.add(word);
            } while (c.moveToNext());

        }

        c.close();
        db.close();
        return returnValue;
    }

    public int getCountWord() {
        db = getReadableDatabase();

        SQLiteStatement s = db.compileStatement("SELECT COUNT(*) FROM words;");
        return (int) s.simpleQueryForLong();
    }

    private String getNow() {
        Date date = new Date();

        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyLocalizedPattern(" d MMM yyyy HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
