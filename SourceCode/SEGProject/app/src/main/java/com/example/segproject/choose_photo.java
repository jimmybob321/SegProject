package com.koyukan.latest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SetProfileIcon(View view) {
        Intent returnIntent = new Intent();
        ImageView chosenImage = (ImageView) view;
        returnIntent.putExtra("image", chosenImage.getId());
        setResult(RESULT_OK, returnIntent);
        finish();
    }





}
