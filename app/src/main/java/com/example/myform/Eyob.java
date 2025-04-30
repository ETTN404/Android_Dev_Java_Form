package com.example.myform;

import android.content.Context;
import android.net.Uri;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.myform.databinding.MyCustomListBinding;

import java.util.List;

public class Eyob extends ArrayAdapter<file> {
    private List<file> people;
    private int currentPosition;
    private ActionMode actionMode;
    private Context context;

    public Eyob(Context context, List<file> people){
        super(context,0,people);
        this.people =people;
        this.context=context;
    }
    public void onClick(int position){

    }
    public void deleter(){

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        file File=people.get(position);
        MyCustomListBinding binding;

        if(convertView==null){
            LayoutInflater inflate=LayoutInflater.from(getContext());
            binding=MyCustomListBinding.inflate(inflate,parent,false);
            convertView=binding.getRoot();
            convertView.setTag(binding);
        }
        else{
            binding=(MyCustomListBinding)convertView.getTag();
        }

        binding.name.setText(File.Full_name);
        binding.email.setText(File.Email);
        binding.date.setText(File.Calendar);
        binding.password.setText(File.Password);
        if(File.Picture!=null){
            Glide.with(context)
                .load(Uri.parse(File.Picture))
                .into(binding.imageView);
        }
        binding.counter.setText(String.valueOf(position+1));
        binding.layout.setOnLongClickListener(v->{
            currentPosition=position;
            if(actionMode!=null) return false;
            actionMode= binding.layout.startActionMode(actionmodecallback);
            v.setSelected(true);
            return true;
        });
        binding.call.setOnClickListener(v->{
            Toast.makeText(context, "Calling........", Toast.LENGTH_SHORT).show();
        });
        binding.message.setOnClickListener(v->{
            Toast.makeText(context, "Sending........", Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }

    ActionMode.Callback actionmodecallback=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater=mode.getMenuInflater();
            menuInflater.inflate(R.menu.show,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int a=item.getItemId();
            if(a==R.id.action_delete){
                onClick(currentPosition);
            } else if(a==R.id.action_Edit){
                deleter();
            }
            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
                actionMode=null;
        }
    };
}
