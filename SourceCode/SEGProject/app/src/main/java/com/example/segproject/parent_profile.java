package com.example.segproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class parent_profile extends AppCompatActivity {
    Profile USER;
    TextView name, score;
    ImageView avatar;

    DatabaseReference dRTasks, dRChildren;
    ListView listViewTasks, listViewChild;
    ArrayList<Task> tasks;
    ArrayList<Profile> children;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        USER = (Profile) getIntent().getSerializableExtra("Profile");

        listViewTasks = (ListView) findViewById(R.id.listViewTasks);
        listViewChild = (ListView) findViewById(R.id.listViewChildren);
        tasks = new ArrayList<>();
        children = new ArrayList<>();
        dRTasks = FirebaseDatabase.getInstance().getReference("tasks").child(USER.get_name());
        dRChildren = FirebaseDatabase.getInstance().getReference("profiles");

        avatar = (ImageView) findViewById(R.id.imgAvatar);
        name = (TextView) findViewById(R.id.txtName);
        score = (TextView) findViewById(R.id.txtScore);
        name.setText(new StringBuilder("Name: ").append(USER.get_name()));
        score.setText(new StringBuilder("Score: ").append(USER.get_score()));
        int resID = getResources().getIdentifier(USER.get_img(), "drawable", getPackageName());
        avatar.setImageResource(resID);


        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = tasks.get(i);
                Intent intent = new Intent(getApplicationContext(), view_task.class);
                intent.putExtra("Profile", USER);
                intent.putExtra("Task",task);
                startActivityForResult(intent, 0);
                return true;
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        dRTasks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                tasks.clear();

                for (DataSnapshot postSnapshot : snap.getChildren()) {
                    Task task = postSnapshot.getValue(Task.class);
                    tasks.add(task);
                }

                //adapter
                TaskList tasksAdapter = new TaskList(parent_profile.this, tasks);
                listViewTasks.setAdapter(tasksAdapter);
            }
            @Override
            public  void onCancelled(DatabaseError error){
            }
        });
        dRChildren.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                children.clear();

                for (DataSnapshot postSnapshot : snap.getChildren()) {
                    Profile child = postSnapshot.getValue(Profile.class);
                    if (!child.isParent())
                        children.add(child);
                }

                //adapter
                ProfileList childAdapter = new ProfileList(parent_profile.this, children);
                listViewChild.setAdapter(childAdapter);
            }
            @Override
            public  void onCancelled(DatabaseError error){
            }
        });
    }

    public void ReturnClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();

    }
    //TODO Need to display child profiles and tasks

}
