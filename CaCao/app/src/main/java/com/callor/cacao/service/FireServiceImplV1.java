package com.callor.cacao.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.callor.cacao.adapter.ChattAdapter;
import com.callor.cacao.model.Chat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class FireServiceImplV1 implements ChildEventListener {

    private ChattAdapter adapter;

    public FireServiceImplV1(ChattAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        Chat chatVO = snapshot.getValue(Chat.class);
        adapter.addChatList(chatVO);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
