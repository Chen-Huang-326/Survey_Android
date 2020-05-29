package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PollSurvey extends AppCompatActivity {
    TextView surTitle;
    TextView surDueDate;

    private ArrayList<String[]> queries = new ArrayList<>();
    private ArrayList<String> qc_pair = new ArrayList<>();
    // private ArrayList<String> questions;
    private ArrayList<String[]> basic_info = new ArrayList<>();
    private LinearLayout layout;
    private ArrayList<ArrayList<CheckBox>> cb_choice = new ArrayList<>(); // Store the check box
    private ArrayList<Integer> lim_num = new ArrayList<>(); // Store the maximum selection number
    private ArrayList<EditText> open_answer = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_poll_survey);

        //basic_info = DBUtils.getSurveyInfo(); // require to revert
        Intent intent = getIntent();
        String id = intent.getStringExtra("survey id");
        int index = Integer.parseInt(id);

        //temp
        ArrayList<String[]> surveys = new ArrayList<>();
        for (int i= 10; i <20; i++ ){
            String [] tmp = new String[2];
            tmp[0] = "test" + i;
            tmp[1] = i+ "/07/2020";
            surveys.add(tmp);
        }
        basic_info.addAll(surveys);
        //temp
        String [] info = basic_info.get(index);

        String title = info[0];
        String due_date = info[1];
        /*
        String username = info[0];
        String title = info[1];
        String due_date = info[2];
        */ // temp hide
        /*
        String question = info[3];
        String question_type = info[4];
        int choiceNum = Integer.parseInt(info[5]);
        int limitedNum = Integer.parseInt(info[6]);
        */
        //queries.addAll(DBUtils.getQueryInfo(username,title));

        surTitle = (TextView)findViewById(R.id.sur_title);
        surDueDate = (TextView)findViewById(R.id.sur_due_date);

        surTitle.setText(title);
        surDueDate.setText(due_date);

        surveyConstructor();
    }

    public void surveyConstructor (){
        TempTest();

        layout = (LinearLayout)findViewById(R.id.sur_que_view);
        for (String [] q: queries) System.out.println(q[1]);

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
                answer.setHint("Enter your answer");
                answer.setId(i);
                answer.setTextSize(16);

                open_answer.add(answer);

                layout.addView(openEnd);
                layout.addView(answer);
            } if (type.equals("multiple")){
                //ArrayList<String> choices = DBUtils.getChoicesInfo(question);
                lim_num.add(Integer.parseInt(limitedNum));
                ArrayList<String> choices = qc_pair;
                TextView multiple = new TextView(this);
                multiple.setText(question);
                multiple.setId(i);
                multiple.setTextSize(16);

                layout.addView(multiple);

                ArrayList<CheckBox> choiceView = new ArrayList<>();

                for (int a = 1; a < choices.size(); a++){
                    CheckBox option = new CheckBox(this);
                    String choice = choices.get(a);
                    option.setText(choice);
                    option.setId((i+1) * 10000 + a);
                    option.setTextSize(16);

                    choiceView.add(option);

                    layout.addView(option);
                }

                cb_choice.add(choiceView);

            }
        }
    }

    public void BackToFindSurvey (View v){ finish();}

    public void SurveySubmitForResult (View v){
        ArrayList<Integer[]> clickedInfo = new ArrayList<>();
        for (int i = 0; i < lim_num.size(); i++){
            int limNum = lim_num.get(i); // the maximum selection number
            ArrayList<CheckBox> current = cb_choice.get(i);
            int count = 0; // count the number of selected check box
            for (CheckBox checkBox: current){
                if (checkBox.isChecked()) count ++;
            }

            int questionNum = (int)((current.get(0).getId())/10000);

            Integer [] info = new Integer[3];
            info[0] = limNum;
            info[1] = count;
            info[2] = questionNum;

            clickedInfo.add(info);
        }

        boolean flag = Overflowed(clickedInfo);

        if (!flag){
            ArrayList<Integer> findQuestionNum = findIncorrectNum(clickedInfo);
            String q = "";
            for (int i = 0; i < findQuestionNum.size(); i++){
                if (i == findQuestionNum.size() - 1) q += findQuestionNum.get(i);
                else q += findQuestionNum.get(i) + ", ";
            }
            Toast.makeText(this,"(Q"+q+")"+"Selection over the requirement!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Submit success!", Toast.LENGTH_SHORT).show();

            // debug
            //TODO: require push the result to the server
            ArrayList<String[]> result = getResult();
            for (String [] a :result){
                String q = a [0];
                String an = a[1];
                System.out.println(q + ", " +an);
            }

            Intent intent = new Intent();
            setResult(RESULT_OK,intent);
            finish();
        }
    }

    public boolean Overflowed (ArrayList<Integer[]> clickedInfo){
        boolean flag = true;
        for (Integer [] info : clickedInfo){
            int lim = info[0];
            int count = info[1];
            if (lim < count) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public ArrayList<Integer> findIncorrectNum(ArrayList<Integer[]> clickedInfo){
        ArrayList<Integer> findNum = new ArrayList<>();
        for (Integer [] info : clickedInfo) {
            int qNum = info[2];
            findNum.add(qNum);
        }
        return findNum;
    }

    /**
     * Store the result data as the below pattern
     *
     * Q1: multiple question with 3 choices and 2 limitation number
     * Q2: description
     * Q3: open-end
     *
     * (no order)
     * questionNum    answer
     * 1              2
     * 1              3
     * 3              open-end answer
     *
     * or
     * questionNum    answer
     * 3              open-end answer
     * 1              2
     * 1              3
     *
     * @return result
     */
    public ArrayList<String[]> getResult (){
        ArrayList<String[]> result = new ArrayList<>();

        for (int i = 0 ; i < open_answer.size(); i++){
            EditText answer = open_answer.get(i);
            int quNum = (answer.getId()) + 1;
            String content = answer.getText().toString();
            String [] pair = new String[2];
            pair[0] = quNum + "";
            pair[1] = content;

            result.add(pair);
        }

        for (int a = 0; a< cb_choice.size(); a++) {
            ArrayList<CheckBox> current = cb_choice.get(a);
            int quNum = (int) ((current.get(0).getId()) / 10000);
            for (int b = 0; b < current.size(); b++) {
                if (current.get(b).isChecked()){
                    int id = b + 1;
                    System.out.println(id);
                    String [] tmp = new String[2];
                    tmp[0] = quNum + "";
                    tmp[1] = id + "";
                    result.add(tmp);
                }
            }
        }


        return result;
    }


    // To construct the template
    public void TempTest(){
        String [] q1 = {"q1","description", "0", "0"};
        String [] q2 = {"q2","multiple", "3","1"};
        String [] q3 = {"q3", "text", "0" ,"0"};
        queries.add(q1);
        queries.add(q2);
        queries.add(q3);

        String q = "q2";
        String c1 = "a1";
        String c2 = "a2";
        String c3 = "a3";
        qc_pair.add(q);
        qc_pair.add(c1);
        qc_pair.add(c2);
        qc_pair.add(c3);
    }
}
