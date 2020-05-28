package com.example.voting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Survey extends AppCompatActivity {

    private ArrayList<TextView> description_list = new ArrayList<>(); // Storing description view
    private ArrayList<TextView> multiple_list = new ArrayList<>(); // Storing multiple view
    private ArrayList<TextView> text_list = new ArrayList<>(); // Storing text question view
    private ArrayList<ArrayList<TextView>> choice_list = new ArrayList<>(); // Storing multiple choice corresponding to particular question
    private int index = 0;
    private ArrayList<Button> des_removeButtons = new ArrayList<>(); // Storing "remove" button for description
    private ArrayList<Button> mul_removeButtons = new ArrayList<>(); // Storing "remove" button for multiple question
    private ArrayList<Button> text_removeButtons = new ArrayList<>(); // Storing "remove" button for multiple question
    private String [] basic_info = new String[3];
    private ArrayList<String []> question_list = new ArrayList<>(); // Storing information: question, question type, choice number and selected limitation
    private ArrayList<ArrayList<String>> que_choice_pair = new ArrayList<>(); // Stroing information: question with corresponding choices

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_survey);
    }

    /**
     * Set the function of "create_query" button;
     * Click to generate a menu to choose the question type
     * question types include "Multiple choice" "Paragraph" "Text (Open question)"
     * @param v
     */

    //TODO: task 6 - Advanced feature: Survey (TBD)
    public void QuestionOption (View v){
        PopupMenu popupMenu = new PopupMenu(Survey.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.question_type,popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.description:
                        Intent intentI = new Intent(Survey.this, Question_description.class);
                        startActivityForResult(intentI,2);
                        break;
                    case R.id.multiple_choice:
                        Intent intentII = new Intent(Survey.this, Question_multiple.class);
                        startActivityForResult(intentII,3);
                        break;
                    case R.id.open_question:
                        Intent intentIII = new Intent(Survey.this, Question_text.class);
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
        Intent intent = new Intent(Survey.this, CreateTitle.class);
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
                basic_info[1] = data.getStringExtra("input"); // add survey title in to the basic information list
            }
            // To demonstrate the "description"
            if (requestCode == 2){
                index++;
                // Create a textView to show the content of question
                TextView query = new TextView(this);
                String question = data.getStringExtra("description");
                query.setText(question);
                query.setTextSize(16);
                query.setId(index);
                description_list.add(query);

                // Store the information: question, type, choice_num and selected_limitation
                String [] info = new String[4];
                info [0] = question;
                info [1] = "description";
                info [2] = "0";
                info [3] = "0";

                question_list.add(info);

                // Create a button to add the button to remove the question
                Button remove = new Button(this);
                remove.setId(index);
                remove.setText("Remove");
                remove.setTextSize(12);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                des_removeButtons.add(remove);

                survey_layout.addView(query);
                survey_layout.addView(remove);

                // Set remove function
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeButton(v);
                    }
                });
            }
            //To demonstrate the "multiple choice"
            if (requestCode == 3){
                index++;
                //Create a group of textViews to show the contents of question and choices
                ArrayList<String> multiple = data.getStringArrayListExtra("multiple_choice");
                //Copy the question
                TextView query = new TextView(this);
                String question = multiple.get(0);
                query.setText(question);
                query.setTextSize(16);
                query.setId(index);
                multiple_list.add(query);
                survey_layout.addView(query);

                int length = multiple.size();
                ArrayList<TextView> choices = new ArrayList<>();
                for (int i = 2; i < length; i++){
                    TextView choice = new TextView(this);
                    choice.setText(i + ". " + multiple.get(i));
                    choice.setTextSize(16);
                    choice.setId(index+i);
                    choices.add(choice);
                    survey_layout.addView(choice);
                }
                choice_list.add(choices);

                // Store the information: question, type, choice_num and selected_limitation
                String [] info = new String[4];
                info [0] = question;
                info [1] = "multiple";
                info [2] = (multiple.size() - 2) + "";
                info [3] = multiple.get(1);

                question_list.add(info);

                // Store the information: question and corresponding choices
                multiple.remove(1);
                que_choice_pair.add(multiple);

                // Set remove button
                Button remove = new Button(this);
                remove.setId(index);
                remove.setText("Remove");
                remove.setTextSize(12);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mul_removeButtons.add(remove);

                survey_layout.addView(remove);

                // Set remove function
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeButton(v);
                    }
                });
            }
            // To demonstrate the "open question"
            if (requestCode == 4){
                index++;
                // Create a textView to show the content of question
                TextView query = new TextView(this);
                String question = data.getStringExtra("text_question") + "\n" +"(Open-end question)";
                query.setText(question);
                query.setTextSize(16);
                query.setId(index);
                text_list.add(query);

                // Store the information: question, type, choice_num and selected_limitation
                String [] info = new String[4];
                info [0] = question;
                info [1] = "text";
                info [2] = "0";
                info [3] = "0";

                question_list.add(info);

                // Create a button to add the button to remove the question
                Button remove = new Button(this);
                remove.setId(index);
                remove.setText("Remove");
                remove.setTextSize(12);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                text_removeButtons.add(remove);

                survey_layout.addView(query);
                survey_layout.addView(remove);

                // Set remove function
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removeButton(v);
                    }
                });

            }
        }
    }

    //TODO: task 1.2 - Deleting an topic with a set of options

    /**
     * Ues the method to match the view of button click behaviour and execute the behaviour
     * @param v
     */
    public void removeButton (View v){
        LinearLayout survey_layout = (LinearLayout)findViewById(R.id.survey_view);

        if (v == null) return;

        for (int i = 0; i < des_removeButtons.size(); i++){
            if (des_removeButtons.get(i) == v){
                survey_layout.removeView(description_list.get(i));
                survey_layout.removeView(des_removeButtons.get(i));
                description_list.remove(i);
                des_removeButtons.remove(i);
                break;
            }
        }

        for (int i = 0; i < mul_removeButtons.size(); i++){
            if (mul_removeButtons.get(i) == v){
                survey_layout.removeView(multiple_list.get(i));
                ArrayList<TextView> choice = choice_list.get(i);
                survey_layout.removeViews(0, choice.size());
                survey_layout.removeView(mul_removeButtons.get(i));
                multiple_list.remove(i);
                choice_list.remove(i);
                mul_removeButtons.remove(i);
                break;

            }
        }
    }

    /**
     * This method is used for clear all descriptions and questions
     */

    public void EditClear (View v){
        LinearLayout survey_layout = (LinearLayout)findViewById(R.id.survey_view);

        survey_layout.removeAllViews();
    }

    public void Submit (View v){
        ConstraintLayout sur_main = (ConstraintLayout)findViewById(R.id.survey_main);
        AlertDialog.Builder sub_builder = new AlertDialog.Builder(this);
        sub_builder.setIcon(R.drawable.ic_launcher_background);
        final View view = getLayoutInflater().inflate(R.layout.survey_submit,null);
        sub_builder.setView(view);
        sub_builder.setTitle("Submit");
        sub_builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView due_date_title = (TextView) view.findViewById(R.id.due_data_title);
                EditText due_date = (EditText) view.findViewById(R.id.sur_title);
                String date_title = due_date_title.getText().toString();
                String date = due_date.getText().toString();
                if (date.length() == 0) {
                    Toast.makeText(Survey.this, "Haven't set the due date", Toast.LENGTH_SHORT).show();
                } else {
                    // Check the date format is corrected or not
                    boolean dateFormatValid = checkDateFormat(date);

                    if (dateFormatValid) {
                        if (checkDateValid(date)) {
                            basic_info[0] = MainActivity.myUsername;// add user name into the basic information list
                            basic_info[2] = date; // add due date into the basic information list

                            // Create the information about the whole survey without choices
                            ArrayList<String[]> my_survey = createInfo();

                            // evoke the method to transform the survey data to server
                            DBUtils.StoreSurveyData (my_survey, que_choice_pair);

                            Toast.makeText(Survey.this, "Submit success!", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                            Intent intent = new Intent();
                            setResult(RESULT_OK,intent);
                            finish();
                        } else Toast.makeText(Survey.this,"Date must be set after today", Toast.LENGTH_SHORT).show();
                    }
                    if (!dateFormatValid) {
                        Toast.makeText(Survey.this, "Date is illegal", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        sub_builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        sub_builder.show();

    }

    /**
     * Check whether the string has corrected date format (dd/mm/yyyy)
     * @param date
     * @return boolean
     */
    public boolean checkDateFormat (String date){
        boolean dateValid = false;

        if (date.length() == 10 && date.charAt(2) == 47 && date.charAt(5) == 47) {
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(3, 5));
            int year = Integer.parseInt(date.substring(6));
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
        } else dateValid = false;
        return dateValid;
    }

    /**
     * Check whether the date is set after the current date
     * @param date
     * @return boolean
     */
    public boolean checkDateValid (String date) {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date current = new Date(System.currentTimeMillis());
        Date set = null;
        try {
            set = simpleFormat.parse(date);
            return set.after(current);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Combine the information: user name, survey title, due date, question, question type, choice number and selected limitation into one list;
     * @return
     */
    public ArrayList<String[]> createInfo (){
        ArrayList<String[]> surveys = new ArrayList<>();

        for (int i = 0; i < question_list.size(); i++){
            String [] survey = new String[7];
            String[] list = question_list.get(i);
            survey[0] = basic_info[0];
            survey[1] = basic_info[1];
            survey[2] = basic_info[2];
            survey[3] = list[0];
            survey[4] = list[1];
            survey[5] = list[2];
            survey[6] = list[3];
            surveys.add(survey);
        }


        return surveys;
    }

}
