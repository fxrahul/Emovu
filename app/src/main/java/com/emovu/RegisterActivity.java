package com.emovu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RegisterActivity extends AppCompatActivity {

    private EditText inputName, inputEmail , inputPassword, inputUsername;
    private TextView btnSignIn;
    private Button btnSignUp, btnResetPassword;
    private ProgressBar progressBar;

    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        btnSignIn = (TextView) findViewById(R.id.link_login);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        inputUsername = (EditText) findViewById(R.id.input_username);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addUser();
            }
        });
    }

    private void addUser() {
        //getting the values to save
        String username = inputUsername.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String uniqueKey = email+password;



        //checking if the value is provided
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)  && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(username) ) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseUsers.push().getKey();

            //creating an Artist Object
            User user = new User(id,username,uniqueKey, name, email, password);

            //Saving the Artist
            databaseUsers.child(id).setValue(user);

            //setting edittext to blank again
//            inputName.setText("");

            //displaying a success toast
            Toast.makeText(RegisterActivity.this, "User added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_LONG).show();
        }
    }



    }
