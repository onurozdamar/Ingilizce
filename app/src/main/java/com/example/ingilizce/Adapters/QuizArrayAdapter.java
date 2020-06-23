package com.example.ingilizce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                convertView = inflater.inflate(recource, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (viewHolder != null) {
            viewHolder.setTexts(getItem(position));
        }

        return convertView;
    }

    class ViewHolder {

        private TextView textView;

        ViewHolder(View view) {
            textView = view.findViewById(R.id.custLayQuizTextView);
        }

        void setTexts(QuizResult quizResult) {
            if (quizResult != null) {
                textView.setText((quizResult.getId() + 1 + ". Sınav"));
            }
        }

    }
}
