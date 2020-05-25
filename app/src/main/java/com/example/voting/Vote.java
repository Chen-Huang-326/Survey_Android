package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

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
        removeOption5.setVisibility(View.INVISIBLE);
    }


    /**
     * Set the function to edit the survey topic title;
     * @param v
     */
    public void voteTitleEdit (View v){

    }
}
