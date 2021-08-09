package com.example.projectapp;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.components.Legend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class Trends extends AppCompatActivity {
    private PieChart pieChart;
    DatabaseHelper_compare my_DbCompare;
    ArrayList<String> array_models_selected;
    ArrayList<String> array_features;
    String s=null;
    Spinner spinner,spinnerf;
    ArrayAdapter<String> arrayadapter,arrayadapterf;
    Integer feature;
    TextView tv_featture,tv_bike_select,tv_desc;
    Button btn_cleartext,btn_compare;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        my_DbCompare= new DatabaseHelper_compare(this);
        my_DbCompare.getModels();
        tv_bike_select=findViewById(R.id.textview_bike);
        array_models_selected=new ArrayList<>();
        features_array();
        spinner=findViewById(R.id.spinner_model_search);
        arrayadapter= new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,my_DbCompare.getModels());
        spinner.setAdapter(arrayadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)

            {

                if(position!=0){
                    array_models_selected.add(parent.getItemAtPosition(position).toString());
                    s=s +" "+ parent.getItemAtPosition(position).toString();
                    tv_bike_select.setText("Compare " + s );
            }
            else  Toast.makeText(getApplicationContext(), "Select objects to compare", Toast.LENGTH_SHORT).show();}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        tv_featture=findViewById(R.id.tv_feature);
        spinnerf=findViewById(R.id.spinner_feature);
        arrayadapterf= new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,array_features);
        spinnerf.setAdapter(arrayadapterf);
        spinnerf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    if(position!=0){
                        feature= position;
                    tv_featture.setText(parent.getItemAtPosition(position).toString());}
                    else  Toast.makeText(getApplicationContext(), "Please select feature", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
        });
        tv_desc=findViewById(R.id.tv_Compare);

        pieChart=findViewById(R.id.piechart);
        btn_compare=findViewById(R.id.Compare);
        btn_compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_desc.setText("");
                setupPieChart();
                loadpiechartData();

            }
        });
        btn_cleartext=findViewById(R.id.clear_text);
        btn_cleartext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array_models_selected = new ArrayList<>();
                tv_bike_select.setText("");
                tv_desc.setText(R.string.compare_descp);
                tv_featture.setText("");
                s=null;
                loadpiechartData();
                setupPieChart();
            }
        });

//        BottomNavigationView bottomNavigationView= (BottomNavigationView)findViewById(R.id.navigation);
//        bottomNavigationView.setSelectedItemId(R.id.trends);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.home:
//                        Intent intent1= new Intent(Trends.this,Rate.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.rate:
//                        Intent intent2= new Intent(Trends.this,Rate.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.trends:
//
//                        break;
//                    case R.id.profile:
//                        Intent intent3= new Intent(Trends.this,Profile.class);
//                        startActivity(intent3);
//                        break;
//                }
//                return  false;
//            }
//        });

        FloatingActionButton f_home, f_rate, f_profile,f_navigate;
        // to check whether sub FAB buttons are visible or not.
        final Boolean[] isAllFabsVisible = new Boolean[1];
        f_navigate=findViewById(R.id.fb_navigate);
        f_home=findViewById(R.id.fb_home);
        f_rate=findViewById(R.id.fb_rate);
        f_profile=findViewById(R.id.fb_profile);
        f_home.setVisibility(View.GONE);
        f_rate.setVisibility(View.GONE);
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
                            f_rate.show();
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
                            f_rate.hide();
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
                        Intent intent1 = new Intent(Trends.this, Home.class);
                        startActivity(intent1);
                    }
                });
        f_rate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent1 = new Intent(Trends.this, Rate.class);
                        startActivity(intent1);
                    }
                });
        f_profile.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent1 = new Intent(Trends.this, Profile.class);
                        startActivity(intent1);
                    }
                });
    }
    void features_array()
    {
        array_features = new ArrayList<>();
        array_features.add("Select feature");
        array_features.add("Bodystyle");
        array_features.add("Economy");
        array_features.add("Sound Engine");
        array_features.add("Brake Quality");
        array_features.add("Comfort Level");
        array_features.add("Acceleration Rate");
        array_features.add("Handling Efficiency");
        array_features.add("Cummulative Ratings");
    }
    public void loadpiechartData(){
        ArrayList<PieEntry> entries= new ArrayList<>();
        for(int i=0;i<array_models_selected.size();i++)
        {
            float rating=my_DbCompare.findratings(array_models_selected.get(i),feature);
            entries.add(new PieEntry(rating,array_models_selected.get(i)));
        }
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }
        PieDataSet dataSet = new PieDataSet(entries, "Bike models compare");
        dataSet.setColors(colors);
        PieData Data = new PieData(dataSet);
        Data.setDrawValues(true);
        Data.setValueFormatter(new PercentFormatter(pieChart));
        Data.setValueTextSize(12f);
        Data.setValueTextColor(Color.BLACK);
        pieChart.setData(Data);
        pieChart.invalidate();
        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);
        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }
}