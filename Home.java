package com.example.projectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Home extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        floating button
        FloatingActionButton f_rate, f_trend, f_profile, f_navigate;
        // to check whether sub FAB buttons are visible or not.
        final Boolean[] isAllFabsVisible = new Boolean[1];
        f_navigate = findViewById(R.id.fb_navigate);
        f_rate = findViewById(R.id.fb_rate);
        f_trend = findViewById(R.id.fb_trends);
        f_profile = findViewById(R.id.fb_profile);
        f_rate.setVisibility(View.GONE);
        f_trend.setVisibility(View.GONE);
        f_profile.setVisibility(View.GONE);
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
                            f_rate.show();
                            f_profile.show();
                            f_trend.show();
                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible[0] = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            f_rate.hide();
                            f_profile.hide();
                            f_trend.hide();
                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible[0] = false;
                        }
                    }
                });
        f_rate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Home.this, Rate.class);
                        startActivity(intent1);
                    }
                });
        f_trend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Home.this, Trends.class);
                        startActivity(intent1);
                    }
                });
        f_profile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Home.this, Profile.class);
                        startActivity(intent1);
                    }
                });
    }
}