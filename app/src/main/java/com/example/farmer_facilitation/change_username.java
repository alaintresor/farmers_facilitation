package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class change_username extends AppCompatActivity {
    EditText newUsername, currentUsername, password;
    Button cancel, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);

        newUsername = findViewById(R.id.username);
        currentUsername = findViewById(R.id.current_username);
        password = findViewById(R.id.cu_password);
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);

        //get user ID
        final String userId = getIntent().getStringExtra("userId");

        final String[] DBusername = new String[1];
        final String[] DBpassword = new String[1];


        //get user login information
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

                PutData putData = new PutData("http://192.168.1.10/famer_facilition/getUsernameAndPassword.php", "POST", field, data);
                if (putData.startPut()) {

                    String result = null;
                    if (putData.onComplete()) {
                        result = putData.getResult();

                        try {
                            JSONArray array = new JSONArray(result);


                            JSONObject object = array.getJSONObject(0);
                            DBusername[0] = object.getString("username");
                            DBpassword[0] = object.getString("password");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                    }

                }
            }
            //End Write and Read data with URL

        });

//        submitting form
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nUsername = String.valueOf(newUsername.getText());
                String cUsername = String.valueOf(currentUsername.getText());
                String pwd = String.valueOf(password.getText());

                if (!nUsername.equals("") && !cUsername.equals("") && !pwd.equals("")) {
                    if (cUsername.equals(DBusername[0])) {
                        if (pwd.equals(DBpassword[0])) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //Starting Write and Read data with URL
                                    //Creating array for parameters
                                    String[] field = new String[4];
                                    field[0] = "farmerId";
                                    field[1] = "newUsername";
                                    field[2] = "oldUsername";
                                    field[3] = "password";
                                    //Creating array for data
                                    String[] data = new String[4];
                                    data[0] = userId;
                                    data[1] = nUsername;
                                    data[2] = cUsername;
                                    data[3] = pwd;
                                    PutData putData = new PutData("http://192.168.1.10/famer_facilition/changeUsername.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            String result = putData.getResult();
                                            if (result.toString().equals("Username Changed well")) {
                                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(),profile.class);
                                                intent.putExtra("userId", userId);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                            }

                                        } else {

                                            Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {

                                        Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                    }
                                    //End Write and Read data with URL
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid current username!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //cancel process
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
                finish();
            }
        });
    }
}