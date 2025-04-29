package com.example.myform;

import android.content.Context;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class Eyob extends ArrayAdapter<file> {
    private List<file> people;
    private int currentPosition;
    private ActionMode actionMode;
    private Context context;
    private TextView name, email, password, date;
    private ConstraintLayout cl;

    public Eyob(Context context, List<file> people){
        super(context,0,people);
        this.people =people;
        this.context=context;
    }
    public void onClick(int position){

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        file File=people.get(position);

        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.activity_my_custom_list, parent,false);
        }
        name=convertView.findViewById(R.id.name);
        name.setText(File.Full_name);
        email=convertView.findViewById(R.id.email);
        email.setText(File.Email);
        date=convertView.findViewById(R.id.date);
        date.setText(File.Calendar);
        password=convertView.findViewById(R.id.password);
        password.setText(File.Password);
        cl=convertView.findViewById(R.id.main_layout_custom);
        cl.setOnLongClickListener(v->{
            currentPosition=position;
            if(actionMode!=null) return false;

            actionMode= cl.startActionMode(actionmodecallback);
            v.setSelected(true);
////            onClick(position);
            return true;
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
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
                actionMode=null;
        }
    };
}
