package com.example.voting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);
    }

    public void confirmRegisterActivityButton(View view){
        EditText username = findViewById(R.id.e_rUsername);
        EditText password = findViewById(R.id.e_rPassword);
        EditText confirm = findViewById(R.id.e_rConfirm);
        String name = username.getText().toString();
        String pass = password.getText().toString();
        String con = confirm.getText().toString();
        if(name == null || "".equals(name) || pass == null || "".equals(pass) ){
            Toast.makeText(Register.this,"the input cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            if(!pass.equals(con)){
                Toast.makeText(Register.this,"inconsistent password", Toast.LENGTH_SHORT).show();
            }else{
                boolean judge = DBUtils.register(name,pass);
                if(judge){
                    Toast.makeText(Register.this,"registered successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Register.this,"user name duplication", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
