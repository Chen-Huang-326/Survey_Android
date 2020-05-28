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

    public String myAge;
    public String myEmail;
    public String myGender;
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
            Toast.makeText(MainActivity.this,"输入不能为空", Toast.LENGTH_SHORT).show();
        }else{
            boolean judge = DBUtils.loginJudge(name,pass);
            if(judge){
                Intent intent = new Intent(this, MainPage.class);
                ArrayList info = DBUtils.getInformation(name);
                myAge = (String) info.get(0);
                myEmail = (String) info.get(1);
                myGender = (String)info.get(2);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this,"failed", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void visitorActivityButton(View view){
        Intent intent = new Intent(this, Visitor.class);
        startActivity(intent);
    }
}
