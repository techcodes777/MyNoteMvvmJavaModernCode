package com.eclatsol.mynotemvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        }, 3000);
        //Direct new lagavi intent no object banavelo che tene kahvi shavi anonymous object java no concept che
        //Te memory ne dereference kari de che //Dereference no matlab thay che memory ne release kari de che
        //Issue tyare ave che ek activity mathi biji activity ma data ne send karvi shavi
    }
}