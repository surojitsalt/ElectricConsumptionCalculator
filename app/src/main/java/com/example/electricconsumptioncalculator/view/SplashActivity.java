package com.example.electricconsumptioncalculator.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.example.electricconsumptioncalculator.R;

public class SplashActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        setDelayonSplash();
    }

    private void setDelayonSplash(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        openMainScreen();
                    }
                });

            }
        }).start();
    }

    private void openMainScreen(){
        Intent vIntent = new Intent(this, MainActivity.class);
        startActivity(vIntent);
        finish();
    }
}
