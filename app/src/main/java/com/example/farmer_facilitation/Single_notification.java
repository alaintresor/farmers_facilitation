package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Single_notification extends AppCompatActivity {
    TextView subjectTextView,dateTextView,bodyTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_notification);

        //get user ID
        final String userId = getIntent().getStringExtra("userId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Notification");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
//                Toast.makeText(product.this, "tool bar", Toast.LENGTH_SHORT).show();
            }
        });

        subjectTextView=findViewById(R.id.sub);
        dateTextView=findViewById(R.id.date);
        bodyTextView=findViewById(R.id.body);

        String subject=getIntent().getStringExtra("subject");
        String date=getIntent().getStringExtra("date");
        String msg=getIntent().getStringExtra("msg");
        subjectTextView.setText(subject);
        dateTextView.setText(date);
        bodyTextView.setText(msg);
    }
}