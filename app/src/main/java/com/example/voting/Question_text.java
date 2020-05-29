package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

/**
 * SURVEY
 *
 * This activity aims to generate an open-end question in the survey;
 * Customers are able to use the function to generate an open-end question which enables participants to give a random answer;
 *
 * @author: Chen Huang
 * @Uid: u6735118
 */
public class Question_text extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_question_text);
    }

    public void TextBack (View v){
        finish();
    }

    public void TextSubmit (View v){
        Intent intent = new Intent();
        EditText inputText = (EditText)findViewById(R.id.text_que_content);
        intent.putExtra("text_question", inputText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }

}
