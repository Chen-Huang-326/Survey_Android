package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TypeSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);
    }

    public void clickSelectBack(View v){
        finish();
    }
    public void clickSurvey(View v){
        Intent intent = new Intent(this, Survey.class);
        startActivity(intent);
    }
    public void clickVoting(View v){
        Intent intent = new Intent(this,Vote.class);
        startActivity(intent);
    }
}
