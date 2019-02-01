package com.example.shield.complaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;

public class Complaintmain extends AppCompatActivity {
    private RadioGroup hostelordepartmentcategory;
    private RadioGroup hosteladvance;
    private RadioGroup departmentadvance;
    private RadioGroup problem;
    private EditText comment;
    private Button register;
    private RadioButton hostelordepartmentbutton;
    private RadioButton hosteladvancebutton;
    private RadioButton departmentadvancebutton;
    private RadioButton problembutton;
    FirebaseAuth firebaseAuth;
    DatabaseReference mdatabase;
    HashMap<String,String> data=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintmain);
        firebaseAuth=FirebaseAuth.getInstance();
        register=(Button)findViewById(R.id.registercomplaint);
        comment=(EditText)findViewById(R.id.complaintid);
        hostelordepartmentcategory=(RadioGroup)findViewById(R.id.categoryradiogroupid);
        hosteladvance=(RadioGroup)findViewById(R.id.hosteladvance);
        problem=(RadioGroup)findViewById(R.id.plumberradiogroupid);
        departmentadvance=(RadioGroup)findViewById(R.id.departmentadvanceid);
        String userid=firebaseAuth.getCurrentUser().getUid();
        mdatabase=FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
        setHostel(hostelordepartmentcategory);
        setProblem(problem);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdatabase.child("Status:").setValue("0");
                Toast.makeText(Complaintmain.this,"Complaint Registered Succesfully",Toast.LENGTH_LONG).show();
                mdatabase.child("Comment").setValue(comment.getText().toString());
                startActivity(new Intent(Complaintmain.this,Profile.class));
            }
        });
       // mdatabase.setValue(data);
    }

    public void setHosteladvance(final RadioGroup radioGroup)
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioid=radioGroup.getCheckedRadioButtonId();
                hosteladvancebutton=(RadioButton)findViewById(radioid);
                mdatabase.child("Category").setValue(hosteladvancebutton.getText().toString());
            }
        });
    }
    public void setDepartmentadvance(final RadioGroup radioGroup)
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioid=radioGroup.getCheckedRadioButtonId();
                departmentadvancebutton=(RadioButton)findViewById(radioid);
                mdatabase.child("Category").setValue(departmentadvancebutton.getText().toString());
            }
        });
    }
    public void setHostel(final RadioGroup radioGroup)
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioid=radioGroup.getCheckedRadioButtonId();
                hostelordepartmentbutton=(RadioButton)findViewById(radioid);
                if(hostelordepartmentbutton.getText().equals("Hostel"))
                {
                    data.put("Place","Hostel");
                    mdatabase.child("Place").setValue("Hostel");
                    hosteladvance.setVisibility(View.VISIBLE);
                    setHosteladvance(hosteladvance);
                    departmentadvance.setVisibility(View.GONE);
                }
                if(hostelordepartmentbutton.getText().equals("Department"))
                {  data.put("Place","Department");
                    mdatabase.child("Place").setValue("Department");
                    departmentadvance.setVisibility(View.VISIBLE);
                    setDepartmentadvance(departmentadvance);
                    hosteladvance.setVisibility(View.GONE);
                }

            }
        });
    }
    public void setProblem(final RadioGroup radioGroup)
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioid=radioGroup.getCheckedRadioButtonId();
                problembutton=(RadioButton)findViewById(radioid);
                mdatabase.child("Problem").setValue(problembutton.getText().toString());
            }
        });
    }




}
