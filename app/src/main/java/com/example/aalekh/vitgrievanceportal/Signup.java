package com.example.aalekh.vitgrievanceportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Signup extends AppCompatActivity {
    EditText etfname,etlname,etuname,etemail,etpwd,etconfirmpwd,etphone1,etphone2,etroom;
    String fname,lname,uname,email,pwd,confirmpwd,phone1,phone2,room,hostel;
    String method;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spinner = (Spinner)findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this,R.array.hostel,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        init();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hostel=spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void init(){
        etfname = (EditText)findViewById(R.id.fname);
        etlname = (EditText)findViewById(R.id.lname);
        etuname = (EditText)findViewById(R.id.uname);
        etemail = (EditText)findViewById(R.id.email);
        etpwd = (EditText)findViewById(R.id.pwd);
        etconfirmpwd = (EditText)findViewById(R.id.confirmpwd);
        etphone1 = (EditText)findViewById(R.id.phone1);
        etphone2 = (EditText)findViewById(R.id.phone2);
        etroom = (EditText)findViewById(R.id.roomno);
        return;
    }

    public void signupT(View v){
        fname = etfname.getText().toString();
        lname = etlname.getText().toString();
        uname = etuname.getText().toString();
        email = etemail.getText().toString();
        pwd = etpwd.getText().toString();
        confirmpwd = etconfirmpwd.getText().toString();
        phone1 = etphone1.getText().toString();
        phone2 = etphone2.getText().toString();
        room = etroom.getText().toString();
        Log.d("Data", fname + "," + lname + "," + uname + "," + email + "," + pwd + "," + confirmpwd + "," + phone1 + "," + phone2 + "," + hostel + "," + room);
        method="signup";
        BackgroundTask backgroundTask = new BackgroundTask(this,Signup.this);
        backgroundTask.execute(method,fname,lname,uname,email,pwd,phone1,phone2,hostel,room);
        //Intent intent = new Intent(Signup.this,Homepage.class);
        //finish();
        //startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
