package com.example.shield.complaint;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity {
    private EditText email;
    private Button forgot;
    private ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        email=(EditText)findViewById(R.id.emailid);
        forgot=(Button)findViewById(R.id.buttonid);
        progressBar=(ProgressBar)findViewById(R.id.progressBarid);
        firebaseAuth=FirebaseAuth.getInstance();
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            Toast.makeText(ForgotActivity.this,"Password sent to your Email",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ForgotActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
