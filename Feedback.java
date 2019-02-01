package com.example.shield.complaint;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {
private EditText messagetype;
private Button submitfeedback;
private EditText subjectfeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        messagetype=(EditText)findViewById(R.id.feedbackmessageid);
        submitfeedback=(Button)findViewById(R.id.feedbacksubmitid);
        subjectfeedback=(EditText)findViewById(R.id.feedbacksubjecteditid);
        submitfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmail();
            }
        });
    }
    private void sendmail()
    {
        String recepient="pavansai18.a@gmail.com";
        String message=messagetype.getText().toString();
        String subject=subjectfeedback.getText().toString();

        Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",recepient,null));
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        //intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }
}
