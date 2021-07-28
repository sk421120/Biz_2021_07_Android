package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import model.UserDTO;

public class MainActivity extends AppCompatActivity {

    private Button btn_submit = null;
    private TextInputEditText input_id = null;
    private TextInputEditText input_password = null;
    private TextInputEditText input_name = null;
    private TextInputEditText input_tel = null;
    private TextInputEditText input_addr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_submit = findViewById(R.id.btn_submit);

        input_id = findViewById(R.id.txt_id);
        input_password = findViewById(R.id.txt_password);
        input_name = findViewById(R.id.txt_name);
        input_tel = findViewById(R.id.txt_tel);
        input_addr = findViewById(R.id.txt_addr);

        btn_submit.setOnClickListener(view-> {
            String id = input_id.getText().toString();
            String password = input_password.getText().toString();
            String name = input_name.getText().toString();
            String tel = input_tel.getText().toString();
            String addr = input_addr.getText().toString();

            if(id.isEmpty()){
                input_id.setError("Please input your ID!");
                return;
            }
            if(password.isEmpty()){
                input_password.setError("Please input your PASSWORD");
                return;
            }
            if(name.isEmpty()){
                input_name.setError("Please input your NAME");
                return;
            }

            UserDTO user = new UserDTO();
            user.user_id = id;
            user.password = password;
            user.name = name;
            user.tel = tel;
            user.addr = addr;

            Intent join_intent = new Intent(MainActivity.this, JoinActivity.class);
//            join_intent.putExtra("user_id", id);
//            join_intent.putExtra("password", password);
//            join_intent.putExtra("name", name);
//            join_intent.putExtra("tel", tel);
//            join_intent.putExtra("addr", addr);

//            join_intent.putExtra("USER",user.getClass());
            join_intent.putExtra("USER", (Parcelable)user); // 시리얼라이징
            startActivity(join_intent);

        });
    }
}