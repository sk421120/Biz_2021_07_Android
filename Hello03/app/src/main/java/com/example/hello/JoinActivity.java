package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import model.UserDTO;

public class JoinActivity extends AppCompatActivity {

//    private TextView txt_join_msg = null;
    private TextView user_id = null;
    private TextView password = null;
    private TextView name = null;
    private TextView tel = null;
    private TextView addr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

//        txt_join_msg = findViewById(R.id.txt_join_msg);

        user_id = findViewById(R.id.txt_join_id);
        password = findViewById(R.id.txt_join_password);
        name = findViewById(R.id.txt_join_name);
        tel = findViewById(R.id.txt_join_tel);
        addr = findViewById(R.id.txt_join_addr);

        Intent intent = getIntent();
        UserDTO user = (UserDTO) intent.getExtras().get("USER");

//        String user_id = intent.getExtras().getString("user_id");
//        String password = intent.getExtras().getString("password");
//        String name = intent.getExtras().getString("name");
//        String tel = intent.getExtras().getString("tel");
//        String addr = intent.getExtras().getString("addr");

//        String msg = String.format("ID : %s \n password : %s \n name : %s \n tel : %s \n addr : %s", user_id, password, name, tel, addr);
//        txt_join_msg.setText(msg);

    }
}