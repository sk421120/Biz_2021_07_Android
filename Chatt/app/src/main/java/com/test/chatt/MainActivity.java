package com.test.chatt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.chatt.adapter.ChattAdapter;
import com.test.chatt.model.Chat;
import com.test.chatt.service.FireServiceImplV1;

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

    // firebase와 연결하는 Connection을 위한 객체 선언하기
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.activity)
        layout.xml 파일을 읽어서 화면을 만드는 method
        setContentView는 한개의 파일을 읽어서 한개의 전체 화면을 만드는 것
         */
        setContentView(R.layout.activity_main);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chat_list_view = findViewById(R.id.chatt_list_view);

        chatList = new ArrayList<Chat>();
        // TEST 데이터 객체 생성
        /*
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
         */

        // 1. Adapter 객체 생성
        // Adapter 객체를 생성할 때 보여줄 데이터 객체를
        // 생성자 매개변수로 주입해 주어야 한다
        chattAdapter = new ChattAdapter(chatList);

        // 2. RecyclerView.Adapter와 RecyclerView를 서로 연결
        chat_list_view.setAdapter(chattAdapter);

        // 3. RecyclerView의 데이터를 표현하는데 사용할 Layout 매니저를 설정하기
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chat_list_view.setLayoutManager(layoutManager);

        FirebaseDatabase dbConn = FirebaseDatabase.getInstance();

        // 사용할 table
        // realtimeDatabase에서는 table을 path라는 개념으로 부른다
        dbRef = dbConn.getReference("chatting");

        // firebase로 부터 데이터 변화 이벤트가 전달되면 이벤트를 수신하여 할일을 지정하기
        // 이벤트를 수신하여 할 일을 지정하기 위한 핸들러 선언
        ChildEventListener childEventListener = new FireServiceImplV1(chattAdapter);

        // 이벤트 핸들러 연결
        dbRef.addChildEventListener(childEventListener);

        /*
        EditBox에 메세지를 입력하고 Send 버튼을 클릭했을때 할일 지정하기

        EditBox에 메세지를 입력하고 Send를 하면 FireBase의 Realtime DataBase에 데이터를 insert 하기

        */

        btn_send.setOnClickListener(view -> {
            // EditBox에 입력된 문자열을 추출하여 문자열 변수에 담기
            String msg = txt_msg.getText().toString();
            if(!msg.isEmpty() && msg != null ) {
//                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

                Chat chat = new Chat();
                chat.setMsg(msg);
                chat.setName("Welsh_Corgi");

                Log.d("Click", chat.toString());
//                chatList.add(chat);
                // firebase의 realtime DB의 table에 데이터 insert하라 = push
                dbRef.push().setValue(chat);

                txt_msg.setText("");
            }
        });
    }
}