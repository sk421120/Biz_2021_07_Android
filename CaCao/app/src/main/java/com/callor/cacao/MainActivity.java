package com.callor.cacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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

    private String nickname = "NoNamed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String nickname = preferences.getString("nick_name", "NoNamed");
        String alarm = preferences.getString("alarm", "ON");

        Log.d("NickName", nickname);
        Log.d("Alarm", alarm);

        /*
        custom 된 toolbar를 ActionBar로 설정하기 위한 코드
         */
        Toolbar main_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(main_toolbar);

        /*
        새로운 Activity가 열렸을때 이전 Activity(page)로 돌아가기 아이콘을 표시학
        MainActivity에서는 의미가 없기 때문에 사용하지 않는다
         */
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        txt_msg = findViewById(R.id.txt_msg);
        btn_send = findViewById(R.id.btn_send);

        chat_list_view = findViewById(R.id.chatt_list_view);

        chatList = new ArrayList<Chat>();

        // App에 등록된 nickname을 Adapter에 데이터와 함께 전달하기
//        chattAdapter = new ChattAdapter(chatList);
        chattAdapter = new ChattAdapter(chatList, nickname);

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
                chat.setName(nickname);

                dbRef.push().setValue(chat);

                txt_msg.setText("");
            }
        });
    }   // end onCreate()

    /*
    Custom 한 Toolbar가 (Main)Activity에 적용될때
    setSupportActionBar() method가 실행될때
    event가 발생하고 자동으로 호출되는 method

    Toolbar를 사용하여 ActionBar Custom하는 이유 중에 하나가
    onCreateOptionsMenu() method를 Override하여 더욱 세밀한 Customizing을 하기 위해서 이다

    Toolbar에 사용자 정의형 menu를 설정하여
    다른 기능을 수행하도록 하는 UI를 구현할 수 있다

    @param menu
    @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // xml 끼얹기 inflater
        getMenuInflater().inflate(R.menu.main_tool_menu, menu);

        return true;
    }

    /*
    ActionBar에 설정된 Option Menu의 특정한 항목(item)을 클릭하면 호출되는 method

    @param item
    @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menu_item = item.getItemId();
        if(menu_item == R.id.app_bar_settings) {
            Toast.makeText(this,"Settings Menu!", Toast.LENGTH_SHORT).show();
            Intent setting_intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(setting_intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}