package com.example.projectapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseHelper_compare extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "compare.db";
    public static final String TABLE_NAME = "compare_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Person_Id";
    public static final String COL_3 = "Bike_Model";
    public static final String COL_4 = "CC";
    public static final String COL_5 = "Bodystyle";
    public static final String COL_6 = "Economy";
    public static final String COL_7 = "Sound_Engine";
    public static final String COL_8 = "Brake_Quality";
    public static final String COL_9 = "Comfort_Level";
    public static final String COL_10 = "Acceleration_Rate";
    public static final String COL_11 = "Handling_Efficiency";
    public static final String COL_12 = "Cummulative_Ratings";

    // Database_creation
    public DatabaseHelper_compare(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    // Table_creation
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,Person_Id TEXT,Bike_Model TEXT,CC TEXT,Bodystyle REAL,Economy REAL,Sound_Engine REAL,Brake_Quality REAL,Comfort_Level REAL,Acceleration_Rate REAL,Handling_Efficiency REAL,Cummulative_Ratings REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Person_Id, String Bike_Model, String CC, float Bodystyle, float Economy, float Sound_Engine, float Brake_Quality, float Comfort_Level, float Acceleration_Rate, float Handling_Efficiency, float Cummulative_Ratings) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Person_Id);
        contentValues.put(COL_3, Bike_Model);
        contentValues.put(COL_4, CC);
        contentValues.put(COL_5, Bodystyle);
        contentValues.put(COL_6, Economy);
        contentValues.put(COL_7, Sound_Engine);
        contentValues.put(COL_8, Brake_Quality);
        contentValues.put(COL_9, Comfort_Level);
        contentValues.put(COL_10, Acceleration_Rate);
        contentValues.put(COL_11, Handling_Efficiency);
        contentValues.put(COL_12, Cummulative_Ratings);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public float findratings(String keyword, Integer feature) {
        List<Float> list; float sum = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_3 + " like ?", new String[]{"%" + keyword + "%"});
        if (cursor.moveToFirst()) {
            list = new ArrayList<Float>();
            do {
                float f = cursor.getFloat(3 + feature);
                list.add(f);
            }
            //

            while (cursor.moveToNext());

            for (int i = 0; i < list.size(); i++) {
                sum = sum + list.get(i);
            }
            sum = sum / list.size();

        }
        return sum;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> getModels() {
        List<String> getModels =new ArrayList<String>();
        List<String> listAll =new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String str = null;

        do {
           listAll.add(cursor.getString(2));
        }
            while (cursor.moveToNext()) ;
        getModels = listAll.stream().distinct().collect(Collectors.toList());
        getModels.add(0,"Add objects to compare");


        return getModels;

    }
}