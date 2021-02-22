package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class profile extends AppCompatActivity {
    TextView ch_username, ch_password, usernameTextView;
    EditText districtEditText, sectorEditText, villageEditText, cellEditText, phoneEditText;
    Button SaveLocationBtn, SavePhoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        //get user ID
        final String userId = getIntent().getStringExtra("userId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("My Profile");
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

        ch_username = findViewById(R.id.ch_username);
        ch_password = findViewById(R.id.ch_password);
        usernameTextView = findViewById(R.id.userName);
        phoneEditText = findViewById(R.id.phone);
        districtEditText = findViewById(R.id.district);
        sectorEditText = findViewById(R.id.sector);
        villageEditText = findViewById(R.id.village);
        cellEditText = findViewById(R.id.cell);
        SavePhoneBtn = findViewById(R.id.phoneBtn);
        SaveLocationBtn = findViewById(R.id.locationBtn);


        //get user information
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

                PutData putData = new PutData("http://192.168.1.10/famer_facilition/getUserInfo.php", "POST", field, data);
                if (putData.startPut()) {

                    String result = null;
                    if (putData.onComplete()) {
                        // progressBar.setVisibility(View.GONE);
                        result = putData.getResult();

//                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
                        try {
                            JSONArray array = new JSONArray(result);



                                JSONObject object = array.getJSONObject(0);
                                String farmerName = object.getString("farmerName");
                                String district = object.getString("district");
                                String sector = object.getString("sector");
                                String village = object.getString("village");
                                String cell = object.getString("cell");
                                String phone = object.getString("phone");

                                usernameTextView.setText(farmerName);
                                districtEditText.setText(district);
                                sectorEditText.setText(sector);
                                villageEditText.setText(village);
                                cellEditText.setText(cell);
                                phoneEditText.setText(phone);



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    }

                }
            }
            //End Write and Read data with URL

        });

        //update location
        SaveLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String district = String.valueOf(districtEditText.getText());
                final String sector = String.valueOf(sectorEditText.getText());
                final String village = String.valueOf(villageEditText.getText());
                final String cell = String.valueOf(cellEditText.getText());

                if (!district.equals("") && !sector.equals("") && !village.equals("") && !cell.equals("")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Creating array for parameters
                            String[] field = new String[5];
                            field[0] = "farmerId";
                            field[1] = "district";
                            field[2] = "sector";
                            field[3] = "village";
                            field[4] = "cell";

                            //Creating array for data
                            String[] data = new String[5];
                            data[0] = userId;
                            data[1] = district;
                            data[2] = sector;
                            data[3] = village;
                            data[4] = cell;

                            PutData putData = new PutData("http://192.168.1.10/famer_facilition/updateLocation.php", "POST", field, data);
                            if (putData.startPut()) {

                                String result = null;
                                if (putData.onComplete()) {
                                    // progressBar.setVisibility(View.GONE);
                                    result = putData.getResult();
                                    if (result.toString().equals("Data updated well done")) {
                                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        }
                        //End Write and Read data with URL

                    });
                }
            }
        });

        //update phone
        SavePhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = String.valueOf(phoneEditText.getText());

                if (!phone.equals("") ) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "farmerId";
                            field[1] = "phone";


                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = userId;
                            data[1] = phone;


                            PutData putData = new PutData("http://192.168.1.10/famer_facilition/updatePhone.php", "POST", field, data);
                            if (putData.startPut()) {

                                String result = null;
                                if (putData.onComplete()) {
                                    // progressBar.setVisibility(View.GONE);
                                    result = putData.getResult();
                                    if (result.toString().equals("Data updated well done")) {
                                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        }
                        //End Write and Read data with URL

                    });
                }
            }
        });

        ch_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), change_username.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        ch_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), change_password.class);
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