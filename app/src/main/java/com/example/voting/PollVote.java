package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PollVote extends AppCompatActivity {
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
        ArrayList<String> titleInfo = intent.getStringArrayListExtra("titleInfo");
        int index = Integer.parseInt(id);

        for (int i = 0; i <titleInfo.size(); i++ ){
            String [] tmp = {titleInfo.get(i) ,DBUtils.getDueInfo(titleInfo.get(i))};
            vote_queries.add(tmp);
        }

        String[] td = vote_queries.get(index);
        String title = td[0];
        String due_date = td[1];


        votingTitle = (TextView)findViewById(R.id.voting_title_in_find);
        votingDeadline = (TextView)findViewById(R.id.deadline);

        votingTitle.setText(title);
        votingDeadline.setText(due_date);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDate = formatter.format(calendar.getTime());
        String dDate = due_date+" 23:59:59";
        try {
            Date curDate = formatter.parse(currentDate);
            Date date= formatter.parse(dDate);
            if(curDate.getTime()>date.getTime()){
                showResult();
                Toast.makeText(PollVote.this,"overdue",Toast.LENGTH_SHORT).show();
            }else{
                ArrayList<String> choice = DBUtils.getChoice(title);
                ArrayList<String> choiceAmount = new ArrayList<>();
                for (int i = 0; i < choice.size(); i++) {
                    String answer = choice.get(i);
                    if(answer!=null){
                        choiceAmount.add(answer);
                    }
                }
                TextView choice1 = findViewById(R.id.option_one_in_find);
                TextView choice2 = findViewById(R.id.option_two_in_find);
                TextView choice3 = findViewById(R.id.option_three_in_find);
                TextView choice4 = findViewById(R.id.option_four_in_find);
                TextView choice5 = findViewById(R.id.option_five_in_find);
                switch (choiceAmount.size()){
                    case 2:
                        choice1.setText(choiceAmount.get(0));
                        choice2.setText(choiceAmount.get(1));
                        break;
                    case 3:
                        choice1.setText(choiceAmount.get(0));
                        choice2.setText(choiceAmount.get(1));
                        choice3.setVisibility(View.VISIBLE);
                        choice3.setText(choiceAmount.get(2));
                        break;
                    case 4:
                        choice1.setText(choiceAmount.get(0));
                        choice2.setText(choiceAmount.get(1));
                        choice3.setVisibility(View.VISIBLE);
                        choice3.setText(choiceAmount.get(2));
                        choice4.setVisibility(View.VISIBLE);
                        choice4.setText(choiceAmount.get(3));
                        break;
                    case 5:
                        choice1.setText(choiceAmount.get(0));
                        choice2.setText(choiceAmount.get(1));
                        choice3.setVisibility(View.VISIBLE);
                        choice3.setText(choiceAmount.get(2));
                        choice4.setVisibility(View.VISIBLE);
                        choice4.setText(choiceAmount.get(3));
                        choice5.setVisibility(View.VISIBLE);
                        choice5.setText(choiceAmount.get(4));
                        break;
                    default:
                        break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    boolean flag1;
    boolean flag2;
    boolean flag3;
    boolean flag4;
    boolean flag5;
    public void clickOption1(View v){
        flag1=true;
        flag2=false;
        flag3=false;
        flag4=false;
        flag5=false;
        TextView option1 = findViewById(R.id.option_one_in_find);
        TextView option2 = findViewById(R.id.option_two_in_find);
        TextView option3 = findViewById(R.id.option_three_in_find);
        TextView option4 = findViewById(R.id.option_four_in_find);
        TextView option5 = findViewById(R.id.option_five_in_find);
        option1.setBackground(ContextCompat.getDrawable(this,R.drawable.main_button_drawable));
        option2.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option3.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option4.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option5.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        Toast.makeText(this,"You choose "+option1.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption2(View v){
        flag1=false;
        flag2=true;
        flag3=false;
        flag4=false;
        flag5=false;
        TextView option1 = findViewById(R.id.option_one_in_find);
        TextView option2 = findViewById(R.id.option_two_in_find);
        TextView option3 = findViewById(R.id.option_three_in_find);
        TextView option4 = findViewById(R.id.option_four_in_find);
        TextView option5 = findViewById(R.id.option_five_in_find);
        option2.setBackground(ContextCompat.getDrawable(this,R.drawable.main_button_drawable));
        option1.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option3.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option4.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option5.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        Toast.makeText(this,"You choose "+option2.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption3(View v){
        flag1=false;
        flag2=false;
        flag3=true;
        flag4=false;
        flag5=false;
        TextView option1 = findViewById(R.id.option_one_in_find);
        TextView option2 = findViewById(R.id.option_two_in_find);
        TextView option3 = findViewById(R.id.option_three_in_find);
        TextView option4 = findViewById(R.id.option_four_in_find);
        TextView option5 = findViewById(R.id.option_five_in_find);
        option3.setBackground(ContextCompat.getDrawable(this,R.drawable.main_button_drawable));
        option2.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option1.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option4.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option5.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        Toast.makeText(this,"You choose "+option3.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption4(View v){
        flag1=false;
        flag2=false;
        flag3=false;
        flag4=true;
        flag5=false;
        TextView option1 = findViewById(R.id.option_one_in_find);
        TextView option2 = findViewById(R.id.option_two_in_find);
        TextView option3 = findViewById(R.id.option_three_in_find);
        TextView option4 = findViewById(R.id.option_four_in_find);
        TextView option5 = findViewById(R.id.option_five_in_find);
        option4.setBackground(ContextCompat.getDrawable(this,R.drawable.main_button_drawable));
        option2.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option3.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option1.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option5.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        Toast.makeText(this,"You choose "+option4.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }
    public void clickOption5(View v){
        flag1=false;
        flag2=false;
        flag3=false;
        flag4=false;
        flag5=true;
        TextView option1 = findViewById(R.id.option_one_in_find);
        TextView option2 = findViewById(R.id.option_two_in_find);
        TextView option3 = findViewById(R.id.option_three_in_find);
        TextView option4 = findViewById(R.id.option_four_in_find);
        TextView option5 = findViewById(R.id.option_five_in_find);
        option5.setBackground(ContextCompat.getDrawable(this,R.drawable.main_button_drawable));
        option2.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option3.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option4.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        option1.setBackground(ContextCompat.getDrawable(this,R.drawable.survey_button_drawable));
        Toast.makeText(this,"You choose "+option5.getText().toString()+"!", Toast.LENGTH_SHORT).show();
    }

    public void backInFind(View v){
        Intent intent = new Intent(this, FindVote.class);
        startActivity(intent);
    }
    public void showResult(){
        TextView questionTitle = findViewById(R.id.voting_title_in_find);
        String title = questionTitle.getText().toString();
        ArrayList<String[]> response = DBUtils.countResult(title);
        TextView choice1 = findViewById(R.id.option_one_in_find);
        TextView choice2 = findViewById(R.id.option_two_in_find);
        TextView choice3 = findViewById(R.id.option_three_in_find);
        TextView choice4 = findViewById(R.id.option_four_in_find);
        TextView choice5 = findViewById(R.id.option_five_in_find);
        switch (response.size()){
            case 2:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                break;
            case 3:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                choice3.setVisibility(View.VISIBLE);
                choice3.setText(response.get(2)[0]+"("+response.get(2)[1]+")");
                break;
            case 4:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                choice3.setVisibility(View.VISIBLE);
                choice3.setText(response.get(2)[0]+"("+response.get(2)[1]+")");
                choice4.setVisibility(View.VISIBLE);
                choice4.setText(response.get(3)[0]+"("+response.get(3)[1]+")");
                break;
            case 5:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                choice3.setVisibility(View.VISIBLE);
                choice3.setText(response.get(2)[0]+"("+response.get(2)[1]+")");
                choice4.setVisibility(View.VISIBLE);
                choice4.setText(response.get(3)[0]+"("+response.get(3)[1]+")");
                choice5.setVisibility(View.VISIBLE);
                choice5.setText(response.get(4)[0]+"("+response.get(4)[1]+")");
                break;
            default:
                break;
        }
        choice1.setClickable(false);
        choice2.setClickable(false);
        choice3.setClickable(false);
        choice4.setClickable(false);
        choice5.setClickable(false);
        findViewById(R.id.voting_result_submit_in_find).setVisibility(View.INVISIBLE);
    }

    public void confirmAndVote(View view){
        TextView questionTitle = findViewById(R.id.voting_title_in_find);
        String title = questionTitle.getText().toString();
        boolean result=false;
        if(flag1){
            result = DBUtils.count(title,"1");
        }
        if(flag2){
            result = DBUtils.count(title,"2");
        }
        if(flag3){
            result = DBUtils.count(title,"3");
        }
        if(flag4){
            result = DBUtils.count(title,"4");
        }
        if(flag5){
            result = DBUtils.count(title,"5");
        }
        if(result){
            Toast.makeText(PollVote.this,"success",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(PollVote.this,"failed",Toast.LENGTH_SHORT).show();
        }

        ArrayList<String[]> response = DBUtils.countResult(title);
        TextView choice1 = findViewById(R.id.option_one_in_find);
        TextView choice2 = findViewById(R.id.option_two_in_find);
        TextView choice3 = findViewById(R.id.option_three_in_find);
        TextView choice4 = findViewById(R.id.option_four_in_find);
        TextView choice5 = findViewById(R.id.option_five_in_find);
        switch (response.size()){
            case 2:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                break;
            case 3:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                choice3.setVisibility(View.VISIBLE);
                choice3.setText(response.get(2)[0]+"("+response.get(2)[1]+")");
                break;
            case 4:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                choice3.setVisibility(View.VISIBLE);
                choice3.setText(response.get(2)[0]+"("+response.get(2)[1]+")");
                choice4.setVisibility(View.VISIBLE);
                choice4.setText(response.get(3)[0]+"("+response.get(3)[1]+")");
                break;
            case 5:
                choice1.setText(response.get(0)[0]+"("+response.get(0)[1]+")");
                choice2.setText(response.get(1)[0]+"("+response.get(1)[1]+")");
                choice3.setVisibility(View.VISIBLE);
                choice3.setText(response.get(2)[0]+"("+response.get(2)[1]+")");
                choice4.setVisibility(View.VISIBLE);
                choice4.setText(response.get(3)[0]+"("+response.get(3)[1]+")");
                choice5.setVisibility(View.VISIBLE);
                choice5.setText(response.get(4)[0]+"("+response.get(4)[1]+")");
                break;
            default:
                break;
        }
        choice1.setClickable(false);
        choice2.setClickable(false);
        choice3.setClickable(false);
        choice4.setClickable(false);
        choice5.setClickable(false);
        findViewById(R.id.voting_result_submit_in_find).setVisibility(View.INVISIBLE);

    }
}
