package com.example.todoo.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoo.AddNewTask;
import com.example.todoo.MainActivity;
import com.example.todoo.Model.ToDooModel;
import com.example.todoo.R;
import com.example.todoo.Utils.DatabaseHandler;

import java.util.List;

public class ToDooAdapter extends RecyclerView.Adapter<ToDooAdapter.ViewHolder> {

    private List<ToDooModel> todooList;
    private MainActivity activity;
    private DatabaseHandler db;

    public ToDooAdapter(DatabaseHandler db, MainActivity activity){
        this.db=db;
        this.activity=activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout,parent,false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        db.openDatabase();
        ToDooModel item = todooList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
        holder.task.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(),1);
                }
                else{
                    db.updateStatus(item.getId(),0);
                }
            }
        });
    }

    public int getItemCount(){
        return todooList.size();
    }

    private boolean toBoolean(int n){
        return n!=0;
    }

    public void setTask(List<ToDooModel> todooList){
        this.todooList=todooList;
        notifyDataSetChanged();
    }
    public Context getContext(){
        return activity;
    }

    public void editItem(int position){
        ToDooModel item = todooList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id",item.getId());
        bundle.putString("task",item.getTask());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View view){
            super(view);
            task=view.findViewById(R.id.todoCheckBox);
        }
    }
}
