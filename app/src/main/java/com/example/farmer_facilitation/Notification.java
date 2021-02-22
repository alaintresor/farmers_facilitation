package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //get user ID
        final String userId = getIntent().getStringExtra("userId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Notifications");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
//                Toast.makeText(product.this, "tool bar", Toast.LENGTH_SHORT).show();
            }
        });

        listView = findViewById(R.id.listView);

        final List<MyNotifications> MyNotifications;
        MyNotifications = new ArrayList<>();
        MyNotifications.add(new MyNotifications("1", "20/2/2021", "Meeting on sunday", "You are all invited in our staff meeting on sunday You are all invited in our staff meeting on sunday You are all invited in our staff meeting on sunday"));
        MyNotifications.add(new MyNotifications("1", "20/2/2021", "Meeting on sunday", "You are all invited in our staff meeting on sunday"));

        notificationAdapter notificationAdapter = new notificationAdapter(this, R.layout.notification_item, MyNotifications);
        listView.setAdapter(notificationAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String subject=MyNotifications.get(i).getSubject();
                String date=MyNotifications.get(i).getDate();
                String msg=MyNotifications.get(i).getMsg();
                Intent intent=new Intent(getApplicationContext(),Single_notification.class);
                intent.putExtra("subject",subject);
                intent.putExtra("date",date);
                intent.putExtra("msg",msg);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }
}