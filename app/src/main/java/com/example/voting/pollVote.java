package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class pollVote extends AppCompatActivity {
    TextView votingTitle;
    TextView votingDeadline;
    TextView option3;
    TextView option4;
    TextView option5;
    private ArrayList<String[]> vote_queries = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_poll_vote);

        Intent intent = getIntent();
        String id = intent.getStringExtra("vote id");
        int index = Integer.parseInt(id);

        for (int i = 0; i < 20; i++ ){
            String [] tmp = {"title" + i ,"due" + i};
            vote_queries.add(tmp);
        }

        String[] td = vote_queries.get(index);
        String title = td[0];
        String due_date = td[1];



        votingTitle = (TextView)findViewById(R.id.voting_title_in_find);
        votingDeadline = (TextView)findViewById(R.id.deadline);

        votingTitle.setText(title);
        votingDeadline.setText(due_date);

    }

    public void backInFind(View v){
        Intent intent = new Intent(this, FindVote.class);
        startActivity(intent);
    }



    boolean flag1=false;
    boolean flag2=false;
    boolean flag3=false;
    boolean flag4=false;
    boolean flag5=false;
    public void clickOption1(View v){
        flag1=true;
        flag2=false;
        flag3=false;
        flag4=false;
        flag5=false;
        TextView option1 = findViewById(R.id.option_one_in_find);
        Toast.makeText(this,"You choose"+option1.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption2(View v){
        flag1=false;
        flag2=true;
        flag3=false;
        flag4=false;
        flag5=false;
        TextView option2 = findViewById(R.id.option_two_in_find);
        Toast.makeText(this,"You choose"+option2.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption3(View v){
        flag1=false;
        flag2=false;
        flag3=true;
        flag4=false;
        flag5=false;
        TextView option3 = findViewById(R.id.option_three_in_find);
        Toast.makeText(this,"You choose"+option3.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption4(View v){
        flag1=false;
        flag2=false;
        flag3=false;
        flag4=true;
        flag5=false;
        TextView option4 = findViewById(R.id.option_four_in_find);
        Toast.makeText(this,"You choose"+option4.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption5(View v){
        flag1=false;
        flag2=false;
        flag3=false;
        flag4=false;
        flag5=true;
        TextView option5 = findViewById(R.id.option_five_in_find);
        Toast.makeText(this,"You choose"+option5.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }

}
