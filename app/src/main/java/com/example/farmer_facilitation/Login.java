package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText user=(EditText) findViewById(R.id.editTextTextPersonName);
        final EditText pwd=(EditText) findViewById(R.id.editTextTextPersonName2);
        final Button loginBtn=(Button) findViewById(R.id.button);
//        final TextView signUp=(TextView)findViewById(R.id.signHere);
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progress);



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username= String.valueOf(user.getText());
                final String password= String.valueOf(pwd.getText());
                if(!username.equals("" )&& !password.equals("")){

                    progressBar.setVisibility(View.VISIBLE);
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData("http://192.168.1.10/famer_facilition/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(!result.toString().equals("Wrong Username or Password"))
                                    {
                                        String userId=result.toString();
                                        Toast.makeText(getApplicationContext(),"Welcome", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("userId",userId);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else
                                {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),"Network Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"Network Error", Toast.LENGTH_SHORT).show();
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields are required", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}