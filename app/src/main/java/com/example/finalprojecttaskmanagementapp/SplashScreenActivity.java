package com.example.finalprojecttaskmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Define the delay in milliseconds
        int splashScreenDuration = 3000; // 3 seconds

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity
                Intent mainIntent = new Intent(SplashScreenActivity.this, SignUpActivity.class);
                startActivity(mainIntent);
                finish(); // Close the splash screen activity
            }
        }, splashScreenDuration);
    }
}