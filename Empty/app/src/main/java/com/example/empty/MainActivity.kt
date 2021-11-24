package com.example.empty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.empty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    lateinit var : 지금 변수
    private lateinit var mainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        선언된 mainBinding 을 activity_main.xml 파일을 열어서 inflate(확장)
//        하여 mainBinding 객채로(변수로) 초기화하기
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }
}