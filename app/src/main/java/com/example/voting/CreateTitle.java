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
 * This activity aims to create a title for a survey
 *
 * @author: Chen Huang
 * @Uid: u6735118
 */
public class CreateTitle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_create_title);
    }

    public void TitleBack (View v){
        finish();
    }

    public void TitleSubmit (View v){
        Intent intent = new Intent();
        EditText inputText = (EditText)findViewById(R.id.title_input);
        intent.putExtra("input", inputText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}
