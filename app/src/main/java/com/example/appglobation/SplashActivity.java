package com.example.appglobation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity mainActivity = new MainActivity();
                Intent intent = new Intent(SplashActivity.this,mainActivity.getClass());
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
