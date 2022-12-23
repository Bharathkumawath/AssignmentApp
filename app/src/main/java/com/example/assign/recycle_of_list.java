package com.example.assign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycle_of_list extends RecyclerView.Adapter<recycle_of_list.ViewHolder> {
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> surname=new ArrayList<>();
    Context context;
    String workname;
     recycle_of_list(ArrayList<String> name,ArrayList<String> surname,Context context,String workname){
         this.name=name;
         this.surname=surname;
         this.context=context;
         this.workname=workname;
     }
    @NonNull
    @Override
    public recycle_of_list.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_of_list,parent,false);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull recycle_of_list.ViewHolder holder, int position) {
       holder.n.setText(name.get(position));
        holder.sur.setText(surname.get(position));
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView n,sur;
         ArrayList<String> array=new ArrayList<>();
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            n=itemView.findViewById(R.id.name);
            sur=itemView.findViewById(R.id.surname);
            n.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    array.add(name.get(getAdapterPosition()));
                    array.add(surname.get(getAdapterPosition()));
                    array.add(workname);
                    Intent intent=new Intent(context,profile_details.class);
                    intent.putExtra("bharath",array);
                    context.startActivity(intent);

                }
            });
        }
    }
}
