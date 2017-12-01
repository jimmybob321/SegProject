package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.widget.AdapterView;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class home_page extends AppCompatActivity {
    Profile USER;
    DatabaseReference dR;
    ListView listViewTasks;
    ArrayList<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        USER = (Profile) getIntent().getSerializableExtra("Profile");
        listViewTasks = (ListView) findViewById(R.id.listViewTasks);
        tasks = new ArrayList<>();
        dR = FirebaseDatabase.getInstance().getReference("tasks").child("unassigned");

        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = tasks.get(i);
                Intent intent = new Intent(getApplicationContext(),claim_task.class);
                intent.putExtra("Task", task);
                intent.putExtra("Profile", USER);
                startActivityForResult(intent, 0);
                return true;
            }
        });

    }
    public void ViewProfileClick(View view){
        if(USER.isParent()) {
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

    @Override
    protected void onStart() {
        super.onStart();
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                tasks.clear();

                for (DataSnapshot postSnapshot : snap.getChildren()) {
                    Task task = postSnapshot.getValue(Task.class);
                    tasks.add(task);
                }

                //adapter
                TaskList tasksAdapter = new TaskList(home_page.this, tasks);
                listViewTasks.setAdapter(tasksAdapter);
            }
            @Override
            public  void onCancelled(DatabaseError error){
            }
        });
    }





}
