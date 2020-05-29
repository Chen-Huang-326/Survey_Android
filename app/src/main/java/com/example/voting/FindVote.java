package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.voting.ui.home.HomeFragment;

import java.util.ArrayList;


/**
 * VOTE
 *
 * This activity aims to create page to visualize the available votes for the customers
 *
 * @author: Chen Huang
 * @Uid: u6735118
 */
public class FindVote extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter my_adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_find_vote);

        initData();
        initView();

    }
    public void clickFindVoteBack(View v){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    /**
     * Create the adapter to generate the list views in the page
     * In this page, each view represents a vote including information about vote title and vote due date;
     *
     * In addition, create the click function to enter into the pollVote page to participate in a vote;
     * The intent will transform the id of the vote to the next page, which enables the next activity to find the corresponding contents of a particular vote;
     */
    private void initData (){
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        my_adapter = new MyAdapter(DBUtils.getTitleDueInfo());
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

    /**
     * Visualize the views
     */
    private void initView (){
        recyclerView = (RecyclerView) findViewById(R.id.RV_vote_table);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(my_adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }



}

