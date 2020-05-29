package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PollSurvey extends AppCompatActivity {
    TextView surTitle;
    TextView surDueDate;

    private ArrayList<String[]> queries;
    // private ArrayList<String> questions;
    private ArrayList<String[]> basic_info = new ArrayList<>();
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_poll_survey);

        basic_info = DBUtils.getSurveyInfo();
        Intent intent = getIntent();
        String id = intent.getStringExtra("survey id");
        int index = Integer.parseInt(id);

        String [] info = basic_info.get(index);

        String username = info[0];
        String title = info[1];
        String due_date = info[2];
        /*
        String question = info[3];
        String question_type = info[4];
        int choiceNum = Integer.parseInt(info[5]);
        int limitedNum = Integer.parseInt(info[6]);
        */
        queries = DBUtils.getQueryInfo(username,title);

        surTitle = (TextView)findViewById(R.id.sur_title);
        surDueDate = (TextView)findViewById(R.id.sur_due_date);

        surTitle.setText(title);
        surDueDate.setText(due_date);

        surveyConstructor();
    }

    public void surveyConstructor (){
        layout = (LinearLayout)findViewById(R.id.sur_que_view);

        for (int i = 0; i < queries.size(); i++){
            String[] query = queries.get(i);
            String question = query[0];
            String type = query[1];
            String choiceNum = query[2];
            String limitedNum = query[3];


            if (type.equals("description")) {
                TextView description = new TextView(this);
                description.setText(question);
                description.setId(i);
                description.setTextSize(16);
                layout.addView(description);
            } if (type.equals("text")) {
                TextView openEnd = new TextView(this);
                openEnd.setText(question);
                openEnd.setId(i);
                openEnd.setTextSize(16);

                EditText answer = new EditText(this);
                answer.setText("Enter your answer");
                answer.setId(i);
                answer.setTextSize(16);

                layout.addView(openEnd);
                layout.addView(answer);
            } if (type.equals("multiple")){
                ArrayList<String> choices = DBUtils.getChoicesInfo(question);
                TextView multiple = new TextView(this);
                multiple.setText(question);
                multiple.setId(i);
                multiple.setTextSize(16);

                layout.addView(multiple);

                for (int a = 0; a < choices.size(); a++){
                    CheckBox option = new CheckBox(this);
                    String choice = choices.get(a);
                    option.setText(choice);
                    option.setId(i * 10 + a);
                    option.setTextSize(16);

                    layout.addView(option);
                }

            }
        }
    }
}
