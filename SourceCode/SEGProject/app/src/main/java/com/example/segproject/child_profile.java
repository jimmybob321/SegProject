package com.example.segproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class child_profile extends AppCompatActivity {
    Profile USER;
    TextView name, score;
    ImageView avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        USER = (Profile) getIntent().getSerializableExtra("Profile");
        avatar = (ImageView) findViewById(R.id.imgAvatar);
        name = (TextView) findViewById(R.id.txtName);
        score = (TextView) findViewById(R.id.txtScore);

        name.setText(new StringBuilder("Name: ").append(USER.get_name()));
        score.setText(new StringBuilder("Score: ").append(USER.get_score()));
        int resID = getResources().getIdentifier(USER.get_img(), "drawable", getPackageName());
        avatar.setImageResource(resID);
    }
        //TODO add display of tasks

}
