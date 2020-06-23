package com.example.ingilizce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ingilizce.R;
import com.example.ingilizce.Word.Word;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Word> {

    private Context context;
    private int recource;

    public MyArrayAdapter(Context context, int resource, ArrayList<Word> words) {
        super(context, resource, words);
        this.context = context;
        this.recource = resource;
    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

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

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    class ViewHolder {

        private TextView textView;


        ViewHolder(View view) {
            textView = view.findViewById(R.id.custLayTextView);
        }

        void setTexts(Word word) {
            textView.setText(String.valueOf(word.getWordEn()));
        }

    }
}
