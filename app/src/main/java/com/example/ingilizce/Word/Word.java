package com.example.ingilizce.Word;

import java.io.Serializable;

public class Word implements Serializable {
    private int id;
    private String wordEn;
    private String wordTr;
    private String sentenceEn;
    private String sentenceTr;
    private String wordDate;
    private byte[] bytes;


    public Word() {
    }

    public Word(String wordEn, String wordTr, String sentenceEn, String sentenceTr, byte[] bytes) {
        this.wordEn = wordEn;
        this.wordTr = wordTr;
        this.sentenceEn = sentenceEn;
        this.sentenceTr = sentenceTr;
        this.bytes = bytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordEn() {
        return wordEn;
    }

    public void setWordEn(String wordEn) {
        this.wordEn = wordEn;
    }

    public String getWordTr() {
        return wordTr;
    }

    public void setWordTr(String wordTr) {
        this.wordTr = wordTr;
    }

    public String getSentenceEn() {
        return sentenceEn;
    }

    public void setSentenceEn(String sentenceEn) {
        this.sentenceEn = sentenceEn;
    }

    public String getSentenceTr() {
        return sentenceTr;
    }

    public void setSentenceTr(String sentenceTr) {
        this.sentenceTr = sentenceTr;
    }

    String getWordDate() {
        return wordDate;
    }

    public void setWordDate(String wordDate) {
        this.wordDate = wordDate;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
