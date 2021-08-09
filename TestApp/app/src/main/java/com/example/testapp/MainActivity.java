package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView[] colors;
    private View planet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors = new TextView[4];

        createRandColor(colors);

        planet = findViewById(R.id.draw_planet);

        planet.setOnClickListener(view -> {
//            planet.color
        });

    }

    private float[] createRandColor() {
        Random rnd = new Random();
        int r = rnd.nextInt(256);
        int g = rnd.nextInt(256);
        int b = rnd.nextInt(256);

        float[] hsv = new float[3];

        Color.RGBToHSV(r,g,b,hsv);

        float h = hsv[0];
        float s = hsv[1];
        // value
        float v = hsv[2];

        Log.d("h", String.format("%f",h));
        Log.d("s", String.format("%f",s));
        Log.d("v", String.format("%f",v));

        return hsv;
    }

    private void createRandColor(TextView[] colors) {
        colors[0] = findViewById(R.id.txt_color1);
        colors[1] = findViewById(R.id.txt_color2);
        colors[2] = findViewById(R.id.txt_color3);
        colors[3] = findViewById(R.id.txt_color4);

        float[] color = new float[3];

        for (int i =0; i < colors.length ; i++){
            color = createRandColor();
            int txt_color = Color.HSVToColor(color);
//            colors[i].setText(String.format("%d",txt_color));
//            colors[i].setText(color.toString());
            colors[i].setTextColor( txt_color );
            color = createRandColor();
            txt_color = Color.HSVToColor(color);
            colors[i].setBackgroundColor(txt_color);
        }
    }
}