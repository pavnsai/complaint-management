package com.example.shield.complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {
    //private TextView useremail;
    private Button logout;
    private Button feedback;
    private Button profile;
    private Button complaint;
    private Button status;
    private Button aboutus;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //useremail=(TextView)findViewById(R.id.emailtextid);
        logout=(Button)findViewById(R.id.buttonlogout);
        aboutus=(Button)findViewById(R.id.buttonaboutus);
        status=(Button)findViewById(R.id.buttonstatus);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Status.class));
            }
        });
        complaint=(Button)findViewById(R.id.buttoncomplaint);
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(Profile.this,Complaintmain.class));
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Aboutus.class));
            }
        });
        profile=(Button)findViewById(R.id.buttonprofile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Editprofile.class));
            }
        });
        feedback=(Button)findViewById(R.id.buttonfeedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this,Feedback.class));
            }
        });
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        //useremail.setText(firebaseUser.getEmail());
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Profile.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}
