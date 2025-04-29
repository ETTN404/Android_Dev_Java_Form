package com.example.myform;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class d extends AppCompatActivity {

    private Gson gson;

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson=new Gson();
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        // Apply transparent status bar
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // hide layout behind bottom nav bar too
//            );
//            window.setStatusBarColor(Color.TRANSPARENT); // top bar
//            window.setNavigationBarColor(Color.TRANSPARENT); // bottom nav bar
//        }
    }
    protected void dateformat(Calendar calendar, Context context, TextView view){
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog ddd=new DatePickerDialog(this,(v, y, m, da)->{
            calendar.set(y,m,da);
            view.setText(da+"/"+(m+1)+"/"+y);
        },year,month,day);
        ddd.show();
    }
    protected boolean putToFile(file obj,String string){

        String fileString=prefs.getString(string,null);
        List<file> fileObjectArray;
        if(fileString==null){
            fileObjectArray=new ArrayList<>();
        }
        else{
            fileObjectArray=gson.fromJson(fileString,new TypeToken<List<file>>(){}.getType());
            if(fileObjectArray==null){
                fileObjectArray=new ArrayList<>();
            }
        }
        fileObjectArray.add(obj);
        prefs.edit().putString(string,gson.toJson(fileObjectArray)).apply();
        return true;
    }
    protected void deleteFile(int position){
        String json=prefs.getString("file",null);
        if(json!=null){
            List<file> fileList=gson.fromJson(json,new TypeToken<List<file>>(){}.getType());

            if(fileList!=null&&position<fileList.size()){
                fileList.remove(position);
                prefs.edit().putString("file",gson.toJson(fileList)).apply();
            }
        }

    }
    protected List<file> getFromFile(String string){
        String json=prefs.getString(string,null);
        List<file> getfile=new ArrayList<>();
        if(json!=null){
            getfile=gson.fromJson(json,new TypeToken<List<file>>(){}.getType());
        }
        return getfile;
    }
}



// Apply to fully hide the status and bottom nav bar

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            );
//            window.setStatusBarColor(Color.TRANSPARENT);
//            window.setNavigationBarColor(Color.TRANSPARENT);
//        }
