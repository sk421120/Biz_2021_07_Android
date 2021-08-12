package com.callor.myword.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// 이 Class는 DTO 역할을 수행함과 동시에 table을 만들기 위한 구조화 model이다
@Entity(tableName = "tbl_word")
public class WordDTO {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;    // 영어단어

    @ColumnInfo(name = "korea")
    private String korea;   // 한글 뜻

    // getter
    @NonNull
    public String getWord() {
        return word;
    }

    public String getKorea() {
        return korea;
    }

    // setter
    public void setWord(@NonNull String word) {
        this.word = word;
    }

    public void setKorea(String korea) {
        this.korea = korea;
    }

    // toString
    @Override
    public String toString() {
        return "WordDTO{" +
                "word='" + word + '\'' +
                ", korea='" + korea + '\'' +
                '}';
    }
}
