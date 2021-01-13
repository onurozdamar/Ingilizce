package com.example.ingilizce.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ingilizce.Quiz.MakeQuiz;
import com.example.ingilizce.R;
import com.example.ingilizce.Word.AddWord;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_anasayfa, container, false);
        Button addButton = rootView.findViewById(R.id.addWordButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddWord.class);
                intent.putExtra("info", "new");
                startActivity(intent);
            }
        });

        Button quizButton = rootView.findViewById(R.id.ceateQuizButton);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeQuiz.class);
                intent.putExtra("info", "new");
                startActivity(intent);
            }
        });
        return rootView;
    }

}
