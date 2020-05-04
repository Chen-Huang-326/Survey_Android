package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Question_multiple extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multiple);
        
    }

    public void MulBack (View v){
        finish();
    }

    public void MulNext (View v){
        Intent intent = new Intent(Question_multiple.this, Multiple_detail.class);
        Bundle bd = new Bundle();
        bd.putInt("mul_choice_number",1);
        startActivity(intent);
    }


}
