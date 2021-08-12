package com.callor.myword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.callor.myword.databinding.ActivityWordInsertBinding;

public class WordInsertActivity extends AppCompatActivity {

    // Binding ready to apply
    ActivityWordInsertBinding wordBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_insert);

        // Binding make to apply(init)
        wordBinding = ActivityWordInsertBinding.inflate(getLayoutInflater());

        // Binding applty to make layout screen
        // setContentView(R.layout.activity_word_insert);
        setContentView(wordBinding.getRoot());

        // open한 Activity에게 return하기 위한 intent 정보 추출
        // open한 Activity에서 데이터를 보내면 그 데이터 받는 용도로도 사용된다
        Intent returnIntent = new Intent();

        // btn_save click event
        wordBinding.btnSave.setOnClickListener(view-> {
            // 두개의 입력박스에 입력한 값을 변수에 담기
            String word = wordBinding.inputWord.getText().toString();
            String korea = wordBinding.inputKorea.getText().toString();

            // WORD, KOREA 라는 변수를 각각 선언하고
            // word, korea에 담긴 값을 보내기 위하여 setting(putting)하기
            returnIntent.putExtra("WORD", word);
            returnIntent.putExtra("KOREA", korea);

            // 나를 open한 Activity에게 데이터가 준비되었으니(RESULT_OK) 데이터를 받아라
            setResult(RESULT_OK, returnIntent);
            // self close method
            finish();
        });
    }
}