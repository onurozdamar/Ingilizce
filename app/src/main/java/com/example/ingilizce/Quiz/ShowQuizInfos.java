package com.example.ingilizce.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ingilizce.R;

public class ShowQuizInfos extends AppCompatActivity {

    TextView correctAnswerCount;
    TextView wrongAnswerCount;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quiz_infos);

        correctAnswerCount = findViewById(R.id.correct_answer_txt_sqi);
        wrongAnswerCount = findViewById(R.id.wrong_answer_txt_sqi);

        Intent i = getIntent();

        if (i != null) {
            QuizResult quiz = (QuizResult) i.getSerializableExtra("quiz");

            if (quiz != null) {
                correctAnswerCount.setText(quiz.getCorrectCount()+" Doğru");
                wrongAnswerCount.setText(quiz.getWrongCount()+" Yanlış");
            }

        }

    }
}
