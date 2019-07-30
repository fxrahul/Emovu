package com.emovu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {
    private Button startDetectorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        startDetectorButton =(Button) findViewById(R.id.detectorButton);
        startDetectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                Intent detectorIntent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(detectorIntent);
            }
        });
    }
}
