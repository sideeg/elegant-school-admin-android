package com.sideeg.elegant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.sideeg.elegant.R;

public class SplashActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar);
        progressBar.postDelayed(runnable,3000);
    }
}
