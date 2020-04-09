package com.example.ingilizce.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ingilizce.Adapters.QuizArrayAdapter;
import com.example.ingilizce.DataBase.QuizDbHelper;
import com.example.ingilizce.Quiz.QuizResult;
import com.example.ingilizce.Quiz.ShowQuizInfos;
import com.example.ingilizce.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SinavlarFragment extends Fragment {


    public SinavlarFragment() {
        // Required empty public constructor
    }

    private ArrayList<QuizResult> quizArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sinavlar, container, false);
        ListView listView = rootView.findViewById(R.id.quizList);

        quizArrayList = new ArrayList<>();
        getData();

        QuizArrayAdapter customAdapter = new QuizArrayAdapter(getContext(), R.layout.custom_layout_quiz_list, quizArrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ShowQuizInfos.class);
                intent.putExtra("quiz", quizArrayList.get(position));
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void getData() {
        QuizDbHelper quizDbHelper = new QuizDbHelper(getContext());
        quizArrayList = quizDbHelper.getQuizArrayList();
    }

}
