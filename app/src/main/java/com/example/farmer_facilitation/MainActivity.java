package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    CardView profile;
    CardView manuel;
    CardView AddHarvest;
    CardView MyHarvest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Farmers Facilitation");

        setSupportActionBar(toolbar);

        profile = findViewById(R.id.profile);
        manuel = findViewById(R.id.manuel);
        AddHarvest = findViewById(R.id.addHarvest);
        MyHarvest = findViewById(R.id.harvest);



        //get user ID
        final String userId = getIntent().getStringExtra("userId");

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), profile.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        manuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Apply.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        MyHarvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyHarvest.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        AddHarvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddHarvest.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_notification:
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                final String userId=getIntent().getStringExtra("userId");
                intent.putExtra("userId",userId);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}