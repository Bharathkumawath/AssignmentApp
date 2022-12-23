package com.example.assign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list_of_profiles extends AppCompatActivity {
     RecyclerView recyclerView;
     FirebaseDatabase database;
     DatabaseReference ref;
    String workname;
     ArrayList<String> name=new ArrayList<>();
    ArrayList<String> surname=new ArrayList<>();
    recycle_of_list adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_profiles);
        recyclerView=findViewById(R.id.recyclerView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("users");
        Intent intent=getIntent();
        workname=intent.getStringExtra("bharath");
        name.clear();
        surname.clear();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        if(dataSnapshot1.getKey().equals("name")){
                            name.add(dataSnapshot1.getValue().toString());
                        }
                        else  if(dataSnapshot1.getKey().equals("surname")){
                            surname.add(dataSnapshot1.getValue().toString());
                        }
                        }

                }
                adapter = new recycle_of_list(name,surname,list_of_profiles.this,workname);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
        adapter = new recycle_of_list(name,surname,list_of_profiles.this,workname);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}