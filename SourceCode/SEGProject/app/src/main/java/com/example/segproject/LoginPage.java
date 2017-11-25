package com.example.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
    }

    public void btnLoginClick(View view){
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        Login(username.getText().toString().trim(),password.getText().toString().trim());
    }

    private void Login(String user, String pass){
        //james implement this method

        Intent intent = new Intent(getApplicationContext(),home_page.class);
        startActivityForResult (intent,0);
    }

}
