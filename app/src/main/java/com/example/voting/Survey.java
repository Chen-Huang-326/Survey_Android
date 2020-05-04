package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Survey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
    }

    /**
     * Set the function of "create_query" button;
     * Click to generate a menu to choose the question type
     * question types include "Multiple choice" "Paragraph" "Text (Open question)"
     * @param v
     */
    //TODO: task 1 - Adding/deleting an topic with a set of options
    public void QuestionOption (View v){
        PopupMenu popupMenu = new PopupMenu(Survey.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.question_type,popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.description:
                        Intent intentI = new Intent(Survey.this, Question_description.class);
                        startActivityForResult(intentI,2);
                        break;
                    case R.id.multiple_choice:
                        Intent intentII = new Intent(Survey.this, Question_multiple.class);
                        startActivityForResult(intentII,3);
                        break;
                    case R.id.open_question:
                        Intent intentIII = new Intent(Survey.this, Question_text.class);
                        startActivityForResult(intentIII,4);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.question_type, menu);
        return true;
    }

    /**
     *  Set the function of "back" button;
     *  @param v
     */
    public void Back (View v){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    /**
     * Set the function to edit the survey topic title;
     * @param v
     */
    public void TitleEdit (View v){
        Intent intent = new Intent(Survey.this, CreateTitle.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                TextView title = (TextView)findViewById(R.id.survey_title_editer);
                title.setText(data.getStringExtra("input"));
            }
            if (requestCode == 2){
                TextView query = (TextView)findViewById(R.id.query_content);
                query.setText(data.getStringExtra("description"));
            }
        }
    }

}
