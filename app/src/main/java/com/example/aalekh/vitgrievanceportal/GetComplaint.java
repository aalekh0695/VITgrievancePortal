package com.example.aalekh.vitgrievanceportal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetComplaint extends AppCompatActivity {
    EditText etSubject,etComplaint;
    String subject,description,department,uname,method;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_complaint);
        Intent intent = this.getIntent();
        if(intent!=null){
            department = intent.getExtras().getString("data");
            Log.d("dept",department);
        }

        SharedPreferences sharedPreferences = GetComplaint.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        uname= sharedPreferences.getString(getString(R.string.USERNAME),"");
        Log.d("username",uname);

        etSubject = (EditText)findViewById(R.id.etSubject);
        etComplaint = (EditText)findViewById(R.id.etComplaint);
        //send complaint data to the database...
    }

    public void postComplaint(View v){
        subject = etSubject.getText().toString();
        description=etComplaint.getText().toString();

        method = "postcomplaint";
        BackgroundTask backgroundTask = new BackgroundTask(this,GetComplaint.this);
        backgroundTask.execute(method,uname,subject,description,department);
        finish();
    }
}
