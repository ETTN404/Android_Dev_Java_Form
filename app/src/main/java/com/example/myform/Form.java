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
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myform.databinding.ActivityFormBinding;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Form extends d {
    ActivityFormBinding b;
    Calendar calendar=Calendar.getInstance();
    String Tdate,Tpass,Tmail,Tname,CheckLanguage;
    MyAppLa myAppLa;
    private Intent i;
    Uri oldImage=null;
    Uri cameraImage;
    File photoFile;
    String uriString;
    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
    result -> {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                oldImage = data.getData();
//                assert oldImage != null;
                uriString = oldImage.toString();
                b.isuploaded.setText(uriString);
            }
        }
    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        EdgeToEdge.enable(this);
        b=ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0);
            return insets;
        });
        b.back.setOnClickListener(v->{
            finish();
        });
        b.upload.setOnClickListener(v->{
            Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,20);
        });



        b.Date.setOnClickListener(v->dateformat(calendar,this,b.Date));
        b.register.setOnClickListener(v->startActivity(new Intent(this,display.class)));

        b.register.setOnLongClickListener(v->{
            Tdate= b.Date.getText().toString();
            Tmail=b.email.getText().toString();
            Tpass=b.password.getText().toString();
            Tname=b.name.getText().toString();
            file File;
            if(uriString!=null){
                File=new file(Tname,Tmail,Tdate,Tpass,uriString);
            }
            else{
                File=new file(Tname,Tmail,Tdate,Tpass,null);
            }
            putToFile(File,"file");
            Toast.makeText(this, "your data have been saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,display.class));
            return true;
        });







        b.upload.setOnLongClickListener(v->{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Get image").
                    setMessage("choose Gallery to choose from gallery and Camera to take a photo").
                    setPositiveButton("Gallery",(dialog, which) -> {
                        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        galleryLauncher.launch(galleryIntent);}).
                    setNegativeButton("Camera",(dialog, which) -> {
                        File imagefile=new File(getExternalFilesDir(null),
                                "photo_"+System.currentTimeMillis()+".jpg");
                        cameraImage=FileProvider.getUriForFile(
                                this,
                                getApplicationContext().getPackageName()+".provider",
                                imagefile);
                        cameralauncher.launch(cameraImage);
                    }).show();
            return true;
        });







    }


ActivityResultLauncher<Uri> cameralauncher= registerForActivityResult(
        new ActivityResultContracts.TakePicture(),result->{
            if(result){
                oldImage=cameraImage;
//                assert oldImage != null;
                uriString = oldImage.toString();
                b.isuploaded.setText(uriString);
            }
        }

);

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


    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

}