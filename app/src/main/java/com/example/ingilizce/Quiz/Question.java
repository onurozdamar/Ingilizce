package com.example.ingilizce.Quiz;

class Question {
    private String question;
    private String[] options = new String[4];
    private String answer;

    Question(String question, String optionOne, String optionTwo, String optionThree, String optionFour, String answer) {
        this.question = question;
        this.options[0] = optionOne;
        this.options[1] = optionTwo;
        this.options[2] = optionThree;
        this.options[3] = optionFour;
        this.answer = answer;
    }

    String getQuestion() {
        return question;
    }

    String getOptionOne() {
        return options[0];
    }

    String getOptionTwo() {
        return options[1];
    }

    String getOptionThree() {
        return options[2];
    }

    String getOptionFour() {
        return options[3];
    }


    String getAnswer() {
        return answer;
    }
}
