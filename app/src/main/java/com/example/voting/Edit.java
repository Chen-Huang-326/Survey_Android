package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {

    private ArrayList<TextView> query_list = new ArrayList<>();
    private int index = 0;
    private ArrayList<Button> removeButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    /**
     * Set the function of "create_query" button;
     * Click to generate a menu to choose the question type
     * question types include "Multiple choice" "Paragraph" "Text (Open question)"
     * @param v
     */
    
    //TODO: task 6 - Advanced feature: Survey (TBD)
    public void QuestionOption (View v){
        PopupMenu popupMenu = new PopupMenu(Edit.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.question_type,popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.description:
                        Intent intentI = new Intent(Edit.this, Question_description.class);
                        startActivityForResult(intentI,2);
                        break;
                    case R.id.multiple_choice:
                        Intent intentII = new Intent(Edit.this, Question_multiple.class);
                        startActivityForResult(intentII,3);
                        break;
                    case R.id.open_question:
                        Intent intentIII = new Intent(Edit.this, Question_text.class);
                        startActivityForResult(intentIII,4);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.question_type, menu);
        return true;
    }

    /**
     *  Set the function of "back" button;
     *  @param v
     */
    public void Back (View v){
        Intent intent = new Intent();
        setResult(RESULT_OK,intent);
        finish();
    }

    /**
     * Set the function to edit the survey topic title;
     * @param v
     */
    public void TitleEdit (View v){
        Intent intent = new Intent(Edit.this, CreateTitle.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        LinearLayout survey_layout = (LinearLayout)findViewById(R.id.survey_view);

        if (resultCode == RESULT_OK){
            // To demonstrate the "title"
            if (requestCode == 1){
                TextView title = (TextView)findViewById(R.id.survey_title_editer);
                title.setText(data.getStringExtra("input"));
            }
            // To demonstrate the "description"
            if (requestCode == 2){
                index++;
                // Create a textView to show the content of question
                TextView query = new TextView(this);
                query.setText(data.getStringExtra("description"));
                query.setTextSize(16);
                query.setId(index);
                query_list.add(query);

                // Create a button to add the button to remove the question
                Button remove = new Button(this);
                remove.setId(index);
                remove.setText("Remove");
                remove.setTextSize(12);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                removeButtons.add(remove);

                survey_layout.addView(query);
                survey_layout.addView(remove);
            }
            //To demonstrate the "multiple choice"
            if (requestCode == 3){
                index++;
                //Create a group of textViews to show the contents of question and choices
                ArrayList<String> multiple = data.getStringArrayListExtra("multiple_choice");TextView query = new TextView(this);
                //Copy the question
                query.setText(multiple.get(0));
                query.setTextSize(16);
                query.setId(index);
                survey_layout.addView(query);

                int length = multiple.size();
                for (int i = 1; i < length; i++){
                    CheckBox choice = new CheckBox(this);
                    choice.setText(multiple.get(i));
                    choice.setTextSize(16);
                    choice.setId(index+i);
                    survey_layout.addView(choice);
                }

                Button remove = new Button(this);
                remove.setId(index);
                remove.setText("Remove");
                remove.setTextSize(12);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                removeButtons.add(remove);

                survey_layout.addView(remove);
            }
            // To demonstrate the "open question"
            // TODO: task 6.4 Open question (TBD)
        }
    }

}
