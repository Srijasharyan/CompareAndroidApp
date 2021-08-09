package com.example.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class rate_smart_phone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_smart_phone);
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        bottomNavigationView.setSelectedItemId(R.id.rate);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.home:
//                        Intent intent1 = new Intent(rate_smart_phone.this, MainActivity.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.rate:
//
//                        break;
//
//                    case R.id.trends:
//                        Intent intent2 = new Intent(rate_smart_phone.this, Trends.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.profile:
//                        Intent intent3 = new Intent(rate_smart_phone.this, Profile.class);
//                        startActivity(intent3);
//                        break;
//
//                }
//                return false;
//
//            }
//
//
//        });
        FloatingActionButton f_home, f_trend, f_profile,f_navigate;



        // to check whether sub FAB buttons are visible or not.
        final Boolean[] isAllFabsVisible = new Boolean[1];
        f_navigate=findViewById(R.id.fb_navigate);
        f_home=findViewById(R.id.fb_home);
        f_trend=findViewById(R.id.fb_trends);
        f_profile=findViewById(R.id.fb_profile);
        f_home.setVisibility(View.GONE);
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
                            f_home.show();
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
                            f_home.hide();
                            f_profile.hide();
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
                        Intent intent1 = new Intent(rate_smart_phone.this,Home.class);
                        startActivity(intent1);
                    }
                });
        f_trend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(rate_smart_phone.this, Trends.class);
                        startActivity(intent1);
                    }
                });
        f_profile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(rate_smart_phone.this, Profile.class);
                        startActivity(intent1);
                    }
                });
    }
}