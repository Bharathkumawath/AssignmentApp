package com.example.assign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle_work_list extends RecyclerView.Adapter<recycle_work_list.ViewHolder> {
    ArrayList<work_object> name=new ArrayList<>();
    Context context;
    String current;
    recycle_work_list(ArrayList<work_object> name,Context context,String current){
        this.name=name;
        this.current=current;
        this.context=context;
    }
    @NonNull
    @Override
    public recycle_work_list.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_work_list,parent,false);
        return new recycle_work_list.ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull recycle_work_list.ViewHolder holder, int position) {
           holder.work.setText(name.get(position).getWork_name());
        holder.prize.setText(name.get(position).getPrize());
        holder.time.setText(name.get(position).getTime());
        holder.status.setText(name.get(position).getStatus());
        holder.user.setText(name.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView work,prize,time,user,status;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            work=itemView.findViewById(R.id.workname);
            prize=itemView.findViewById(R.id.prize);
            time=itemView.findViewById(R.id.time);
            user=itemView.findViewById(R.id.name);
            status=itemView.findViewById(R.id.status);
            constraintLayout=itemView.findViewById(R.id.con);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // work_object obj=new work_object();
                    ArrayList<String> array=new ArrayList<>();
                   array.add(work.getText().toString());
                    array.add(prize.getText().toString());
                    array.add(status.getText().toString());
                    array.add(user.getText().toString());
                    array.add(time.getText().toString());
                    array.add(current);
                    Intent intent=new Intent(context,change_activity.class);
                    intent.putExtra("bharath",array);
                    context.startActivity(intent);
                }
            });
        }
    }
}
