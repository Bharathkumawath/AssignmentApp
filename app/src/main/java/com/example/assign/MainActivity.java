package com.example.assign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    String user;
    EditText current;
    Button update;
    DatabaseReference ref;
    work_object object;
    ArrayList<work_object> arrayList=new ArrayList<>();
    recycle_work_list adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.workname);
        button=findViewById(R.id.submit);
        recyclerView=findViewById(R.id.recyclerView);
        current=findViewById(R.id.current);
        update=findViewById(R.id.update);
        database=FirebaseDatabase.getInstance();

        user=current.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,list_of_profiles.class);
                intent.putExtra("bharath",editText.getText().toString());
                startActivity(intent);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                      DataExtract();
            }
        });
//        adapter = new recycle_work_list(arrayList,MainActivity.this,current.getText().toString());
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
    }
    void DataExtract(){

         user=current.getText().toString();
        ref=database.getReference("users").child(user).child("task");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList.clear();

                Toast.makeText(MainActivity.this, ""+1, Toast.LENGTH_SHORT).show();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    object=new work_object();
                    object.setName(dataSnapshot.getKey().toString());
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        object.setWork_name(dataSnapshot1.getKey().toString());
                        for (DataSnapshot dataSnapshot2:dataSnapshot1.getChildren()){
                            if(dataSnapshot2.getKey().equals("prize")){
                                object.setPrize(dataSnapshot2.getValue().toString());
                            }
                            else  if(dataSnapshot2.getKey().equals("time")){
                                object.setTime(dataSnapshot2.getValue().toString());
                            }
                            else  if(dataSnapshot2.getKey().equals("status")){
                                object.setStatus(dataSnapshot2.getValue().toString());
                            }

                        }
                        arrayList.add(object);
                        object=new work_object();
                        object.setName(dataSnapshot.getKey().toString());
                    }


                }
                adapter = new recycle_work_list(arrayList,MainActivity.this,current.getText().toString());
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }
}