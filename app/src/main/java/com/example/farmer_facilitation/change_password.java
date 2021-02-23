package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class change_password extends AppCompatActivity {
    EditText newPassword, confirmPassword, currentPassword;
    Button cancel, save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPassword = findViewById(R.id.newPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        currentPassword = findViewById(R.id.currentPassword);
        save = findViewById(R.id.cp_save);
        cancel = findViewById(R.id.cancel);

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
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }

                }
            }
            //End Write and Read data with URL

        });

//        submitting form
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPwd = String.valueOf(newPassword.getText());
                String confirmPwd = String.valueOf(confirmPassword.getText());
                String currentPwd = String.valueOf(currentPassword.getText());
                if (!newPwd.equals("") && !confirmPwd.equals("") && !currentPwd.equals("")) {
                    if (newPwd.equals(confirmPwd)) {
                        if (currentPwd.equals(DBpassword[0])) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //Starting Write and Read data with URL
                                    //Creating array for parameters
                                    String[] field = new String[3];
                                    field[0] = "farmerId";
                                    field[1] = "newPassword";
                                    field[2] = "currentPassword";

                                    //Creating array for data
                                    String[] data = new String[3];
                                    data[0] = userId;
                                    data[1] = newPwd;
                                    data[2] = currentPwd;

                                    PutData putData = new PutData("http://192.168.1.10/famer_facilition/changePassword.php", "POST", field, data);
                                    if (putData.startPut()) {
                                        if (putData.onComplete()) {
                                            String result = putData.getResult();
                                            if (result.toString().equals("Password Changed well")) {
                                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), profile.class);
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
                            Toast.makeText(getApplicationContext(), "Invalid current password!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "You Entered two different password!", Toast.LENGTH_SHORT).show();
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