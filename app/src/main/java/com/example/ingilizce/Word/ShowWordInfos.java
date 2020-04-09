package com.example.ingilizce.Word;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ingilizce.DataBase.WordsDbHelper;
import com.example.ingilizce.Activities.MainActivity;
import com.example.ingilizce.R;

public class ShowWordInfos extends AppCompatActivity {

    ImageView imageView;
    TextView wordEnText;
    TextView wordTrText;
    TextView sentenceEnText;
    TextView sentenceTrText;
    TextView dateText;

    Word incomingWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_word_infos);

        imageView = findViewById(R.id.wordImage);
        wordEnText = findViewById(R.id.wordEn);
        wordTrText = findViewById(R.id.wordTr);
        sentenceEnText = findViewById(R.id.sentenceEn);
        sentenceTrText = findViewById(R.id.sentenceTr);
        dateText = findViewById(R.id.wordDate);

        Intent intent = getIntent();
        incomingWord = (Word) intent.getSerializableExtra("word");

        wordEnText.setText(incomingWord.getWordEn());
        wordTrText.setText(incomingWord.getWordTr());
        sentenceEnText.setText(incomingWord.getSentenceEn());
        sentenceTrText.setText(incomingWord.getSentenceTr());
        dateText.setText(incomingWord.getWordDate());

        byte[] bytes = incomingWord.getBytes();

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imageView.setImageBitmap(bitmap);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflater
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.update_word_item) {
            //update word
            Intent intent = new Intent(this, AddWord.class);
            intent.putExtra("info", "update");
            intent.putExtra("word", incomingWord);
            startActivity(intent);
        } else if (item.getItemId() == R.id.delete_word_item) {
            //delete  word
            WordsDbHelper wordsDbHelper = new WordsDbHelper(this);
            wordsDbHelper.deleteWord(incomingWord);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("position", 1);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
