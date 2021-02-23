package com.example.farmer_facilitation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    CardView profile;
    CardView manuel;
    CardView AddHarvest;
    CardView MyHarvest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

//pop up new notifications
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

                PutData putData = new PutData("http://192.168.1.10/famer_facilition/popUpNewNotifications.php", "POST", field, data);
                if (putData.startPut()) {

                    String result = null;
                    if (putData.onComplete()) {
                        // progressBar.setVisibility(View.GONE);
                        result = putData.getResult();
                        if (parseInt(result) > 0) {
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.ic_message)
                                    .setContentTitle("New Notification")
                                    .setContentText("Your have new" + result + "message(s)")
                                    .setAutoCancel(true);
                            Intent intent = new Intent(getApplicationContext(), Notification.class);
                            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                            PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(pendingIntent);
                            NotificationManager notificationManager=(NotificationManager)getSystemService(
                                    Context.NOTIFICATION_SERVICE
                            );
                            notificationManager.notify(0,builder.build());
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Check your network connection", Toast.LENGTH_SHORT).show();

                    }

                }
            }
            //End Write and Read data with URL

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
        switch (item.getItemId()) {
            case R.id.nav_notification:
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                final String userId = getIntent().getStringExtra("userId");
                intent.putExtra("userId", userId);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}