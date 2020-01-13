package com.sideeg.elegant.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sideeg.elegant.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sign(View view) {
        EditText user = findViewById(R.id.usrusr);
        EditText pass = findViewById(R.id.pswrd);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        if (user.getText().toString().equals("admin")&&pass.getText().toString().equals("123")){

            intent.putExtra("admin","yes");
        }

        startActivity(intent);
    }
}
