package com.example.myform;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class d extends AppCompatActivity {

    private Gson gson;

    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson=new Gson();
        prefs= PreferenceManager.getDefaultSharedPreferences(this);

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
//    public void addImage(Uri imageUri){
//        String a=imageUri.toString();
//        List<file> list;
//        String string=prefs.getString("file",null);
//        if(string!=null){
//           list=gson.fromJson(string,new TypeToken<List<file>>(){}.getType());
//           if(list != null && !list.isEmpty()){
//               list.get(list.size()-1).Picture=a;
//               prefs.edit().putString("file",gson.toJson(list)).apply();
//           }
//        }
//    }
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
    public void deletefile(){
        prefs.edit().clear().apply();
    }
    protected List<file> getFromFile(){
        String json=prefs.getString("file",null);
        List<file> getfile=new ArrayList<>();
        if(json!=null){
            getfile=gson.fromJson(json,new TypeToken<List<file>>(){}.getType());
        }
        return getfile;
    }

}

