package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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


public class view_task extends AppCompatActivity {
    Task T;
    Profile USER;
    DatabaseReference databaseProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        USER = (Profile) getIntent().getSerializableExtra("Profile");
        T = new Task("",1,"",1);
        //blank to not cause errors we still need to figure out how to pass an object to this page
    }
    public void CompleteClick(View view){
        completeTask(T);
    }
    public void DeclineClick(View view){
        declineTask(T);
    }
    public void ReturnClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void completeTask(Task compTask){

        int scoreToAdd = compTask.getReward();
        USER.set_score(USER.get_score() + scoreToAdd);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    public void declineTask(Task delTask){
        USER.removeTask(delTask);
        T = delTask;
        //TODO add to unassigned user once database is fixed

        databaseProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("profiles").child("UNASSIGN");
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                Profile unassign = snap.getValue(Profile.class);
                unassign.addTask(T);

            }

            @Override public void onCancelled(DatabaseError error) { }
        });
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
