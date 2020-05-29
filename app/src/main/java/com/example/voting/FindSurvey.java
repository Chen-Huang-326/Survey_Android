package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;

public class FindSurvey extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter my_adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_find_survey);

        initData();
        initView();
    }

    private void initData (){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        my_adapter = new MyAdapter(getData());
        my_adapter.setOnItemClick(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position, String id) {
                Intent intent = new Intent(FindSurvey.this, PollSurvey.class);
                intent.putExtra("survey id",id);
                startActivity(intent);
            }
        });

    }

    private void initView (){
        recyclerView = (RecyclerView) findViewById(R.id.RV_sur_table);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(my_adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private ArrayList<String[]> getData(){
        /*
        ArrayList<String[]> survey = DBUtils.getSurveyInfo();
        ArrayList<String[]> surveys = new ArrayList<>();
        for (String[] s: survey){
            String [] tmp = new String[2];
            tmp[0] = s[1];
            tmp[1] = s[2];
            surveys.add(tmp);
            System.out.println(s[1]);
        }
        return surveys;
         */

        ArrayList<String[]> surveys = new ArrayList<>();
        for (int i= 10; i <20; i++ ){
            String [] tmp = new String[2];
            tmp[0] = "test" + i;
            tmp[1] = i+ "/07/2020";
            surveys.add(tmp);
        }
        return surveys;
    }
}
