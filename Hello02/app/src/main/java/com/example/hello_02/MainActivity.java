package com.example.hello_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    // activity_*.xml 에서 생성한 view를 java에서 핸들링 하기
    // view를 핸들링 하기 위해서 class member 영역에 해당 view의 객체변수를 선언한다

    private Button btn_login = null;
    private TextInputEditText input_email = null;
    private TextInputEditText input_password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml 파일에 선언된 view를 java의 객체로 변환 생성하기
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener( view -> {
            String email = input_email.getText().toString();
            String password = input_password.getText().toString();

            /*
            Java code 에서 정규화 문법 검사
            정규화 문자열을 생성하고 문자열 변수의 matches() method 정규화 문자열을
            전달하여 패턴 검사
             */
            String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if(email.isEmpty()) {
                input_email.setError("Please Input Your Email..");
//                input_email.setFocusable(true);
                return;
            }
            else if(!email.matches(email_pattern))
            {
                input_email.setError("Check Your Email Type");
//                input_email.setFocusable(true);
                return;
            } else {
                input_email.setFocusable(false);
            }

            if(password.isEmpty()){
                input_password.setError("Please Input Your Password..");
                input_password.setFocusable(true);
                return;
            }

            Intent login_intent = new Intent(MainActivity.this,LoginActivity.class);
            login_intent.putExtra("user_id", email);
            login_intent.putExtra("password", password);

            startActivity(login_intent);
//            String msg = String.format("ID : %s \n PW: %s", email, password);
//            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } );
    }
}