package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        //get notifications from database
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "farmerId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = userId;

                PutData putData = new PutData("http://192.168.1.10/famer_facilition/getNotifications.php", "POST", field, data);
                if (putData.startPut()) {

                    String result = null;
                    if (putData.onComplete()) {
                        // progressBar.setVisibility(View.GONE);
                        result = putData.getResult();
                        if (!result.toString().equals("No notifications found")) {
                            try {
                                JSONArray array = new JSONArray(result);

                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject object = array.getJSONObject(i);
                                    String id=object.getString("id");
                                    String date = object.getString("date");
                                    String subject = object.getString("subject");
                                    String message = object.getString("message");
                                    String status = object.getString("status");

                                    MyNotifications.add(new MyNotifications(id, date, subject, message,status));

                                }

                                notificationAdapter notificationAdapter = new notificationAdapter(getApplicationContext(), R.layout.notification_item, MyNotifications);
                                listView.setAdapter(notificationAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Check your network connection", Toast.LENGTH_SHORT).show();

                    }

                }
            }
            //End Write and Read data with URL

        });


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