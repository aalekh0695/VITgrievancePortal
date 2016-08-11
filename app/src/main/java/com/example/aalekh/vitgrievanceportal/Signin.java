package com.example.aalekh.vitgrievanceportal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signin extends AppCompatActivity {
    EditText etUname,etUpwd;
    String uname,upwd,method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        etUname = (EditText)findViewById(R.id.etUname);
        etUpwd = (EditText)findViewById(R.id.etUpwd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signin, menu);
        return true;
    }
    public void signinT(View v){
        uname = etUname.getText().toString();
        upwd = etUpwd.getText().toString();
        method="login";
        BackgroundTask backgroundTask = new BackgroundTask(this,Signin.this);
        backgroundTask.execute(method, uname, upwd);

        //startActivity(new Intent(Signin.this, Homepage.class));
    }


}
