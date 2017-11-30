package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.io.Serializable;

public class parent_profile extends AppCompatActivity {
    Profile USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        USER = (Profile) getIntent().getSerializableExtra("Profile");
    }

    public void ReturnClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }
    //Need to display child profiles and tasks
}
