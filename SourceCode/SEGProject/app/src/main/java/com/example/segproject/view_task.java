package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class view_task extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

    }
    public void CompleteClick(View view){
        completeTask();
    }
    public void DeclineClick(View view){
        declineTask();
    }
    public void ReturnClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void completeTask(){
        // needs Implementation




        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void declineTask(){
        // Needs Implementation



        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
