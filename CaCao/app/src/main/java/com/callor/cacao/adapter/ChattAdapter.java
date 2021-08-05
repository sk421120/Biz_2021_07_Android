package com.callor.cacao.adapter;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.callor.cacao.R;
import com.callor.cacao.model.Chat;

public class ChattAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Chat> chatList;
    private String name;

    public void addChatList(Chat chat) {
        chatList.add(chat);

        notifyItemInserted(chatList.size()-1);
    }

    public ChattAdapter(List<Chat> chatList) {
//        this.chatList = chatList;
        this(chatList,"NoNamed");
    }

    public ChattAdapter(List<Chat> chatList, String name) {
        this.chatList = chatList;
        this.name = name;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_layout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatt_item, parent, false);

        ChattViewHolder viewHolder = new ChattViewHolder(item_layout);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Chat chat = chatList.get(position);

        ChattViewHolder chattViewHolder = (ChattViewHolder) holder;

        chattViewHolder.item_name.setText(chat.getName());
        chattViewHolder.item_msg.setText(chat.getMsg());

        /*
        현재 App에서 보낸 메시지를 DB에서 가져왔으면(Fetch)
         */
        if(this.name.equals(chat.getName())){
            chattViewHolder.item_name.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            chattViewHolder.item_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
//            chattViewHolder.msgLinear.setGravity(Gravity.END);
            chattViewHolder.item_msg.setBackgroundColor(Color.parseColor("#ffeb3b"));
        }

    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0 : chatList.size();
    }

    public static class ChattViewHolder extends RecyclerView.ViewHolder {

        public TextView item_name;
        public TextView item_msg;

        public LinearLayout msgLinear;

        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item_name = itemView.findViewById(R.id.item_name);
            this.item_msg = itemView.findViewById(R.id.item_msg);

            /*
            item_name과 item_msg를 감싸고 있는 layout에 접근하기 위하여 객체로 생성
             */
            this.msgLinear = itemView.findViewById(R.id.msg_linear);
        }
    }
}
