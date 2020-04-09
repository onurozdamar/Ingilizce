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

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // layout inflator'ı context'ten alıyoruz
        //Aldığımız inflater ile oluşturduğumuz custom layoutu alıyoruz
        //Custom layouta koyduğumuz text view'a listedeki kelimeyi yazdırıyoruz

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(recource, parent, false);

        TextView textView = convertView.findViewById(R.id.custLayTextView);

        textView.setText(getItem(position).getWordEn());

        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
