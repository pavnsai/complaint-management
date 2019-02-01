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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button signupbutton;
    private EditText emailtext;
    private EditText passwordtext;
    private TextView alternate;
    private ProgressBar progressBar;
    private EditText rollno;
    private EditText name;
    private EditText confirmpasswordtext;

    FirebaseAuth firebaseAuth;
    //FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signupbutton=(Button)findViewById(R.id.buttonid);
        rollno=(EditText)findViewById(R.id.rollnoid);
        name=(EditText)findViewById(R.id.nameid);
        emailtext=(EditText)findViewById(R.id.emailid);
        passwordtext=(EditText)findViewById(R.id.passwordid);
        confirmpasswordtext=(EditText)findViewById(R.id.confirmpasswordid);
        alternate=(TextView)findViewById(R.id.gotologinid);
        progressBar=(ProgressBar)findViewById(R.id.progressBarid);
        firebaseAuth=FirebaseAuth.getInstance();
        //user=firebaseAuth.getCurrentUser();
        signupbutton.setOnClickListener(new View.OnClickListener() {
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
                if(rollno.getText().toString().isEmpty())
                {
                    rollno.setError("Roll no is required");
                    rollno.requestFocus();
                    return;
                }
                if(name.getText().toString().isEmpty())
                {
                    name.setError("Name is required");
                    name.requestFocus();
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
                if(!passwordtext.getText().toString().equals(confirmpasswordtext.getText().toString()))
                {
                    confirmpasswordtext.setError("Passwords didn't match.Try again");
                    confirmpasswordtext.requestFocus();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(emailtext.getText().toString(),passwordtext.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(task.isSuccessful())
                        {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        String userid=firebaseAuth.getCurrentUser().getUid();
                                        Toast.makeText(MainActivity.this, "Registered succesfully.Please check your email for verification", Toast.LENGTH_LONG).show();
                                        DatabaseReference mdatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                                        HashMap<String,String> data=new HashMap<>();
                                        data.put("Rollno:",rollno.getText().toString());
                                        data.put("Name:",name.getText().toString());
                                        data.put("Email:",emailtext.getText().toString());
                                        //data.put("Status:","0");
                                        mdatabase.setValue(data);
                                        emailtext.setText("");
                                        passwordtext.setText("");
                                        confirmpasswordtext.setText("");
                                        rollno.setText("");
                                        name.setText("");

                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
        alternate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });
    }
}
