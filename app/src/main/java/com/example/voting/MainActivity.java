package com.example.voting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
     *  Use global variables to realize the reuse of user data in other classes
     */
    public static String myUsername;
    public static String myAge;
    public static String myEmail;
    public static String myGender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void registerActivityButton(View view){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }



    public void loginActivityButton(View view){
        EditText username = findViewById(R.id.e_username);
        EditText password = findViewById(R.id.e_password);
        String name = username.getText().toString();
        String pass = password.getText().toString();
        if(name == null || "".equals(name) || pass == null || "".equals(pass) ){
            Toast.makeText(MainActivity.this,"the input cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            boolean judge = DBUtils.loginJudge(name,pass);
            myUsername="";
            if(judge){
                Intent intent = new Intent(this, MainPage.class);
                myUsername=name;
                ArrayList info = DBUtils.getInformation(name);
                myAge = (String) info.get(0);
                myEmail = (String) info.get(1);
                myGender = (String)info.get(2);
                startActivity(intent);
            }else{
                myUsername="";
                Toast.makeText(MainActivity.this,"wrong password or username", Toast.LENGTH_LONG).show();
            }
        }

    }

    /**
     *  Visitors can access the main page to vote or fill out survey
     *  BUT have NO ACCESS to create or show personal information
     *  which have been set inside of "Create" fragment and "Account" fragment
     */
    public void visitorActivityButton(View view){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        myUsername="";
    }
}
