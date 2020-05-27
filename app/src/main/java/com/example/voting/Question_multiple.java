package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Question_multiple extends AppCompatActivity {
    private int index = 0;
    private ArrayList<EditText> editTexts = new ArrayList<>(); // To store the new generated EditText;
    private ArrayList<String> textContents = new ArrayList<>(); // To store the contents from the EditText;

    private String TAG = "ShowActivity";
    private EditText choice;

    private ArrayList<LinearLayout> layouts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_question_multiple);

    }



    //TODO: task 1.1 - Adding an topic with a set of options

    /**
     * Actively create EditViews when click the button "ADD"
     * @param v
     */

    public void MulAdd (View v){
        LinearLayout choice_layout = (LinearLayout)findViewById(R.id.choice_view);
        choice = new EditText(this);
        index++;
        choice.setId(index);
        choice.setText("Enter your choice " + index);
        choice.setTextSize(20);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editTexts.add(choice);
        Log.d(TAG, String.format("button[%d]",
                editTexts.size()));
        choice_layout.addView(choice,p);
    }

    public void MulRemove (View v){
        LinearLayout choice_layout = (LinearLayout)findViewById(R.id.choice_view);
        if (!editTexts.isEmpty()) {
            int n = editTexts.size() - 1;
            choice_layout.removeView(editTexts.get(n));
            editTexts.remove(n);
        }
        else {
            Toast.makeText(this, "No choice to remove", Toast.LENGTH_SHORT).show();
        }
    }

    public void MulBack (View v){
        finish();
    }

    public void MulSubmit (View v) {
        EditText selected_limitation = (EditText) findViewById(R.id.limited_num);
        int limited = Integer.parseInt(selected_limitation.getText().toString());
        int choiceNum = editTexts.size();
        if (limited > choiceNum) {
            Toast.makeText(this,"Selected limitation cannot over the choice number", Toast.LENGTH_SHORT).show();
        } else {
            textContents.clear();
            Intent intent = new Intent();
            EditText question = (EditText) findViewById(R.id.mul_question_content);
            String q = question.getText().toString();
            if (limited == 1){
                q += ("\n" + "(Single choice)");
            }
            if (limited > 1) {
                q += ("\n" + "(Please select 1 or " + limited + " options)");
            }
            textContents.add(q);

            int n = editTexts.size();
            for (int i = 0; i < n; i++) {
                textContents.add(editTexts.get(i).getText().toString());
            }
            intent.putStringArrayListExtra("multiple_choice", textContents);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}
