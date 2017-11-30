package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import java.io.Serializable;


public class create_profile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void ChoosePhotoButton (View view){
        //Opens the choose photo page
        Intent intent= new Intent(getApplicationContext(),choose_photo.class);
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult (int requestCode,int resultCode,Intent data){
        if(resultCode==RESULT_CANCELED)return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage =(ImageView)findViewById(R.id.imageView);

        // Figuring out the correct image
        String drawableName = "ic_logo_f00";

        switch(data.getIntExtra("imageID",R.id.imageView0)){
            case R.id.imageView0:
            drawableName="ic_logo_f00";
            break;
            case R.id.imageView1:
            drawableName="ic_logo_01";
            break;
            case R.id.imageView2:
            drawableName="ic_logo_02";
            break;
            case R.id.imageView3:
            drawableName="ic_logo_03";
            break;
            case R.id.imageView4:
            drawableName="ic_logo_04";
            break;
            case R.id.imageView5:
            drawableName="ic_logo_05";
            break;
            default:
                drawableName="ic_logo_00";
                break;
        }
        int resID=getResources().getIdentifier(drawableName,"drawable",getPackageName());
        avatarImage.setImageResource(resID);
    }

    public void onClickSelectImage(View view){
        String drawableName = "ic_logo_f00";

        switch(view.getId()){
            case R.id.imageView0:
                drawableName="ic_logo_f00";
                break;
            case R.id.imageView1:
                drawableName="ic_logo_01";
                break;
            case R.id.imageView2:
                drawableName="ic_logo_02";
                break;
            case R.id.imageView3:
                drawableName="ic_logo_03";
                break;
            case R.id.imageView4:
                drawableName="ic_logo_04";
                break;
            case R.id.imageView5:
                drawableName="ic_logo_05";
                break;
            default:
                drawableName="ic_logo_00";
                break;
        }
        



    }


    public void CreateParentClick(View view) {
    // TODO implement profile creation code here







        //go to home page
        Intent intent = new Intent(getApplicationContext(),home_page.class);
        startActivityForResult(intent, 0);
    }
    public void CreateChildClick(View view) {
        // TODO implement profile creation code here






        //go to home page
        Intent intent = new Intent(getApplicationContext(),home_page.class);
        startActivityForResult(intent, 0);
    }

}
