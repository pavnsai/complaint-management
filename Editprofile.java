package com.example.shield.complaint;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Editprofile extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private TextView rollno;
    private TextView name;
    private TextView email;
    private DatabaseReference mdatabase,mdatabasename,mdatabaseemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        rollno=(TextView) findViewById(R.id.rollnoid);
        name=(TextView) findViewById(R.id.nameid);
        email=(TextView) findViewById(R.id.emailid);
        firebaseAuth=FirebaseAuth.getInstance();
        String userid=firebaseAuth.getCurrentUser().getUid();
        mdatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("Rollno:");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String roll=dataSnapshot.getValue().toString();
                rollno.setText(roll);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdatabasename=FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("Name:");
        mdatabasename.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username=dataSnapshot.getValue().toString();
                name.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mdatabaseemail=FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("Email:");
        mdatabaseemail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String emailaddress=dataSnapshot.getValue().toString();
                email.setText(emailaddress);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
