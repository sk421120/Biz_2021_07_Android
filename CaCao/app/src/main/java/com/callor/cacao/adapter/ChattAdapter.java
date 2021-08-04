package com.callor.cacao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.callor.cacao.R;
import com.callor.cacao.model.Chat;

public class ChattAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Chat> chatList;

    public void addChatList(Chat chat) {
        chatList.add(chat);

        notifyItemInserted(chatList.size()-1);
    }

    public ChattAdapter(List<Chat> chatList) {
        this.chatList = chatList;
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
    }

    @Override
    public int getItemCount() {
        return chatList == null ? 0 : chatList.size();
    }

    public static class ChattViewHolder extends RecyclerView.ViewHolder {

        public TextView item_name;
        public TextView item_msg;

        public ChattViewHolder(@NonNull View itemView) {
            super(itemView);
            this.item_name = itemView.findViewById(R.id.item_name);
            this.item_msg = itemView.findViewById(R.id.item_msg);
        }
    }
}
