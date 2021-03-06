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
import android.widget.TextView;
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
    TextView taskName,taskDate,taskReward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        USER = (Profile) getIntent().getSerializableExtra("Profile");
        T = (Task) getIntent().getSerializableExtra("Task");

        taskName = findViewById(R.id.txtName);
        taskDate = findViewById(R.id.txtDate);
        taskReward = findViewById(R.id.txtReward);

        taskName.setText(T.getTitle());
        taskDate.setText(T.getDate());
        taskReward.setText(Integer.toString(T.getReward()));

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
        databaseProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        databaseProfiles.child(USER.get_name()).child("_score").setValue(USER.get_score());
        databaseProfiles = FirebaseDatabase.getInstance().getReference("tasks");
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child(USER.get_name()).child(T.getTitle());
        dR.setValue(null);

        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    public void declineTask(Task delTask){
        //USER.removeTask(delTask);
        T = delTask;
        String assign = T.getUser();

        databaseProfiles = FirebaseDatabase.getInstance().getReference("tasks");
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("tasks").child("unassigned").child(T.getTitle());
        Task T2= new Task(T.getTitle(),T.getReward(),T.getDate(),T.getPriority(),"unassigned");
        dR.setValue(T2);

        dR = FirebaseDatabase.getInstance().getReference("tasks").child(USER.get_name()).child(T.getTitle());
        dR.setValue(null);
        /*dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                //Profile unassign = snap.getValue(Profile.class);
               // unassign.addTask(T);

                dR.setValue(snap.child(T.getTitle()).getValue(Task.class).setUser("unassigned"));

            }

            @Override public void onCancelled(DatabaseError error) { }
        });*/
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
