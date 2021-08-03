package com.test.chatt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.test.chatt.adapter.ChattAdapter;
import com.test.chatt.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // chatt 메세지를 전달하는 view 들
    private EditText txt_msg;
    private AppCompatButton btn_send;

    // chat 메세지를 표현할 view들
    private RecyclerView chat_list_view;
    private ChattAdapter chattAdapter;
    private List<Chat> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity)
         */
        setContentView(R.layout.activity_main);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chat_list_view = findViewById(R.id.chatt_list_view);

        // 0. 보여줄 데이터 객체 생성
        chatList = new ArrayList<Chat>();
        Chat test = new Chat();
        test.setName("Corgi");
        test.setMsg("Soo Cuute");
        chatList.add(test);

        test = new Chat();
        test.setName("Corgi corgi");
        test.setMsg("Soo Cuuteeee");
        chatList.add(test);

        test = new Chat();
        test.setName("Corgi corgi corgi");
        test.setMsg("Soo Cuuteeeeeee");
        chatList.add(test);
        // 1. Adapter 객체 생성
        // Adapter 객체를 생성할 때 보여줄 데이터 객체를
        // 생성자 매개변수로 주입해 주어야 한다
        chattAdapter = new ChattAdapter(chatList);


        // 2. RecyclerView.Adapter와 RecyclerView를 서로 연결
        chat_list_view.setAdapter(chattAdapter);

        // 3. RecyclerView의 데이터를 표현하는데 사용할 Layout 매니저를 설정하기
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chat_list_view.setLayoutManager(layoutManager);


    }
}