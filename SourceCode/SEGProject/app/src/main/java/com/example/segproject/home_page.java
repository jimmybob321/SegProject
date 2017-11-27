package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class home_page extends AppCompatActivity {
    boolean isParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



    }
    public void ViewProfileClick(View view){
        if(isParent) {
            Intent intent = new Intent(getApplicationContext(),parent_profile.class);
            startActivityForResult(intent, 0);
        }
        else{
            Intent intent = new Intent(getApplicationContext(),child_profile.class);
            startActivityForResult(intent, 0);
        }
    }
    public void CreateTaskClick(View view){
        Intent intent = new Intent(getApplicationContext(),create_task.class);
        startActivityForResult (intent,0);
    }
    public void LogoutClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }
}
