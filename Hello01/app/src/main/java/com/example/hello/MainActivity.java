package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.ui.login.LoginActivity;
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

    private Button btn_next = null;
    private Button btn_login = null;
    private Button btn_calling = null;
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

        btn_next = findViewById(R.id.btn_next);
        btn_login = findViewById(R.id.btn_login);
        btn_calling = findViewById(R.id.btn_calling);

        // click event를 처리할 event Handler 선언하기
        // interface를 사용하여 직접 객체를 생성하는 방법
        // 전통자바에서는 interface를 impl 한 클래스를 작성하고
        // 클래스를 사용하여 객체를 선언(생성, 초기화)하였는데
        // interface를 직접 객체를 사용하는 용도로 활용하기
        View.OnClickListener btn_click = new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(v.getId() == R.id.btn_login) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else if(v.getId() == R.id.btn_next) {

                    /*
                    intent(인텐트)
                    Android에서 Activitiy를 부르는 다른 이름
                    Actibify에 super parant

                    새로운 Activity를 보여주는 절차
                    1. Intent 클래스를 사용하여 인텐트 생성
                    2. startActivity() method 호출하여 새로운 Activity로 화면 전환

                    startActivity() 는 이미 준비된 method
                     */
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else if(v.getId() == R.id.btn_calling) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:010-5118-3576"));
                    startActivity(intent);
                }

            }
        };

        // 하나의 event handler를 생성하여
        // 2개의 버튼을 동시에 적용하기
        btn_next.setOnClickListener(btn_click);
        btn_login.setOnClickListener(btn_click);
        btn_calling.setOnClickListener(btn_click);

        txt1.setText("Cafe is BBBEEESSTT");
        txt2.setText("Lunch Time Soon!");

        txt1.setOnClickListener( (view)->{
            String txt = edit_01.getText().toString();
            String msg = String.format("입력한 번호는 %s", txt);
            Snackbar.make(view,msg,Snackbar.LENGTH_SHORT).show();
        } );

        txt2.setOnClickListener( (view)->{
            String txt = edit_01.getText().toString();
            txt = "입력한 전화번호 " + txt;
            Snackbar.make(view,txt,Snackbar.LENGTH_LONG).show();
        } );

    }
}