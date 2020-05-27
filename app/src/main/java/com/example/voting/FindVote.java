package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class FindVote extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter my_adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_vote);

        initData();
        initView();
    }

    private void initData (){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        my_adapter = new MyAdapter(getData());
        my_adapter.setOnItemClick(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position, String id) {
                Intent intent = new Intent(FindVote.this, pollVote.class);
                intent.putExtra("vote id",id);
                startActivity(intent);
            }
        });

    }

    private void initView (){
        recyclerView = (RecyclerView) findViewById(R.id.RV_vote_table);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(my_adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }


    // TODO: Use this method to collect the data from sever for vote (Haven't completed)
    private ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 20; i++) {
            data.add(i + temp);
        }

        return data;
    }
}

