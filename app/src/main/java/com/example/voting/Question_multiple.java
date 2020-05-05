package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
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
        setContentView(R.layout.activity_question_multiple);

    }

    //TODO: task 1 - Adding/deleting an topic with a set of options

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
        textContents.clear();
        Intent intent = new Intent();
        EditText question = (EditText)findViewById(R.id.mul_question_content);
        String q = question.getText().toString();
        textContents.add(q);

        int n = editTexts.size();
        for (int i = 0; i < n; i++) {
            textContents.add(editTexts.get(i).getText().toString());
        }
        intent.putStringArrayListExtra("multiple_choice",textContents);
        setResult(RESULT_OK,intent);
        finish();
    }

}
