package com.callor.cacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.callor.cacao.adapter.ChattAdapter;
import com.callor.cacao.model.Chat;
import com.callor.cacao.service.FireServiceImplV1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText txt_msg;
    private AppCompatButton btn_send;

    private RecyclerView chat_list_view;
    private ChattAdapter chattAdapter;
    private List<Chat> chatList;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chat_list_view = findViewById(R.id.chatt_list_view);

        chatList = new ArrayList<Chat>();

        chattAdapter = new ChattAdapter(chatList);

        chat_list_view.setAdapter(chattAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        chat_list_view.setLayoutManager(layoutManager);

        dbRef = FirebaseDatabase.getInstance().getReference("chatting");

        ChildEventListener childEventListener = new FireServiceImplV1(chattAdapter);

        dbRef.addChildEventListener(childEventListener);

        btn_send.setOnClickListener(view-> {
            String msg = txt_msg.getText().toString();

            if(!msg.isEmpty() && msg != null){
                Chat chat = new Chat();
                chat.setMsg(msg);
                chat.setName("HELLO");

                dbRef.push().setValue(chat);

                txt_msg.setText("");
            }
        });
    }
}