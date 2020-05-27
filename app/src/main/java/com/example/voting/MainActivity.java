package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void registerActivityButton(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void loginActivityButton(View view){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void visitorActivityButton(View view){
        Intent intent = new Intent(this, Visitor.class);
        startActivity(intent);
    }
}
