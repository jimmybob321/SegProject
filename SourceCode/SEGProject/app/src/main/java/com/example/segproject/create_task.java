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
import android.widget.EditText;


public class create_task extends AppCompatActivity {
    EditText TaskName;
    EditText TaskDate;
    EditText TaskPriority;
    EditText TaskReward;
    EditText TaskUser;
    Profile USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        TaskName = (EditText) findViewById(R.id.txtName);
        TaskDate = (EditText) findViewById(R.id.txtDate);
        TaskPriority = (EditText) findViewById(R.id.txtReward);
        TaskReward = (EditText) findViewById(R.id.txtPriority);
        TaskUser = (EditText) findViewById(R.id.txtAssignUser);
        USER = (Profile) getIntent().getSerializableExtra("Profile");



    }
    public void CreateTaskClick(View view){
        TaskName = (EditText) findViewById(R.id.txtName);
        TaskDate = (EditText) findViewById(R.id.txtDate);
        TaskPriority = (EditText) findViewById(R.id.txtReward);
        TaskReward = (EditText) findViewById(R.id.txtPriority);
        TaskUser = (EditText) findViewById(R.id.txtAssignUser);
        createTask(TaskName.getText().toString().trim(),TaskDate.getText().toString().trim(),Integer.parseInt(TaskPriority.getText().toString().trim()),Integer.parseInt(TaskReward.getText().toString().trim()));
    }
    private void createTask(String name, String Date, int priority, int reward){
        //implement creating task




        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
