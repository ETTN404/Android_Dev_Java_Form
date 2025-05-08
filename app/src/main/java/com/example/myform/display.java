package com.example.myform;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myform.databinding.ActivityDisplayBinding;

import java.util.ArrayList;
import java.util.List;

public class display extends d {

    private static final int REQUEST_CAMERA = 101;
    private static final int REQUEST_GALLERY = 102;
    private static final int REQUEST_PERMISSION = 100;

    ActivityDisplayBinding b;
    List<file> file;
    Eyob adapterCustom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        b=ActivityDisplayBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,0);
            return insets;
        });
        file=getFromFile();
        adapterCustom=new Eyob(this,file){
            @Override
            public void onClick(int position) {

                deleteFile(position);
                file.remove(position);
                adapterCustom.notifyDataSetChanged();
            }

            @Override
            public void deleter() {
                deletefile();
                Toast.makeText(display.this, "delete called", Toast.LENGTH_SHORT).show();
                adapterCustom.notifyDataSetChanged();
            }
        };
        b.list.setAdapter(adapterCustom);





    }
}