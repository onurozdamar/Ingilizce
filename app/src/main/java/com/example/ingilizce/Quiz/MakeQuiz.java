package com.example.ingilizce.Quiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ingilizce.R;

public class MakeQuiz extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton optionOne;
    private RadioButton optionTwo;
    private RadioButton optionThree;
    private RadioButton optionFour;

    private TextView questionTextView;
    private TextView countTextView;
    private TextView wrongCountTextView;
    private TextView correctCountTextView;

    private Quiz quiz = new Quiz(this);

    private Handler handler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        radioGroup = findViewById(R.id.radioGroup);
        optionOne = findViewById(R.id.option_one);
        optionTwo = findViewById(R.id.option_two);
        optionThree = findViewById(R.id.option_three);
        optionFour = findViewById(R.id.option_four);

        questionTextView = findViewById(R.id.questionText);
        countTextView = findViewById(R.id.countTextView);
        wrongCountTextView = findViewById(R.id.countWrongTextView);
        correctCountTextView = findViewById(R.id.countCorrectTextView);

        if (!quiz.isCanCreateQuiz()) {
            setVisiableButtons(View.INVISIBLE);
            questionTextView.setText("Sınav  için yeterli kelimeniz  yoktur");
        } else {
            quiz.startQuiz();
            nextQuestion();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClickButton(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        final RadioButton radioButton = findViewById(radioId);
        radioButton.setBackground(ContextCompat.getDrawable(this, R.drawable.confirm_button_background));
        setEnableButtons(false);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkSolution(radioButton, radioButton.getText().toString());
            }
        }, 500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                nextQuestion();
            }
        }, 1000);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void nextQuestion() {
        if (quiz.goNextQuestion()) {
            changeBackground();
            setEnableButtons(true);
            //Log.d("name", "şuanki soru " + quiz.getCount());
            questionTextView.setText(quiz.getNextQuestion());
            countTextView.setText(quiz.getCount() + 1 + "");
            wrongCountTextView.setText(quiz.getWrongCount() + "");
            correctCountTextView.setText(quiz.getCorrectCount() + "");
            setOptionText();
        } else {
            setVisiableButtons(View.INVISIBLE);
            questionTextView.setText("quiz end correct: " + quiz.getCorrectCount() + " wrong: " + quiz.getWrongCount());
            countTextView.setText("");
            wrongCountTextView.setText("");
            correctCountTextView.setText("");
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void checkSolution(RadioButton radioButton, String string) {
        if (quiz.isAnswerTrue(string)) {
            radioButton.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_button_background));
            quiz.increaseCorrectCount();
        } else {
            radioButton.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_button_background));
            quiz.increaseWrongCount();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void changeBackground() {
        optionOne.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        optionTwo.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        optionThree.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
        optionFour.setBackground(ContextCompat.getDrawable(this, R.drawable.button_background));
    }

    private void setEnableButtons(boolean enable) {
        optionOne.setClickable(enable);
        optionTwo.setClickable(enable);
        optionThree.setClickable(enable);
        optionFour.setClickable(enable);
    }

    private void setVisiableButtons(int visiable) {
        optionOne.setVisibility(visiable);
        optionTwo.setVisibility(visiable);
        optionThree.setVisibility(visiable);
        optionFour.setVisibility(visiable);
    }

    private void setOptionText() {
        optionOne.setText(quiz.getOptionOne());
        optionTwo.setText(quiz.getOptionTwo());
        optionThree.setText(quiz.getOptionThree());
        optionFour.setText(quiz.getOptionFour());
    }

}
