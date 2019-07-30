package com.emovu;

import android.content.Intent;
import android.os.Trace;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail , inputPassword;
    private Button btnLogin;
    private   String usernameAfterExecution;
    DatabaseReference databaseUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = (EditText) findViewById(R.id.login_input_email);
        inputPassword = (EditText) findViewById(R.id.login_input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                getUser();
            }
        });
    }

    public void getUser(){

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String key = email+password;

        //checking if the value is provided
        if (!TextUtils.isEmpty(email)  && !TextUtils.isEmpty(password) ) {

            Query loginQuery = databaseUsers.orderByChild("uniquekey").equalTo(key);
                    loginQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    if(dataSnapshot.getValue()==null){
                        Toast.makeText(LoginActivity.this, "Email or Password is Wrong",Toast.LENGTH_LONG);
                        Intent errorIntent = new Intent(LoginActivity.this, LoginActivity.class);
                        LoginActivity.this.startActivity(errorIntent);
                    }else {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                        Log.d("val=",child.getValue()); // true, true, etc
                            user = child.getValue(User.class);
//                          usernameAfterExecution=user.getUsername();
//                            Log.d("check", user.getUsername());
                        }
                        usernameAfterExecution = user.getUsername();
                        Toast.makeText(LoginActivity.this, "Login Succesfull" + " " + "Welcome" + " " + usernameAfterExecution, Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(LoginActivity.this, DashboardActivity.class);

                        LoginActivity.this.startActivity(myIntent);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}

            });

        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_LONG).show();
        }

    }





    public void RegisterPage(View view){
        Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);

        LoginActivity.this.startActivity(myIntent);
    }
}
