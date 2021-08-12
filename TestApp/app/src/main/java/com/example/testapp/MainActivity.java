package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button rnd_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rnd_color = findViewById(R.id.rnd_color);

        rnd_color.setOnClickListener(view-> {
            Intent randomColor = new Intent(MainActivity.this,RandomColor.class);
            startActivity(randomColor);
        });
    }

}