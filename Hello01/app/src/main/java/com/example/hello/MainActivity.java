package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

/*
Activity.class
기본 하는 일
1. 화면에 UI를 표현하는 일
    *.xml 파일을 읽어서 화면을 구현
    event Handling 수행
2. Activity 이름 짓기
    Activity 클래스는 UI 관련된 파일을 1개 이상 연결한다
    이름Activity.java 라는 이름으로 작성하고
    activity_이름.xml 라는 이름으로 화면 구현 xml 파일을 작성
 */
public class MainActivity extends AppCompatActivity {

    private TextView txt1 = null;
    private TextView txt2 = null;

    private EditText edit_01 = null;

    /*
    on* () method는 대체로 event Handler 들이다
    Create 동작이 실행될때 같이 동반하여 작동되는 Method
    작성하고 있는 App이 안드로이드 phone에서 생성될 때 작동하는 method
    
    안드로이드 App에서는 onCreate()를 진입점 method로 취급한다
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        res.layout.activity_main.xml 파일을 읽어서(열어서)
        시작화면(현재클래스가 작동되는 화면)을 만들어라
         */
        setContentView(R.layout.activity_main);

        /*
        xml view에 설정된 view content를 java code에서 사용하기 위하여 import 하는 절차
         */
        txt1 = findViewById(R.id.txt_01);
        txt2 = findViewById(R.id.txt_02);

        edit_01 = findViewById(R.id.edit_01);

        txt1.setText("Cafe is BBBEEESSTT");
        txt2.setText("Lunch Time Soon!");

//        txt2.setOnClickListener( (view)->{
//            Toast.makeText(this,txt2.getText(),Toast.LENGTH_LONG).show();
//        } );
        txt1.setOnClickListener( (view)->{
//            Toast.makeText(this,"Cafe is Best",Toast.LENGTH_SHORT).show();
            String txt = edit_01.getText().toString();
            String msg = String.format("입력한 번호는 %s", txt);
            Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
        } );

//        txt1.setOnClickListener( (view)->{
//            Toast.makeText(this,txt1.getText(),Toast.LENGTH_LONG).show();
//        } );
//        txt2.setOnClickListener( (view)->{
//            Toast.makeText(this,"Sleeping Time.. And Hungry",Toast.LENGTH_LONG).show();
//        } );
        txt2.setOnClickListener( (view)->{
            String txt = edit_01.getText().toString();
            txt = "입력한 전화번호 " + txt;
            Snackbar.make(view,txt,Snackbar.LENGTH_LONG).show();
//            Snackbar.make(view,txt2.getText(),Snackbar.LENGTH_INDEFINITE).show();
        } );
    }
}