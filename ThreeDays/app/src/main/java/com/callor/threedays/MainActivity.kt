package com.callor.threedays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.callor.threedays.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener() {
//            binding.loginLayoutId
        }
    }

}