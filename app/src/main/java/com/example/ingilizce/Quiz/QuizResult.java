package com.example.ingilizce.Quiz;

import java.io.Serializable;

public class QuizResult implements Serializable {

    private int id;
    private int correctCount = 0;
    private int wrongCount = 0;
    private String date;

    public QuizResult() {
    }

    QuizResult(int correctCount, int wrongCount) {
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public void setWrongCount(int wrongCount) {
        this.wrongCount = wrongCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    void increaseCorrectCount() {
        correctCount++;
    }

    void increaseWrongCount() {
        wrongCount++;
    }


}
