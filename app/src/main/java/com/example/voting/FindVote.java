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
        my_adapter = new MyAdapter(DBUtils.getTitleInfo());
        my_adapter.setOnItemClick(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position, String id) {
                ArrayList<String> titleInfo = DBUtils.getTitleInfo();
                Intent intent = new Intent(FindVote.this, PollVote.class);
                intent.putExtra("vote id",id);
                intent.putExtra("titleInfo", titleInfo);
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



}

