package com.example.empty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.empty.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    lateinit var : 지금 변수 선언만 하고 생성은 잠시 후에 실행하겠다
    private lateinit var mainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        선언된 mainBinding 을 activity_main.xml 파일을 열어서 inflate(확장)
//        하여 mainBinding 객채로(변수로) 초기화하기
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val navController = findNavController(R.id.fragmentContainer)
        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.bottom_nav_home, R.id.bottom_nav_book
            )
        )

        setupActionBarWithNavController(navController, appBarConfig)
        mainBinding.bottomNav.setupWithNavController(navController)
    }
}