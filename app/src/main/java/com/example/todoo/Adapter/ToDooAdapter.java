package com.example.todoo.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoo.MainActivity;
import com.example.todoo.Model.ToDooModel;
import com.example.todoo.R;

import java.util.List;

public class ToDooAdapter extends RecyclerView.Adapter<ToDooAdapter.ViewHolder> {

    private List<ToDooModel> todooList;
    private MainActivity activity;

    public ToDooAdapter(MainActivity activity){
        this.activity=activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout,parent,false);
        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        ToDooModel item = todooList.get(position);
        holder.task.setText(item.getTask());
        holder.task.setChecked(toBoolean(item.getStatus()));
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
    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox task;
        ViewHolder(View view){
            super(view);
            task=view.findViewById(R.id.todoCheckBox);
        }
    }
}
