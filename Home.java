package com.example.shield.complaint;

import android.app.Application;
import android.content.Intent;
import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        {
            if(firebaseUser!=null && firebaseUser.isEmailVerified())
            {
                startActivity(new Intent(Home.this,Profile.class));
            }
        }
    }

}
