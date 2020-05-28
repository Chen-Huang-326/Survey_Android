package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

public class PollSurvey extends AppCompatActivity {
    TextView surTitle;
    TextView surDueDate;

    private ArrayList <String[]> queries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_poll_survey);

        Intent intent = getIntent();
        String id = intent.getStringExtra("survey id");
        int index = Integer.parseInt(id);

        for (int i = 0; i < 20; i++ ){
            String [] tmp = {"title" + i ,"due" + i};
            queries.add(tmp);
        }

        String[] td = queries.get(index);
        String title = td[0];
        String due_date = td[1];

        surTitle = (TextView)findViewById(R.id.sur_title);
        surDueDate = (TextView)findViewById(R.id.sur_due_date);

        surTitle.setText(title);
        surDueDate.setText(due_date);
    }
}
