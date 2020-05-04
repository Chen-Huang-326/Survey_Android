package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void postToVoteActivityButton(View view){
        Intent intent = new Intent(this, Vote.class);
        startActivity(intent);
    }

    public void postToCreateActivityButton(View view){
        Intent intent = new Intent(this, Create.class);
        startActivity(intent);
    }

    public void postToAccountActivityButton(View view){
        Intent intent = new Intent(this, Account.class);
        startActivity(intent);
    }

    public void postLogoutButton(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
