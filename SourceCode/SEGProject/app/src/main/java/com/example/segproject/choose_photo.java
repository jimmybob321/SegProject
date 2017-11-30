package com.example.segproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


public class choose_photo extends AppCompatActivity {
    Profile USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_main);
    }

    public void SetProfileIcon(View view) {
        Intent returnIntent = new Intent();
        ImageView chosenImage = (ImageView) view;
        returnIntent.putExtra("image", chosenImage.getId());
        setResult(RESULT_OK, returnIntent);
        finish();
=======
        setContentView(R.layout.activity_choose_photo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
<<<<<<< HEAD
>>>>>>> 779e829b5ee83dcef4ec1a5d49dc957f0e1d1a7d
=======

        USER = (Profile) getIntent().getSerializableExtra("Profile");
>>>>>>> 89954a3c455793fcc50046a764900219a22dd9be
    }





}
