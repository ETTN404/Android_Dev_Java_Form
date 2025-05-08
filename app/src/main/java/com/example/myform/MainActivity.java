package com.example.myform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myform.databinding.ActivityMainBinding;

public class MainActivity extends d {
    ConstraintLayout d;
    ActivityMainBinding b;
    String CheckLanguage="";
    MyAppLa myAppLa=new MyAppLa();

    @Override
    protected void attachBaseContext(Context newBase) {
        CheckLanguage=myAppLa.get_lan("language",newBase);
        super.attachBaseContext(MyAppLa.Language(newBase,CheckLanguage));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!CheckLanguage.equals(myAppLa.get_lan("language",this))){recreate();}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        b=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right,0);
            return insets;
        });
        b.GetStarted.setOnClickListener(v -> {
            startActivity(new Intent(this, Form.class));
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}