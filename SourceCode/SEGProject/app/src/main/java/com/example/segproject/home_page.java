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
    Profile USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        USER = (Profile) getIntent().getSerializableExtra("Profile");


    }
    public void ViewProfileClick(View view){
        if(isParent) {
            Intent intent = new Intent(getApplicationContext(),parent_profile.class);
            intent.putExtra("Profile", USER);
            startActivityForResult(intent, 0);
        }
        else{
            Intent intent = new Intent(getApplicationContext(),child_profile.class);
            intent.putExtra("Profile", USER);
            startActivityForResult(intent, 0);
        }
    }
    public void CreateTaskClick(View view){
        Intent intent = new Intent(getApplicationContext(),create_task.class);
        intent.putExtra("Profile", USER);
        startActivityForResult (intent,0);
    }
    public void LogoutClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }

    public void ChoosePhotoButton(View view){
        //Opens the choose photo page
        Intent intent= new Intent(getApplicationContext(),choose_photo.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult (int requestCode,int resultCode,Intent data){
        if(resultCode==RESULT_CANCELED)return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage =(ImageView)findViewById(R.id.avatarImage);

        // Figuring out the correct image
        String drawableName ="ic_logo_00";
        switch(data.getIntExtra("imageID",R.id.teamid00)){
            caseR.id.teamid00:
                drawableName="ic_logo_00";
                break;
            caseR.id.teamid01:
                drawableName="ic_logo_01";
                break;
            caseR.id.teamid02:
                drawableName="ic_logo_02";
                break;
            caseR.id.teamid03:
                drawableName="ic_logo_03";
                break;
            caseR.id.teamid04:
                drawableName="ic_logo_04";
                break;
            caseR.id.teamid05:
                drawableName="ic_logo_05";
                break;
            default:
                drawableName="ic_logo_00";
                break;
        }
        intresID=getResources().getIdentifier(drawableName,"drawable",getPackageName());avatarImage.setImageResource(resID);
    }


}
