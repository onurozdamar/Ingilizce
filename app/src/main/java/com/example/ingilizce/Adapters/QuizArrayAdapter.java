package com.example.ingilizce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ingilizce.Quiz.QuizResult;
import com.example.ingilizce.R;

import java.util.ArrayList;

public class QuizArrayAdapter extends ArrayAdapter<QuizResult> {

    private Context context;
    private int recource;

    public QuizArrayAdapter(Context context, int resource, ArrayList<QuizResult> quizzes) {
        super(context, resource, quizzes);
        this.context = context;
        this.recource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(recource, parent, false);

        TextView textView = convertView.findViewById(R.id.custLayQuizTextView);

        textView.setText(getItem(position).getId() + 1 + ". Sınav");

        return convertView;
    }
}
