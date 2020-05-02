package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void Back (View v){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    public void TitleEdit (View v){
        Intent intent = new Intent(Edit.this, CreateTitle.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK){
            TextView title = (TextView)findViewById(R.id.survey_title_editer);
            title.setText(data.getStringExtra("input"));
        }
    }
}
