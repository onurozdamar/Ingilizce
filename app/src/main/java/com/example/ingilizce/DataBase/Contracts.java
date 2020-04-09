package com.example.ingilizce.DataBase;

import android.provider.BaseColumns;

public class Contracts {

    public static class WordsTable implements BaseColumns {

        public static final String TABLE_NAME = "words";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_WORDEN = "wordEn";
        public static final String COLUMN_WORDTR = "wordTr";
        public static final String COLUMN_SENTENCEEN = "sentenceEn";
        public static final String COLUMN_SENTENCETR = "sentenceTr";
        public static final String COLUMN_DATE = "wordDate";
        public static final String COLUMN_IMAGE = "image";

    }

    public static class Quizzes implements BaseColumns {

        public static final String TABLE_NAME = "quizzes";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CORRECT = "correct";
        public static final String COLUMN_WRONG = "wrong";
        public static final String COLUMN_DATE = "quizDate";

    }
}
