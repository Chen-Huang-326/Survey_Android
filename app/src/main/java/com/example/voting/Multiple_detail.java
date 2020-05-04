package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class Multiple_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_detail);
/**
        LinearLayout linearLayout = findViewById(R.id.mul_view);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        int choice_number = bd.getInt("mul_choice_number");

        while (choice_number > 0) {
            String choice_name = "Choice" + choice_number;
            EditText editText = new EditText(this);
            editText.setText(choice_name);
            linearLayout.addView(editText);
        }*/
    }
}
