package com.example.segproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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


public class LoginPage extends AppCompatActivity {
    EditText username;
    EditText password;
    DatabaseReference databaseProfiles;
    List<Profile> profiles;
    String user;
    String pass;
    String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        databaseProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        profiles = new ArrayList<>();


    }

    public void onClickAvatar(View view){
        Intent intent = new Intent(getApplicationContext(),choose_photo.class);
        startActivityForResult (intent,0);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        ImageView avatarImage = (ImageView) findViewById(R.id.imbtnAvatar);
        String drawableName = "avatar_blue";

        switch (data.getIntExtra("imageID",R.id.imBlue)){
            case R.id.imBlue:
                drawableName="avatar_blue";
                image = new StringBuilder("drawable://").append(R.drawable.avatar_blue).toString();
                break;
            case R.id.imGray:
                drawableName="avatar_gray";
                image = new StringBuilder("drawable://").append(R.drawable.avatar_gray).toString();
                break;
            case R.id.imGreen:
                drawableName="avatar_green";
                image = new StringBuilder("drawable://").append(R.drawable.avatar_green).toString();
                break;
            case R.id.imOrange:
                drawableName="avatar_orange";
                image = new StringBuilder("drawable://").append(R.drawable.avatar_orange).toString();
                break;
            case R.id.imPink:
                drawableName="avatar_pink";
                image = new StringBuilder("drawable://").append(R.drawable.avatar_pink).toString();
                break;
            case R.id.imRed:
                drawableName="avatar_red";
                image = new StringBuilder("drawable://").append(R.drawable.avatar_red).toString();
                break;
            default:
                drawableName="avatar_blue";
                image = new StringBuilder("drawable://").append(R.drawable.avatar_blue).toString();
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        avatarImage.setImageResource(resID);


    }

    public void btnLoginClick(View view){
        try {
            username = (EditText) findViewById(R.id.txtUser);
            password = (EditText) findViewById(R.id.txtPassword);
            String user = username.getText().toString().trim();
            //TODO james implement this login method
            Profile USER = new Profile("", "", 1, "", null);// dummy profile
            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("profiles").child(user);
            dR.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snap) {
                    Profile USER = snap.getValue(Profile.class);
                    String user = username.getText().toString().trim();
                    String pass = username.getText().toString().trim();
                    if (USER != null) { //Debug.
                        if (USER.get_name().equals(user) && USER.get_password().equals(pass)) {
                            //TOAST HERE
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), home_page.class);
                            intent.putExtra("Profile", USER);
                            startActivityForResult(intent, 0);
                        } else {
                            //TOAST HERE
                            Toast.makeText(getApplicationContext(), "Failed to Login.", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Failed to Login. User does not exist.", Toast.LENGTH_LONG).show();
                        // TODO Add a break here once database is setup
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            Intent intent = new Intent(getApplicationContext(), home_page.class);
            intent.putExtra("Profile", USER);
            startActivityForResult(intent, 0);
        }catch(Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void btnCreateAccountClick(View view) {
        try { //TODO implement a proper toast for failed creation
            username = (EditText) findViewById(R.id.txtUser);
            password = (EditText) findViewById(R.id.txtPassword);
            String user = username.getText().toString().trim();
            String pass = username.getText().toString().trim();
            RadioButton radChild = (RadioButton) findViewById(R.id.radChild);
            String type;
            if (radChild.isChecked())
                type = "Child";
            else
                type = "Parent";
            Profile profile = new Profile(user, pass, 0, type, image);
            databaseProfiles.child(user).setValue(profile);
            Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }


    }




}



