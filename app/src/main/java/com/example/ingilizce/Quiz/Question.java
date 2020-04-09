package com.example.ingilizce.Quiz;

public class Question {
    private String question;
    private String[] options = new String[4];
    private String answer;

    public Question(String question, String optionOne, String optionTwo, String optionThree, String optionFour, String answer) {
        this.question = question;
        this.options[0] = optionOne;
        this.options[1] = optionTwo;
        this.options[2] = optionThree;
        this.options[3] = optionFour;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionOne() {
        return options[0];
    }

    public String getOptionTwo() {
        return options[1];
    }

    public String getOptionThree() {
        return options[2];
    }

    public String getOptionFour() {
        return options[3];
    }


    public String getAnswer() {
        return answer;
    }
}
