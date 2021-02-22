package com.example.farmer_facilitation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Apply extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        final Spinner seedSpinner = (Spinner) findViewById(R.id.seedSpinner);
        final Spinner pesticideSpinner = (Spinner) findViewById(R.id.pesticideSpinner);
        final Spinner fertilizerSpinner = (Spinner) findViewById(R.id.fertilizerSpinner);
        final EditText seedEditText = (EditText) findViewById(R.id.seedEditText);
        final EditText pesticideEditText = (EditText) findViewById(R.id.pesticideEditText);
        final EditText fertilizerEditText = (EditText) findViewById(R.id.fertilizerEditText);
        Button button = (Button) findViewById(R.id.applySubmitBtn);


        //get user ID
        final String userId = getIntent().getStringExtra("userId");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Applying");
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

        ///creating seeds list
        final List<String> seeds = new ArrayList<String>();
        seeds.add("Select Seed");

        ///creating pesticides list
        final List<String> pesticides = new ArrayList<String>();
        pesticides.add("Select Pesticide");

        ///creating fertilizers list
        final List<String> fertilizers = new ArrayList<String>();
        fertilizers.add("Select fertilizer");

        //get seeds from database
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "userId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = "userId";

                PutData putData = new PutData("http://192.168.1.10/famer_facilition/getSeeds.php", "POST", field, data);
                if (putData.startPut()) {

                    String result = null;
                    if (putData.onComplete()) {
                        // progressBar.setVisibility(View.GONE);
                        result = putData.getResult();
                        if (!result.toString().equals("No seeds found")) {
                            try {
                                JSONArray array = new JSONArray(result);

                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject object = array.getJSONObject(i);
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    seeds.add(name);


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    }

                }
            }
            //End Write and Read data with URL

        });

        //get pesticides from database

        handler.post(new Runnable() {
            @Override
            public void run() {

                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "userId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = "userId";
                PutData putData = new PutData("http://192.168.1.10/famer_facilition/getPesticides.php", "POST", field, data);
                if (putData.startPut()) {

                    String result = null;
                    if (putData.onComplete()) {
                        // progressBar.setVisibility(View.GONE);
                        result = putData.getResult();
                        if (!result.toString().equals("No pesticides found")) {
                            try {
                                JSONArray array = new JSONArray(result);

                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject object = array.getJSONObject(i);
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    pesticides.add(name);


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    }

                }
            }
            //End Write and Read data with URL

        });

        //get fertilizers from database

        handler.post(new Runnable() {
            @Override
            public void run() {

                //Creating array for parameters
                String[] field = new String[1];
                field[0] = "userId";

                //Creating array for data
                String[] data = new String[1];
                data[0] = "userId";
                PutData putData = new PutData("http://192.168.1.10/famer_facilition/getFertilizers.php", "POST", field, data);
                if (putData.startPut()) {

                    String result = null;
                    if (putData.onComplete()) {
                        // progressBar.setVisibility(View.GONE);
                        result = putData.getResult();
                        if (!result.toString().equals("No fertilizers found")) {
                            try {
                                JSONArray array = new JSONArray(result);

                                for (int i = 0; i < array.length(); i++) {

                                    JSONObject object = array.getJSONObject(i);
                                    String id = object.getString("id");
                                    String name = object.getString("name");
                                    fertilizers.add(name);


                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } else {

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    }

                }
            }
            //End Write and Read data with URL

        });

        ArrayAdapter<String> seedAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, seeds);
        seedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seedSpinner.setAdapter(seedAdapter);
//        seedSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> pesticideAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pesticides);
        pesticideAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pesticideSpinner.setAdapter(pesticideAdapter);
//        pesticideSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> fertilizerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, fertilizers);
        fertilizerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fertilizerSpinner.setAdapter(fertilizerAdapter);
//        fertilizerSpinner.setOnItemSelectedListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String seedQty = String.valueOf(seedEditText.getText());
                final String pesticideQty = String.valueOf(pesticideEditText.getText());
                final String fertilizerQty = String.valueOf(fertilizerEditText.getText());
                final String seed = String.valueOf(seedSpinner.getSelectedItem());
                final String pesticide = String.valueOf(pesticideSpinner.getSelectedItem());
                final String fertilizer = String.valueOf(fertilizerSpinner.getSelectedItem());

                if (!seedQty.equals("") && !pesticideQty.equals("") && !fertilizerQty.equals("") && !pesticide.equals("Select pesticide") && !seed.equals("Select seed") && !fertilizer.equals("Select fertilizer")) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            //Creating array for parameters
                            String[] field = new String[7];
                            field[0] = "farmerId";
                            field[1] = "seed";
                            field[2] = "seedQty";
                            field[3] = "fertilizer";
                            field[4] = "fertilizerQty";
                            field[5] = "pesticide";
                            field[6] = "pesticideQty";

                            //Creating array for data
                            String[] data = new String[7];
                            data[0] = userId;
                            data[1] = seed;
                            data[2] = seedQty;
                            data[3] = fertilizer;
                            data[4] = fertilizerQty;
                            data[5] = pesticide;
                            data[6] = pesticideQty;
                            PutData putData = new PutData("http://192.168.1.10/famer_facilition/apply.php", "POST", field, data);
                            if (putData.startPut()) {

                                String result = null;
                                if (putData.onComplete()) {
                                    result = putData.getResult();
                                    if (result.toString().equals("Your Application submitted")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.putExtra("userId", userId);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error: " + result, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();

                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                        //End Write and Read data with URL

                    });
                } else {
                    Toast.makeText(getApplicationContext(), "All field are required", Toast.LENGTH_LONG).show();
                }
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