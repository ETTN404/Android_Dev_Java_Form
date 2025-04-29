package com.example.myform;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myform.databinding.ActivityDisplayBinding;

import java.util.ArrayList;
import java.util.List;

public class display extends d {
    ActivityDisplayBinding b;
    List<file> file;
    Eyob adapterCustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b=ActivityDisplayBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        file=getFromFile("file");
        adapterCustom=new Eyob(this,file){
            @Override
            public void onClick(int position) {
                deleteFile(position);
                file.remove(position);
                adapterCustom.notifyDataSetChanged();
            }
        };
        b.list.setAdapter(adapterCustom);





    }
}