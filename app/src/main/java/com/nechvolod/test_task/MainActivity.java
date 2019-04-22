package com.nechvolod.test_task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View easySplashScreenView = new EasySplashScreen(MainActivity.this)
                .withFullScreen()
                .withTargetActivity(ListActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundResource(android.R.color.darker_gray)
                .withLogo(R.mipmap.rick_and_morty)
                .create();
        setContentView(easySplashScreenView);
    }
}
