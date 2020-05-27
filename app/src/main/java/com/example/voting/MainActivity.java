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

public class MainActivity extends AppCompatActivity {

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
                intent.putExtra("username",name);
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
