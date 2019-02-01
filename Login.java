package com.example.shield.complaint;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private Button loginbutton;
    private EditText emailtext;
    private EditText passwordtext;
    private TextView alternate;
    private ProgressBar progressBar;
    private TextView forgot;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbutton=(Button)findViewById(R.id.buttonid);
        emailtext=(EditText)findViewById(R.id.emailid);
        forgot=(TextView)findViewById(R.id.forgotid);
        passwordtext=(EditText)findViewById(R.id.passwordid);
        alternate=(TextView)findViewById(R.id.gotosignupid);
        progressBar=(ProgressBar)findViewById(R.id.progressBarid);
        firebaseAuth=FirebaseAuth.getInstance();
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgotActivity.class));
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailtext.getText().toString().isEmpty())
                {
                    emailtext.setError("Email is required");
                    emailtext.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(emailtext.getText().toString()).matches())
                {
                    emailtext.setError("Enter valid email");
                    emailtext.requestFocus();
                    return;
                }
                if(passwordtext.getText().toString().isEmpty())
                {
                    passwordtext.setError("Password is required");
                    passwordtext.requestFocus();
                    return;
                }
                if(passwordtext.getText().toString().length()<6)
                {
                    passwordtext.setError("Minimum length is 6");
                    passwordtext.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(emailtext.getText().toString(),passwordtext.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful()) {
                            if (emailtext.getText().toString().equals("pavansai18.a@gmail.com")) {
                                startActivity(new Intent(Login.this, Admin.class));
                                emailtext.setText("");
                                passwordtext.setText("");

                            }
                            else {
                            if (firebaseAuth.getCurrentUser().isEmailVerified()) {
                                startActivity(new Intent(Login.this, Profile.class));
                            } else {
                                Toast.makeText(Login.this, "Please verify your email Address", Toast.LENGTH_LONG).show();
                            }
                        }
                        }
                        else {
                            Toast.makeText(Login.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        alternate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
    }
}
