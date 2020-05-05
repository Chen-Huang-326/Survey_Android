package com.example.voting;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Question_multiple extends AppCompatActivity {
    private int index = 0;
    private ArrayList<TextView> textViews = new ArrayList<>(); // To store the new generated textView;

    private String TAG = "ShowActivity";
    private TextView choice;

    private ArrayList<LinearLayout> layouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_multiple);

    }

    public void MulAdd (View v){
        LinearLayout choice_layout = (LinearLayout)findViewById(R.id.choice_view);
        choice = new TextView(this);
        index++;
        choice.setId(index);
        choice.setText("Enter your choice " + index);
        choice.setTextSize(20);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textViews.add(choice);
        Log.d(TAG, String.format("button[%d]",
                textViews.size()));
        choice_layout.addView(choice,p);

    }

    public void MulBack (View v){
        finish();
    }

}
