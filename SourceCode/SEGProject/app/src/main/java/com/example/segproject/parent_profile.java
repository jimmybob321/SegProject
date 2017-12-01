package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class parent_profile extends AppCompatActivity {
    Profile USER;
    TextView name, score;
    ImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);

        USER = (Profile) getIntent().getSerializableExtra("Profile");
        avatar = (ImageView) findViewById(R.id.imgAvatar);
        name = (TextView) findViewById(R.id.txtName);
        score = (TextView) findViewById(R.id.txtScore);
        name.setText(USER.get_name());
        score.setText(USER.get_score());
        avatar.setImageDrawable(USER.get_img());
    }

    public void ReturnClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }
    //TODO Need to display child profiles and tasks

}
