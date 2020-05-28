package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Vote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_vote);
    }


    /**
     *  Set the function of "back" button;
     *  @param v
     */
    public void clickVoteBack (View v){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }
    public void clickTitleRemove (View v){
        EditText edit_title = findViewById(R.id.vote_title_edit);
        edit_title.setText("");
    }
    public void clickAddOption3(View v){
        final EditText optionThree = findViewById(R.id.option_three);
        Button addOption3 = findViewById(R.id.add_option3);
        ImageButton removeOption3 = findViewById(R.id.remove_option3);
        Button addOption4 = findViewById(R.id.add_option4);
        addOption3.setVisibility(View.INVISIBLE);
        optionThree.setVisibility(View.VISIBLE);
        removeOption3.setVisibility(View.VISIBLE);
        addOption4.setVisibility(View.VISIBLE);

    }
    public void clickRemoveOption3(View v){
        final EditText optionThree = findViewById(R.id.option_three);
        ImageButton removeOption3 = findViewById(R.id.remove_option3);
        Button addOption3 = findViewById(R.id.add_option3);
        Button addOption4 = findViewById(R.id.add_option4);

        final EditText optionFour = findViewById(R.id.option_four);
        final EditText optionFive = findViewById(R.id.option_five);
        ImageButton removeOption4 = findViewById(R.id.remove_option4);
        Button addOption5 = findViewById(R.id.add_option5);
        ImageButton removeOption5 = findViewById(R.id.remove_option5);

        if (optionFour.getVisibility()==View.INVISIBLE && optionFive.getVisibility()==View.INVISIBLE) {
            addOption3.setVisibility(View.VISIBLE);
            addOption4.setVisibility(View.INVISIBLE);
            optionThree.setText("");
            optionThree.setVisibility(View.INVISIBLE);
            removeOption3.setVisibility(View.INVISIBLE);
        }
        else if (optionFour.getVisibility()==View.VISIBLE && optionFive.getVisibility()==View.INVISIBLE){
            optionFour.setVisibility(View.INVISIBLE);
            removeOption4.setVisibility(View.INVISIBLE);
            optionThree.setText(optionFour.getText());
            optionFour.setText("");
            addOption4.setVisibility(View.VISIBLE);
            addOption5.setVisibility(View.INVISIBLE);
        }
        else if (optionFour.getVisibility()==View.VISIBLE && optionFive.getVisibility()==View.VISIBLE){
            optionFive.setVisibility(View.INVISIBLE);
            removeOption5.setVisibility(View.INVISIBLE);
            optionThree.setText(optionFour.getText());
            optionFour.setText(optionFive.getText());
            optionFive.setText("");
            addOption5.setVisibility(View.VISIBLE);
        }
    }

    public void clickAddOption4(View v){
        final EditText optionFour = findViewById(R.id.option_four);
        Button addOption4 = findViewById(R.id.add_option4);
        ImageButton removeOption4 = findViewById(R.id.remove_option4);
        Button addOption5 = findViewById(R.id.add_option5);
        addOption4.setVisibility(View.INVISIBLE);
        optionFour.setVisibility(View.VISIBLE);
        removeOption4.setVisibility(View.VISIBLE);
        addOption5.setVisibility(View.VISIBLE);

    }
    public void clickRemoveOption4(View v){
        final EditText optionFour = findViewById(R.id.option_four);
        final EditText optionFive = findViewById(R.id.option_five);
        ImageButton removeOption4 = findViewById(R.id.remove_option4);
        ImageButton removeOption5 = findViewById(R.id.remove_option5);
        Button addOption4 = findViewById(R.id.add_option4);
        Button addOption5 = findViewById(R.id.add_option5);
        if (optionFive.getVisibility()==View.INVISIBLE){
            addOption4.setVisibility(View.VISIBLE);
            addOption5.setVisibility(View.INVISIBLE);
            optionFour.setText("");
            optionFour.setVisibility(View.INVISIBLE);
            removeOption4.setVisibility(View.INVISIBLE);
        }
        else {
            optionFour.setText(optionFive.getText());
            optionFive.setText("");
            optionFive.setVisibility(View.INVISIBLE);
            removeOption5.setVisibility(View.INVISIBLE);
            addOption5.setVisibility(View.VISIBLE);
        }

    }
    public void clickAddOption5(View v){
        final EditText optionFive = findViewById(R.id.option_five);
        Button addOption5 = findViewById(R.id.add_option5);
        ImageButton removeOption5 = findViewById(R.id.remove_option5);
        addOption5.setVisibility(View.INVISIBLE);
        optionFive.setVisibility(View.VISIBLE);
        removeOption5.setVisibility(View.VISIBLE);

    }
    public void clickRemoveOption5(View v){
        final EditText optionFive = findViewById(R.id.option_five);
        ImageButton removeOption5 = findViewById(R.id.remove_option5);
        Button addOption5 = findViewById(R.id.add_option5);
        addOption5.setVisibility(View.VISIBLE);
        optionFive.setVisibility(View.INVISIBLE);
        optionFive.setText("");
        removeOption5.setVisibility(View.INVISIBLE);
    }


    /**
     * Set the function to edit the survey topic title;
     * @param v
     */
    public void showSavedVoting (View v){
        final EditText edit_title = findViewById(R.id.vote_title_edit);
        TextView saved_title = findViewById(R.id.saved_title);
        EditText edit_option_one = findViewById(R.id.option_one);
        TextView saved_option_one = findViewById(R.id.saved_option_one);
        EditText edit_option_two = findViewById(R.id.option_two);
        TextView saved_option_two = findViewById(R.id.saved_option_two);
        EditText edit_option_three = findViewById(R.id.option_three);
        TextView saved_option_three = findViewById(R.id.saved_option_three);
        EditText edit_option_four = findViewById(R.id.option_four);
        TextView saved_option_four = findViewById(R.id.saved_option_four);
        EditText edit_option_five = findViewById(R.id.option_five);
        TextView saved_option_five = findViewById(R.id.saved_option_five);
        Button add_option3_button = findViewById(R.id.add_option3);
        Button add_option4_button = findViewById(R.id.add_option4);
        Button add_option5_button = findViewById(R.id.add_option5);
        ImageButton remove_title = findViewById(R.id.remove_title);
        ImageButton remove_option3 = findViewById(R.id.remove_option3);
        ImageButton remove_option4 = findViewById(R.id.remove_option4);
        ImageButton remove_option5 = findViewById(R.id.remove_option5);
        Button save_button = findViewById(R.id.save_Voting_page);
        Button re_edit_button = findViewById(R.id.re_edit_voting);
        Button submit_button = findViewById(R.id.submit_voting);

        TextView saved_due_date = findViewById(R.id.saved_vote_due_date);
        EditText edit_due_date = findViewById(R.id.vote_due_date_edit);
        String due_date = edit_due_date.getText().toString();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String current_date = formatter.format(calendar.getTime()).substring(0,10);

        boolean dateValid = false;
        if (due_date.length() == 10 && due_date.charAt(2) == '/' && due_date.charAt(5) == '/') {
            int day = Integer.parseInt(due_date.substring(0, 2));
            int month = Integer.parseInt(due_date.substring(3, 5));
            int year = Integer.parseInt(due_date.substring(6));
            int current_day = Integer.parseInt(current_date.substring(0, 2));
            int current_month = Integer.parseInt(current_date.substring(3, 5));
            int current_year = Integer.parseInt(current_date.substring(6));
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                if (day > 0 && day <= 31) {
                    dateValid = true;
                }
            }
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 0 && day <= 30) {
                    dateValid = true;
                }
            }
            if (month == 2) {
                if (year % 4 == 0) {
                    if (day > 0 && day <= 29) {
                        dateValid = true;
                    }
                }
                if (year % 4 > 0) {
                    if (day > 0 && day <= 28) {
                        dateValid = true;
                    }
                }
            }
            if (year < current_year){
                dateValid = false;
            }
            if (year==current_year && month<current_month){
                dateValid = false;
            }
            if (year==current_year && month==current_month && day<current_day){
                dateValid = false;
            }

        } else {dateValid = false;}

        if (dateValid) {
            edit_due_date.setVisibility(View.INVISIBLE);
            saved_due_date.setVisibility(View.VISIBLE);
            saved_due_date.setText(edit_due_date.getText());

            edit_title.setVisibility(View.INVISIBLE);
            saved_title.setVisibility(View.VISIBLE);
            saved_title.setText(edit_title.getText());
            remove_title.setVisibility(View.INVISIBLE);

            edit_option_one.setVisibility(View.INVISIBLE);
            saved_option_one.setVisibility(View.VISIBLE);
            saved_option_one.setText(edit_option_one.getText());

            edit_option_two.setVisibility(View.INVISIBLE);
            saved_option_two.setVisibility(View.VISIBLE);
            saved_option_two.setText(edit_option_two.getText());
            add_option3_button.setVisibility(View.INVISIBLE);
            if (edit_option_three.getVisibility() == View.VISIBLE) {
                edit_option_three.setVisibility(View.INVISIBLE);
                saved_option_three.setVisibility(View.VISIBLE);
                saved_option_three.setText(edit_option_three.getText());
                add_option4_button.setVisibility(View.INVISIBLE);
                remove_option3.setVisibility(View.INVISIBLE);
            }
            if (edit_option_four.getVisibility() == View.VISIBLE) {
                edit_option_four.setVisibility(View.INVISIBLE);
                saved_option_four.setVisibility(View.VISIBLE);
                saved_option_four.setText(edit_option_four.getText());
                add_option5_button.setVisibility(View.INVISIBLE);
                remove_option4.setVisibility(View.INVISIBLE);
            }
            if (edit_option_five.getVisibility() == View.VISIBLE) {
                edit_option_five.setVisibility(View.INVISIBLE);
                saved_option_five.setVisibility(View.VISIBLE);
                saved_option_five.setText(edit_option_five.getText());
                remove_option5.setVisibility(View.INVISIBLE);
            }
            save_button.setVisibility(View.INVISIBLE);
            re_edit_button.setVisibility(View.VISIBLE);
            submit_button.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(this,"Invalid Deadline!", Toast.LENGTH_SHORT).show();
        }
    }

    public void re_editVoting(View v){
        final EditText edit_title = findViewById(R.id.vote_title_edit);
        TextView saved_title = findViewById(R.id.saved_title);
        EditText edit_option_one = findViewById(R.id.option_one);
        TextView saved_option_one = findViewById(R.id.saved_option_one);
        EditText edit_option_two = findViewById(R.id.option_two);
        TextView saved_option_two = findViewById(R.id.saved_option_two);
        EditText edit_option_three = findViewById(R.id.option_three);
        TextView saved_option_three = findViewById(R.id.saved_option_three);
        EditText edit_option_four = findViewById(R.id.option_four);
        TextView saved_option_four = findViewById(R.id.saved_option_four);
        EditText edit_option_five = findViewById(R.id.option_five);
        TextView saved_option_five = findViewById(R.id.saved_option_five);
        Button add_option3_button = findViewById(R.id.add_option3);
        Button add_option4_button = findViewById(R.id.add_option4);
        Button add_option5_button = findViewById(R.id.add_option5);
        ImageButton remove_title = findViewById(R.id.remove_title);
        ImageButton remove_option3 = findViewById(R.id.remove_option3);
        ImageButton remove_option4 = findViewById(R.id.remove_option4);
        ImageButton remove_option5 = findViewById(R.id.remove_option5);
        Button save_button = findViewById(R.id.save_Voting_page);
        Button re_edit_button = findViewById(R.id.re_edit_voting);
        Button submit_button = findViewById(R.id.submit_voting);


        TextView saved_due_date = findViewById(R.id.saved_vote_due_date);
        EditText edit_due_date = findViewById(R.id.vote_due_date_edit);

        edit_due_date.setVisibility(View.VISIBLE);
        saved_due_date.setVisibility(View.INVISIBLE);
        saved_due_date.setText("");

        edit_title.setVisibility(View.VISIBLE);
        saved_title.setVisibility(View.INVISIBLE);
        saved_title.setText("");
        remove_title.setVisibility(View.VISIBLE);

        edit_option_one.setVisibility(View.VISIBLE);
        saved_option_one.setVisibility(View.INVISIBLE);
        saved_option_one.setText("");

        edit_option_two.setVisibility(View.VISIBLE);
        saved_option_two.setVisibility(View.INVISIBLE);
        saved_option_two.setText("");

        if (saved_option_three.getVisibility()==View.INVISIBLE){
            add_option3_button.setVisibility(View.VISIBLE);
        }

        if (saved_option_three.getVisibility()==View.VISIBLE){
            edit_option_three.setVisibility(View.VISIBLE);
            saved_option_three.setVisibility(View.INVISIBLE);
            saved_option_three.setText("");
            remove_option3.setVisibility(View.VISIBLE);
            add_option4_button.setVisibility(View.VISIBLE);
        }
        if (saved_option_four.getVisibility()==View.VISIBLE){
            edit_option_four.setVisibility(View.VISIBLE);
            saved_option_four.setVisibility(View.INVISIBLE);
            saved_option_four.setText("");
            remove_option4.setVisibility(View.VISIBLE);
            add_option4_button.setVisibility(View.INVISIBLE);
            add_option5_button.setVisibility(View.VISIBLE);
        }
        if (saved_option_five.getVisibility()==View.VISIBLE){
            edit_option_five.setVisibility(View.VISIBLE);
            saved_option_five.setVisibility(View.INVISIBLE);
            saved_option_five.setText("");
            remove_option5.setVisibility(View.VISIBLE);
            add_option5_button.setVisibility(View.INVISIBLE);
        }


        save_button.setVisibility(View.VISIBLE);
        re_edit_button.setVisibility(View.INVISIBLE);
        submit_button.setVisibility(View.INVISIBLE);

    }

    public void create_vote_submit(View view){
        ArrayList<String> info = new ArrayList<>();
        TextView savedTitle = findViewById(R.id.saved_title);
        TextView savedOptionOne = findViewById(R.id.saved_option_one);
        TextView savedOptionTwo = findViewById(R.id.saved_option_two);
        TextView savedDueDate = findViewById(R.id.vote_due_date_edit);

        info.add(savedTitle.getText().toString());
        info.add(savedOptionOne.getText().toString());
        info.add(savedOptionTwo.getText().toString());

        if(findViewById(R.id.saved_option_three).getVisibility() == View.VISIBLE){
            TextView savedOptionThree = findViewById(R.id.saved_option_three);
            info.add(savedOptionThree.getText().toString());
        }
        if(findViewById(R.id.saved_option_four).getVisibility() == View.VISIBLE){
            TextView savedOptionFour = findViewById(R.id.saved_option_four);
            info.add(savedOptionFour.getText().toString());
        }
        if(findViewById(R.id.saved_option_five).getVisibility() == View.VISIBLE){
            TextView savedOptionFive = findViewById(R.id.saved_option_five);
            info.add(savedOptionFive.getText().toString());
        }
        info.add(savedDueDate.getText().toString());
        boolean flag = DBUtils.submitVote(info);
        if(flag){
            Toast.makeText(Vote.this,"success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Vote.this, MainPage.class);
            startActivity(intent);
        }else{
            Toast.makeText(Vote.this,"failed", Toast.LENGTH_SHORT).show();
        }


    }
}
