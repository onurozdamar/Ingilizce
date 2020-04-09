package com.example.ingilizce.Fragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ingilizce.Adapters.MyArrayAdapter;
import com.example.ingilizce.DataBase.WordsDbHelper;
import com.example.ingilizce.R;
import com.example.ingilizce.Word.AddWord;
import com.example.ingilizce.Word.ShowWordInfos;
import com.example.ingilizce.Word.Word;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class KelimelerFragment extends Fragment {


    public KelimelerFragment() {
        // Required empty public constructor
    }

    private ArrayList<Word> wordArrayList;
    private MyArrayAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_kelimeler, container, false);
        ListView listView = rootView.findViewById(R.id.wordsListView);

        wordArrayList = new ArrayList<>();
        getData();

        customAdapter = new MyArrayAdapter(getContext(), R.layout.custom_layout, wordArrayList);
        listView.setAdapter(customAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ShowWordInfos.class);
                intent.putExtra("word", wordArrayList.get(position));
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);

        return rootView;
    }

    private void getData() {
        WordsDbHelper wordsDbHelper = new WordsDbHelper(getContext());
        wordArrayList = wordsDbHelper.getWordArrayList();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        Objects.requireNonNull(getActivity()).getMenuInflater().inflate(R.menu.listview_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getItemId() == R.id.duzenle_item) {
            Intent intent = new Intent(getContext(), AddWord.class);
            intent.putExtra("info", "update");
            intent.putExtra("word", wordArrayList.get(position));
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.delete_item) {
            WordsDbHelper wordsDbHelper  = new WordsDbHelper(getContext());
            wordsDbHelper.deleteWord(wordArrayList.get(position));

            wordArrayList.remove(position);
            //getData();
            customAdapter.notifyDataSetChanged();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
}
