package com.example.projectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Rate extends AppCompatActivity {

    Spinner spinner;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.tv_);
        ArrayList<String> numberList = new ArrayList<>();
        numberList.add("Product");
        numberList.add("Bike");
        numberList.add("Car");
        numberList.add("Smart Phone");

        spinner.setAdapter(new ArrayAdapter<>(Rate.this, android.R.layout.simple_spinner_dropdown_item,numberList));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String SpinnerValue = (String)spinner.getSelectedItem();
                switch(SpinnerValue){
                    case "Product":
                        Toast.makeText(getApplicationContext(),"Please select product",Toast.LENGTH_SHORT).show();
                        textView.setText("");
                        break;
                    case "Bike":
                        String sNumber= parent.getItemAtPosition(position).toString();
                        Intent intent = new Intent(Rate.this, rate_bike.class);
                        textView.setText(sNumber);
                        startActivity(intent);
                        break;

                    case "Car":
                        String sNumber1= parent.getItemAtPosition(position).toString();
                        intent = new Intent(Rate.this, rate_car.class);
                        textView.setText(sNumber1);
                        startActivity(intent);
                        break;

                    case "Smart Phone":
                        String sNumber2= parent.getItemAtPosition(position).toString();
                        intent = new Intent(Rate.this, rate_smart_phone.class);
                        textView.setText(sNumber2);
                        startActivity(intent);
                        break;}
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        bottomNavigationView.setSelectedItemId(R.id.rate);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.home:
//                        Intent intent1 = new Intent(Rate.this, MainActivity.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.rate:
//
//                        break;
//
//                    case R.id.trends:
//                        Intent intent2 = new Intent(Rate.this, Trends.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.profile:
//                        Intent intent3 = new Intent(Rate.this, Profile.class);
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
                        Intent intent1 = new Intent(Rate.this, Home.class);
                        startActivity(intent1);
                    }
                });
        f_trend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Rate.this, Trends.class);
                        startActivity(intent1);
                    }
                });
        f_profile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(Rate.this, Profile.class);
                        startActivity(intent1);
                    }
                });
    }




}
