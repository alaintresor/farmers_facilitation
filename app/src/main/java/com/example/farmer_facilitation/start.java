package com.example.farmer_facilitation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final Handler handler=new Handler();
        final Runnable r=new Runnable() {
            @Override
            public void run() {
                start();
            }
        };
        handler.postDelayed(r,3000);
    }
    public void start(){
        Intent intent=new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
    }
}
