package com.example.ingilizce.Word;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ingilizce.DataBase.WordsDbHelper;
import com.example.ingilizce.Activities.MainActivity;
import com.example.ingilizce.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddWord extends AppCompatActivity {

    Bitmap selectedImage;
    ImageView imageView;
    EditText wordEnEditText;
    EditText wordTrEditText;
    EditText sentenceEnEditText;
    EditText sentenceTrEditText;

    String info;
    Word incomingWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        imageView = findViewById(R.id.imageView);
        wordEnEditText = findViewById(R.id.wordEnEditText);
        wordTrEditText = findViewById(R.id.wordTrEditText);
        sentenceEnEditText = findViewById(R.id.sentenceEnEditText);
        sentenceTrEditText = findViewById(R.id.sentenceTrEditText);


        Intent intent = getIntent();
        info = intent.getStringExtra("info");
        incomingWord = (Word) intent.getSerializableExtra("word");

        if (info.matches("update")) {
            wordEnEditText.setText(incomingWord.getWordEn());
            wordTrEditText.setText(incomingWord.getWordTr());
            sentenceEnEditText.setText(incomingWord.getSentenceEn());
            sentenceTrEditText.setText(incomingWord.getSentenceTr());

            byte[] bytes = incomingWord.getBytes();

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            imageView.setImageBitmap(bitmap);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void selectImage(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            Intent intenToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intenToGalery, 2);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intenToGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intenToGalery, 2);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();

            try {

                if (Build.VERSION.SDK_INT >= 28) {
                    ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), imageData);
                    selectedImage = ImageDecoder.decodeBitmap(source);
                    imageView.setImageBitmap(selectedImage);
                } else {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageData);
                    imageView.setImageBitmap(selectedImage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void save(View view) {
        String wordEn = wordEnEditText.getText().toString();
        String wordTr = wordTrEditText.getText().toString();
        String sentenceEn = sentenceEnEditText.getText().toString();
        String sentenceTr = sentenceTrEditText.getText().toString();
        byte[] byteArray = {};
        if (selectedImage != null) {
            Bitmap smallImage = makeSmallImage(selectedImage, 300);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            smallImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
            byteArray = outputStream.toByteArray();
        }

        Word newWord = new Word(wordEn, wordTr, sentenceEn, sentenceTr, byteArray);

        try {
            WordsDbHelper wordsDbHelper = new WordsDbHelper(this);

            if (info.matches("update")) {
                if (selectedImage == null) {
                    newWord.setBytes(incomingWord.getBytes());
                }
                newWord.setId(incomingWord.getId());
                wordsDbHelper.updateWord(newWord);
            } else {
                wordsDbHelper.insertWord(newWord);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent i = new Intent(AddWord.this, MainActivity.class);
        i.putExtra("position", 1);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public Bitmap makeSmallImage(Bitmap image, int maxSize) {

        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
