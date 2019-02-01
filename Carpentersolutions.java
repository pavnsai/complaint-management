package com.example.shield.complaint;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Carpentersolutions extends AppCompatActivity {
    String rollno;
    String comment;
    String category;
    String place;
    String problem;
    ListView listview;
    FirebaseAuth firebaseAuth;
    DatabaseReference mdatabase,mDatabase2;
    static ArrayList<String > arrayList=new ArrayList<String>();
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);
        firebaseAuth = FirebaseAuth.getInstance();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        listview = (ListView) findViewById(R.id.listview);
        category = getIntent().getStringExtra("category");
        place = getIntent().getStringExtra("place");
        problem = getIntent().getStringExtra("problem");
        comment = getIntent().getStringExtra("comment");
        rollno = getIntent().getStringExtra("rollno");
        arrayList.add(rollno);
        arrayList.add(place);
        arrayList.add(category);
        arrayList.add(comment);
        arrayList.add(problem);
        arrayList.add("Is Problem Solved?");
        adapter = new ArrayAdapter<String>(Carpentersolutions.this, android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp = arrayList.get(position);
                if (temp.equals("Is Problem Solved?")) {
                    //Toast.makeText(Solutions.this,"hi",Toast.LENGTH_LONG).show();
                    mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        int i=0;
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data : dataSnapshot.getChildren()) {

                                //Toast.makeText(Plumber.this,data.child("Name").getValue(String.class),Toast.LENGTH_LONG).show();
                                if (data.child("Rollno:").getValue(String.class).equals(rollno) && (data.child("Status:").getValue(String.class).equals("0"))) {
                                    Toast.makeText(getApplicationContext(),"Complaint solved",Toast.LENGTH_LONG).show();
                                    String userid=data.getKey();
                                    mDatabase2=FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                                    mDatabase2.child("Status:").setValue("1");


                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }
        });
    }
}
