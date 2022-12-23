package com.example.assign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class profile_details extends AppCompatActivity {
    ArrayList<String> arrayList=new ArrayList<>();
    TextView name,surname;
    FirebaseDatabase database;
    DatabaseReference ref;
    EditText e1,e2;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);
        Intent intent=getIntent();
        arrayList=intent.getStringArrayListExtra("bharath");
        name=findViewById(R.id.name);
        surname=findViewById(R.id.surname);
        e1=findViewById(R.id.prizetag);
        e2=findViewById(R.id.timetag);
        b1=findViewById(R.id.assign);
        name.setText(arrayList.get(0));
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("users");
        surname.setText(arrayList.get(1));
        Toast.makeText(profile_details.this, "coming"+arrayList.get(1), Toast.LENGTH_SHORT).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child("bharath").child("task").child(arrayList.get(0)).child(arrayList.get(2)).child("prize").setValue(e1.getText().toString());
                ref.child("bharath").child("task").child(arrayList.get(0)).child(arrayList.get(2)).child("time").setValue(e2.getText().toString());
                ref.child("bharath").child("task").child(arrayList.get(0)).child(arrayList.get(2)).child("status").setValue("pending");
                ref.child(arrayList.get(0)).child("task").child("bharath").child(arrayList.get(2)).child("prize").setValue(e1.getText().toString());
                ref.child(arrayList.get(0)).child("task").child("bharath").child(arrayList.get(2)).child("time").setValue(e2.getText().toString());
                ref.child(arrayList.get(0)).child("task").child("bharath").child(arrayList.get(2)).child("status").setValue("Requested");
               Intent intent=new Intent(profile_details.this,MainActivity.class);
               startActivity(intent);
            }
        });
    }



}
