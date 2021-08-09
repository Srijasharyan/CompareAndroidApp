package com.example.projectapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class rate_bike extends AppCompatActivity {
    DatabaseHelper_compare my_DbCompare;
    DatabaseHelper my_Db;
    TextView textView_bike_search,result_tv;
    Spinner spinner1,spinner2;
    RatingBar rt_1,rt_2,rt_3,rt_4,rt_5,rt_6,rt_7;
    Button btn_submit;
    ArrayList <String> array_tier;
    ArrayList <String> array_bike_search_tier1;
    ArrayList <String> array_bike_search_tier2;
    ArrayList <String> array_bike_search_tier3;
    ArrayAdapter<String>arrayadapter1;
    ArrayAdapter<String>arrayadapter2;
    String sNumber,biketier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_bike);
        my_DbCompare= new DatabaseHelper_compare(this);
        my_Db=new DatabaseHelper(this);
        //binding spinners
        prepared_lis();
        spinner1=findViewById(R.id.spinner_bike_tier);
        spinner2=findViewById(R.id.spinner_bike_search);
        textView_bike_search=findViewById(R.id.textview_bike);
        arrayadapter1= new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,array_tier);
        spinner1.setAdapter(arrayadapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                biketier= parent.getItemAtPosition(position).toString();
                if(position==0){
                    arrayadapter2=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bike_search_tier1);

                }
                if(position==1){
                    arrayadapter2=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bike_search_tier2);

                }
                if(position==2){
                    arrayadapter2=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,array_bike_search_tier3);

                }
                spinner2.setAdapter(arrayadapter2);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        }



        );
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sNumber= parent.getItemAtPosition(position).toString();
                textView_bike_search.setText("Rate "+ sNumber);
                rt_1.setRating((float) 0.0);
                rt_2.setRating((float) 0.0);
                rt_3.setRating((float) 0.0);
                rt_4.setRating((float) 0.0);
                rt_5.setRating((float) 0.0);
                rt_6.setRating((float) 0.0);
                rt_7.setRating((float) 0.0);
                result_tv.setText("Ratings");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
        //Rating bar
        SharedPreferences sp1=this.getSharedPreferences("name",MODE_PRIVATE);

        String user_id=sp1.getString("Unm", null);
        rt_1=findViewById(R.id.rb_bodystyle);
        rt_2=findViewById(R.id.rb_Economy);
        rt_3=findViewById(R.id.rb_Smoothness_or_sound_engine);
        rt_4=findViewById(R.id.rb_Brake_Quality);
        rt_5=findViewById(R.id.rb_comfort_level);
        rt_6=findViewById(R.id.rb_acc_rate);
        rt_7=findViewById(R.id.rb_Handling_efficiency);
        btn_submit=findViewById(R.id.btn_submit_id);
        result_tv=findViewById(R.id.finaltextView);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float f1=rt_1.getRating(),f2=rt_2.getRating(),f3=rt_3.getRating(),f4=rt_4.getRating(),f5=rt_5.getRating(),f6=rt_6.getRating(),f7=rt_7.getRating();
                float f=f1+f2+f3+f4+f5+f6+f7;
                f=f/7;
                String s= String.valueOf(rt_1.getRating());
                result_tv.setText("Total ratings:"+f);
                my_DbCompare.insertData(user_id,sNumber,biketier,f1,f2,f3,f4,f5,f6,f7,f);
                Toast.makeText(getApplicationContext(), "Data entered", Toast.LENGTH_SHORT).show();
            }
        });
        //
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        bottomNavigationView.setSelectedItemId(R.id.rate);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()) {
//                    case R.id.home:
//                        Intent intent1 = new Intent(rate_bike.this, MainActivity.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.rate:
//
//                        break;
//
//                    case R.id.trends:
//                        Intent intent2 = new Intent(rate_bike.this, Trends.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.profile:
//                        Intent intent3 = new Intent(rate_bike.this, Profile.class);
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


        //floating button
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
                        Intent intent1 = new Intent(rate_bike.this, Home.class);
                        startActivity(intent1);
                    }
                });
        f_trend.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(rate_bike.this, Trends.class);
                        startActivity(intent1);
                    }
                });
        f_profile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(rate_bike.this, Profile.class);
                        startActivity(intent1);
                    }
                });

    }
    void prepared_lis(){
        array_tier= new ArrayList<>();
        array_tier.add("Under 125cc");
        array_tier.add("From 125cc to 180cc");
        array_tier.add("180 cc+");
        //
        array_bike_search_tier1= new ArrayList<>();
        array_bike_search_tier1.add("Hero splendor");
        array_bike_search_tier1.add("Bajaj discover");
        array_bike_search_tier1.add("Honda dream yuga");

        //
        array_bike_search_tier2= new ArrayList<>();
        array_bike_search_tier2.add("Honda cb shine");
        array_bike_search_tier2.add("Hero Glamour");
        array_bike_search_tier2.add("Bajaj_pulsor ");
        //
        array_bike_search_tier3= new ArrayList<>();
        array_bike_search_tier3.add("Duke");
        array_bike_search_tier3.add("Royal Enfield");
        array_bike_search_tier3.add("Apache 200");
    }

}