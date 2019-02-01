package com.example.shield.complaint;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Status extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private DatabaseReference mdatabase;
    private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        firebaseAuth=FirebaseAuth.getInstance();
        status=(TextView)findViewById(R.id.textviewid);
        String userid=firebaseAuth.getCurrentUser().getUid();
        mdatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("Status:");
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString().equals("0"))
                {
                    status.setText("Complaint under Processing....");
                }
                if(dataSnapshot.getValue().toString().equals("1"))
                {
                    status.setText("Your Complaint is solved");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
