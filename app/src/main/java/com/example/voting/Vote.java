package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Vote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
    }

    public void QuestionOption (View v){
        PopupMenu popupMenu = new PopupMenu(Vote.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.question_type,popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.description:
                        Intent intentI = new Intent(Vote.this, Question_description.class);
                        startActivityForResult(intentI,2);
                        break;
                    case R.id.multiple_choice:
                        Intent intentII = new Intent(Vote.this, Question_multiple.class);
                        startActivityForResult(intentII,3);
                        break;

                }
                return false;
            }
        });
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

    /**
     * Set the function to edit the survey topic title;
     * @param v
     */
    public void voteTitleEdit (View v){
        Intent intent = new Intent(Vote.this, CreateTitle.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                Button title = (Button) findViewById(R.id.vote_title_add_button);
                title.setText(data.getStringExtra("input"));
            }
        }
    }
}
