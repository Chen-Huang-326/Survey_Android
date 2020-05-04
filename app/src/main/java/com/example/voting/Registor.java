package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Registor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);
    }

    public void confirmRegisterActivityButton(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
