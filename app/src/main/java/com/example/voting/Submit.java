package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Submit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
    }

    public void submitToVoteActivityButton(View view){
        Intent intent = new Intent(this, Vote.class);
        startActivity(intent);
    }

    public void submitToCreateActivityButton(View view){
        Intent intent = new Intent(this, Create.class);
        startActivity(intent);
    }

    public void submitToAccountActivityButton(View view){
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

    public void submitLogoutButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
