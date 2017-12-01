package com.example.segproject;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class child_profile extends AppCompatActivity {
    Profile USER;
    TextView name, score;
    ImageView avatar;
    ListView listViewTasks;
    ArrayList<Task> tasks;
    DatabaseReference dR;
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
        listViewTasks = (ListView) findViewById(R.id.listViewTasks);
        tasks = new ArrayList<>();

        name.setText(new StringBuilder("Name: ").append(USER.get_name()));
        score.setText(new StringBuilder("Score: ").append(USER.get_score()));
        int resID = getResources().getIdentifier(USER.get_img(), "drawable", getPackageName());
        avatar.setImageResource(resID);

        dR = FirebaseDatabase.getInstance().getReference("tasks").child(USER.get_name());

        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = tasks.get(i);
                return true;
            }
        });

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
                TaskList tasksAdapter = new TaskList(child_profile.this, tasks);
                listViewTasks.setAdapter(tasksAdapter);
            }
            @Override
            public  void onCancelled(DatabaseError error){
            }
        });
    }

    //TODO add display of tasks

}
