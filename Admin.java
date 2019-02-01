package com.example.shield.complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Admin extends AppCompatActivity {
    private Button plumber;
    private Button carpenter;
    private Button electrician;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        plumber=(Button)findViewById(R.id.plumberButton);
        electrician=(Button)findViewById(R.id.electricianid);
        logout=(Button)findViewById(R.id.logoutbutton);
        carpenter=(Button)findViewById(R.id.carpenterid);
        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this,Plumber.class));
            }
        });
        electrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this,Electrician.class));
            }
        });
        carpenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin.this,Carpenter.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Admin.this,Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}
