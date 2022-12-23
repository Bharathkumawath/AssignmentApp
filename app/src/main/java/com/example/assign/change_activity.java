package com.example.assign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class change_activity extends AppCompatActivity {
    EditText prize,time;
    TextView name,work,status;
    Button decline,change,let;
    FirebaseDatabase database;
    DatabaseReference ref;
    String current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Intent intent=getIntent();
        prize=findViewById(R.id.prizetag);
        time=findViewById(R.id.timetag);
        name=findViewById(R.id.name);
        work=findViewById(R.id.workname);
        status=findViewById(R.id.status);
        decline=findViewById(R.id.decline);
        change=findViewById(R.id.change);
        let=findViewById(R.id.lets_work);
        ArrayList<String> arrayList=intent.getStringArrayListExtra("bharath");
        prize.setText(arrayList.get(1));
        time.setText(arrayList.get(4));
        name.setText(arrayList.get(3));
        work.setText(arrayList.get(0));
        status.setText(arrayList.get(2));
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("users");
        current=arrayList.get(5);
        ref.child(current).child("task").child(name.getText().toString()).child(work.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(dataSnapshot.getValue().toString().equals("pending"))
                    {
                        change.setEnabled(false);
                        decline.setEnabled(true);
                        let.setEnabled(false);
                    }
                    else if(dataSnapshot.getValue().toString().equals("decline"))
                    {
                        change.setEnabled(false);
                        decline.setEnabled(false);
                        let.setEnabled(false);
                    }
                    else if(dataSnapshot.getValue().toString().equals("final_call_from_them"))
                    {
                       let.setText("final_call_from_them");
                    }
                    else if(dataSnapshot.getValue().toString().equals("me_ok_waiting_other"))
                    {
                        let.setText("waiting_Response");
                        let.setEnabled(false);
                    }
                    else if(dataSnapshot.getValue().toString().equals("Dealed"))
                    {
                        let.setText("Dealed");
                        change.setEnabled(false);
                        decline.setEnabled(false);
                        let.setEnabled(false);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ref.child(current).child("task").child(name.getText().toString()).child(work.getText().toString()).child("status").setValue("decline");
                ref.child(name.getText().toString()).child("task").child(current).child(work.getText().toString()).child("status").setValue("decline");

            }
        });
        let.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (let.getText().toString().equals("final_call_from_them")) {
                    ref.child(current).child("task").child(name.getText().toString()).child(work.getText().toString()).child("status").setValue("Dealed");
                    ref.child(name.getText().toString()).child("task").child(current).child(work.getText().toString()).child("status").setValue("Dealed");
                    let.setText("Dealed");
                    let.setEnabled(false);
                }
                else {
                    ref.child(current).child("task").child(name.getText().toString()).child(work.getText().toString()).child("status").setValue("me_ok_waiting_other");
                    ref.child(name.getText().toString()).child("task").child(current).child(work.getText().toString()).child("status").setValue("final_call_from_them");
                    let.setText("waiting_Response");
                    let.setEnabled(false);
                }
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref.child(current).child("task").child(name.getText().toString()).child(work.getText().toString()).child("prize").setValue(prize.getText().toString());
                ref.child(current).child("task").child(name.getText().toString()).child(work.getText().toString()).child("time").setValue(time.getText().toString());
                ref.child(current).child("task").child(name.getText().toString()).child(work.getText().toString()).child("status").setValue("changed by me");
                ref.child(name.getText().toString()).child("task").child(current).child(work.getText().toString()).child("prize").setValue(prize.getText().toString());
                ref.child(name.getText().toString()).child("task").child(current).child(work.getText().toString()).child("time").setValue(time.getText().toString());
                ref.child(name.getText().toString()).child("task").child(current).child(work.getText().toString()).child("status").setValue("changed by them");

            }
        });
    }
}