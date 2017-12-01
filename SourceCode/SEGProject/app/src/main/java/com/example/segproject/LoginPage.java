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
    String drawableName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        databaseProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        profiles = new ArrayList<>();
        drawableName = "avatar_blue";

    }

    public void onClickAvatar(View view){
        Intent intent = new Intent(getApplicationContext(),choose_photo.class);
        startActivityForResult (intent,0);

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        ImageView avatarImage = (ImageView) findViewById(R.id.imbtnAvatar);
        switch (data.getIntExtra("imageID",R.id.imBlue)){
            case R.id.imBlue:
                drawableName="avatar_blue";
                break;
            case R.id.imGray:
                drawableName="avatar_gray";
                break;
            case R.id.imGreen:
                drawableName="avatar_green";
                break;
            case R.id.imOrange:
                drawableName="avatar_orange";
                break;
            case R.id.imPink:
                drawableName="avatar_pink";
                break;
            case R.id.imRed:
                drawableName="avatar_red";
                break;
            default:
                drawableName="avatar_blue";
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
                    String pass = password.getText().toString().trim();
                    try{
                        if(USER.get_name() == null)
                            Toast.makeText(getApplicationContext(), "Login Failed. User does not exist", Toast.LENGTH_SHORT).show();
                        else if (USER.get_name().equals(user) && USER.get_password().equals(pass)) {
                            //TOAST HERE
                            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), home_page.class);
                            intent.putExtra("Profile", USER);
                            startActivityForResult(intent, 0);
                        } else {
                            //TOAST HERE
                            Toast.makeText(getApplicationContext(), "Failed to Login.", Toast.LENGTH_SHORT).show();
                        }
                    }catch(NullPointerException e){
                        Toast.makeText(getApplicationContext(), "Failed to Login. User does not exist.", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(), "Failed to Login." + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
       }catch(Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void btnCreateAccountClick(View view) {
        try { //TODO implement a proper toast for failed creation
            username = (EditText) findViewById(R.id.txtUser);
            password = (EditText) findViewById(R.id.txtPassword);
            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.equals("")) {
                Toast.makeText(getApplicationContext(), "Enter a Username", Toast.LENGTH_SHORT).show();
                return;
            }else if (pass.equals("")) {
                Toast.makeText(getApplicationContext(), "Enter a Password", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton radChild = (RadioButton) findViewById(R.id.radChild);
            String type;
            if (radChild.isChecked())
                type = "Child";
            else
                type = "Parent";
            Profile profile = new Profile(user, pass, 0, type, drawableName);
            databaseProfiles.child(user).setValue(profile);
            Toast.makeText(getApplicationContext(), "Profile Created", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }


    }




}



