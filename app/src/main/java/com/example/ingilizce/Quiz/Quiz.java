package com.example.ingilizce.Quiz;

import android.content.Context;

import com.example.ingilizce.DataBase.WordsDbHelper;
import com.example.ingilizce.Word.Word;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    private static final int maxQuestion = 20;
    private Question[] questions;
    private int count = -1;
    private WordsDbHelper wordsDbHelper;

    private QuizResult quizResult;

    public Quiz(Context context) {
        wordsDbHelper = new WordsDbHelper(context);
        quizResult = new QuizResult();
        questions = new Question[maxQuestion];
    }

    private void getQuestions() {
        ArrayList<Word> words;
        words = wordsDbHelper.getWords();

        ArrayList<Word> tempWords = new ArrayList<>(words);

        Collections.shuffle(words);

        for (int i = 0; i < maxQuestion; i++) {
            Collections.shuffle(tempWords);
            ArrayList<String> options = new ArrayList<>();

            tempWords.remove(words.get(i));

            options.add(words.get(i).getWordTr());
            options.add(tempWords.get(0).getWordTr());
            options.add(tempWords.get(1).getWordTr());
            options.add(tempWords.get(2).getWordTr());

            tempWords.add(words.get(i));

            Collections.shuffle(options);

            questions[i] = new Question(words.get(i).getWordEn(), options.get(0), options.get(1), options.get(2), options.get(3), words.get(i).getWordTr());
        }

    }

    void startQuiz() {
        getQuestions();
    }

    int getCount() {
        return count;
    }

    boolean goNextQuestion() {
        return count++ < 19;
    }

    boolean isCanCreateQuiz() {
        return wordsDbHelper.getWordCount() >= maxQuestion;
    }

    boolean isAnswerTrue(String str) {
        return questions[count].getAnswer().matches(str);
    }

    String getOptionOne() {
        return questions[count].getOptionOne();
    }

    String getOptionTwo() {
        return questions[count].getOptionTwo();
    }

    String getOptionThree() {
        return questions[count].getOptionThree();
    }

    String getOptionFour() {
        return questions[count].getOptionFour();
    }

    String getNextQuestion() {
        return questions[count].getQuestion();
    }

    void increaseCorrectCount() {
        quizResult.increaseCorrectCount();
    }

    void increaseWrongCount() {
        quizResult.increaseWrongCount();
    }

    int getCorrectCount() {
        return quizResult.getCorrectCount();
    }

    int getWrongCount() {
        return quizResult.getWrongCount();
    }
}
