package com.example.cocktailgenie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2500;//Milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent (MainActivity.this,HomeActivity.class);
                startActivity(homeIntent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
