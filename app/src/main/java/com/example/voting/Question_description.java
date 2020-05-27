package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class Question_description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_question_description);
    }

    public void DescriptionBack (View v){
        finish();
    }

    public void DescriptionSubmit (View v){
        Intent intent = new Intent();
        EditText inputText = (EditText)findViewById(R.id.question_content);
        intent.putExtra("description", inputText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
