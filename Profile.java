package com.example.projectapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        btn_logout=findViewById(R.id.logout);
//        SharedPreferences prefs = getSharedPreferences("name", MODE_PRIVATE);
//        boolean isLoggedIn= prefs.getBoolean("isLoggedIn", false);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("name", MODE_PRIVATE).edit();
                editor.putBoolean("isLoggedIn", false);
                editor.putString("Unm",null);
//any other detail you want to save
                editor.apply();
                Toast.makeText(getApplicationContext(), "Successfully logged out.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        BottomNavigationView bottomNavigationView= (BottomNavigationView)findViewById(R.id.navigation);
//        bottomNavigationView.setSelectedItemId(R.id.profile);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.home:
//                        Intent intent1= new Intent(Profile.this,MainActivity.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.rate:
//                        Intent intent2= new Intent(Profile.this,Rate.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.trends:
//                        Intent intent3= new Intent(Profile.this,Trends.class);
//                        startActivity(intent3);
//                        break;
//                    case R.id.profile:
//                        break;
//
//
//                }
//            return false;
//            }
//        });
        FloatingActionButton f_home, f_trend, f_rate,f_navigate;



        // to check whether sub FAB buttons are visible or not.
        final Boolean[] isAllFabsVisible = new Boolean[1];
        f_navigate=findViewById(R.id.fb_navigate);
        f_home=findViewById(R.id.fb_home);
        f_trend=findViewById(R.id.fb_trends);
        f_rate=findViewById(R.id.fb_rate);
        f_home.setVisibility(View.GONE);
        f_trend.setVisibility(View.GONE);
        f_rate.setVisibility(View.GONE);
        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible[0] = false;
        f_navigate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible[0]) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            f_home.show();
                            f_rate.show();
                            f_trend.show();


                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible[0] = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            f_home.hide();
                            f_rate.hide();
                            f_trend.hide();
                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible[0] = false;
                        }
                    }
                });
        f_home.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Profile.this, Home.class);
                        startActivity(intent1);
                    }
                });
        f_trend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Profile.this, Trends.class);
                        startActivity(intent1);
                    }
                });
        f_rate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Profile.this, Rate.class);
                        startActivity(intent1);
                    }
                });
    }}
