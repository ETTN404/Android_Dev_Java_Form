package com.example.myform;

import android.app.ComponentCaller;
import android.app.DatePickerDialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myform.databinding.ActivityFormBinding;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

public class Form extends d {
    ActivityFormBinding b;
    Calendar calendar=Calendar.getInstance();
    String Tdate,Tpass,Tmail,Tname,CheckLanguage;
    MyAppLa myAppLa;
    private Intent i;
    Uri oldImage=null;
    String uriString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        b.back.setOnClickListener(v->{
            finish();
        });
        b.upload.setOnClickListener(v->{
            Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,20);
        });

        b.upload.setOnLongClickListener(v->{

            return true;
        });
        b.back.setOnLongClickListener(v->{
            return true;
        });
        b.Date.setOnClickListener(v->dateformat(calendar,this,b.Date));

        b.register.setOnClickListener(v->{
            Tdate= b.Date.getText().toString();
            Tmail=b.email.getText().toString();
            Tpass=b.password.getText().toString();
            Tname=b.name.getText().toString();
            file File;
            if(oldImage!=null && uriString!=null){
                File=new file(Tname,Tmail,Tdate,Tpass,uriString);
            }
                else{
                File=new file(Tname,Tmail,Tdate,Tpass,null);
            }
            putToFile(File,"file");
            Toast.makeText(this, "your data have been saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,display.class));
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && data.getData() != null) {
            oldImage = data.getData();
            uriString = oldImage.toString();
            b.isuploaded.setText(oldImage.toString());
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        myAppLa=new MyAppLa();
        String s=item.getTitle().toString();
        if(s.equals("English")){
            myAppLa.store_lan("en","language",this);
            recreate();
        }
        else  if(s.equals("Amharic")) {
            myAppLa.store_lan("am","language",this);
            recreate();
        }
        else if(s.equals("Arabic")){
            myAppLa.store_lan("ar","language",this);
            recreate();
        }
        else  if(s.equals("Herero")) {
            myAppLa.store_lan("hz","language",this);
            recreate();
        }
        else if(s.equals("Chinese")){
            myAppLa.store_lan("zh","language",this);
            recreate();
        }else  if(s.equals("French")) {
            myAppLa.store_lan("fr","language",this);
            recreate();
        }
        else if(s.equals("Hindi")){
            myAppLa.store_lan("hi","language",this);
            recreate();
        }
        return true;
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        myAppLa=new MyAppLa();
        CheckLanguage=myAppLa.get_lan("language",newBase);
        super.attachBaseContext(MyAppLa.Language(newBase,CheckLanguage));
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!CheckLanguage.equals(myAppLa.get_lan("language",this))){
            recreate();
        }
    }

}