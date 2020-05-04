package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void createActivityButton(View view) {
        Intent intent = new Intent(this, Create.class);
        startActivity(intent);
    }

    public void voteActivityButton(View view){
        Intent intent = new Intent(this, Vote.class);
        startActivity(intent);
    }

    public void logoutActivityButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
