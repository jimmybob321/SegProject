package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class claim_task extends AppCompatActivity {
    Profile USER;
    Task T;
    EditText Child;
    DatabaseReference databaseProfiles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        USER = (Profile) getIntent().getSerializableExtra("Profile");
        //T = (Task) getIntent().getSerializableExtra("Task"); will uncomment out when we get the display working
        T = new Task("",1,"",1);//blank to not cause errors we still need to figure out how to pass an object to this page
    }
    public void ClaimClick(View view){

        USER.addTask(T);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    public void AssignClick(View view){
        //TODO add toast for exception
        Child = (EditText) findViewById(R.id.txtChild);
        String child = Child.getText().toString().trim();
        try{
        databaseProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("profiles").child(child);
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                Profile CHILD = snap.getValue(Profile.class);
                CHILD.addTask(T);

            }

            @Override public void onCancelled(DatabaseError error) { }
        });}
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
            //toast asking for proper name
        }
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
