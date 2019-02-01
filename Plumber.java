package com.example.shield.complaint;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Plumber extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    DatabaseReference mdatabase;
    ArrayList<String > arrayList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plumber);
        firebaseAuth=FirebaseAuth.getInstance();
        mdatabase=FirebaseDatabase.getInstance().getReference().child("Users");
        mdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren())
                {
                    //Toast.makeText(Plumber.this,data.child("Name").getValue(String.class),Toast.LENGTH_LONG).show();
                    if(data.child("Status:").getValue(String.class).equals("0")&&(data.child("Problem").getValue(String.class).equals("Plumber")))
                    {
                        Intent i=new Intent(Plumber.this,Solutions.class);
                        i.putExtra("rollno",data.child("Rollno:").getValue().toString());
                        i.putExtra("category",data.child("Category").getValue().toString());
                        i.putExtra("comment",data.child("Comment").getValue().toString());
                        i.putExtra("problem",data.child("Problem").getValue().toString());
                        i.putExtra("place",data.child("Place").getValue().toString());
                        startActivity(i);
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
