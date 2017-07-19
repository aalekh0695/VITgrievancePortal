package com.example.aalekh.vitgrievanceportal;

/**
 * Created by ashtrayaalekh on 4/14/2016.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    String uname,username;

    Activity activity;
    Context context;
    BackgroundTask(Context context,Activity activity){
        this.context=context;
        this.activity=activity;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Info...");

    }

    @Override
    protected String doInBackground(String... params) {
        String reg_url = "http://10.0.2.2/vit_grievance/signup.php";
        String login_url="http://10.0.2.2/vit_grievance/login.php";
        String post_complaint="http://10.0.2.2/vit_grievance/complaint.php";
        String method=params[0];
        if(method.equals("signup")){
            String fname=params[1];
            String lname=params[2];
            uname=params[3];
            String email=params[4];
            String pwd=params[5];
            String phone1=params[6];
            String phone2=params[7];
            String hostel=params[8];
            String room=params[9];
            Log.d("Data recieved",fname + "," + lname + "," + uname + "," + email + "," + pwd + ","+ phone1 + "," + phone2 + "," + hostel + "," + room);
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                Log.d("In background task","Setting url connection");

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("fname", "UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"+
                        URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"+
                        URLEncoder.encode("uname","UTF-8")+"="+URLEncoder.encode(uname,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8")+"&"+
                        URLEncoder.encode("phone1","UTF-8")+"="+URLEncoder.encode(phone1,"UTF-8")+"&"+
                        URLEncoder.encode("phone2","UTF-8")+"="+URLEncoder.encode(phone2,"UTF-8")+"&"+
                        URLEncoder.encode("hostel","UTF-8")+"="+URLEncoder.encode(hostel,"UTF-8")+"&"+
                        URLEncoder.encode("room","UTF-8")+"="+URLEncoder.encode(room,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS= httpURLConnection.getInputStream();
                IS.close();
                return "Registeration success...";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (method.equals("login")){
            username=params[1];
            String userpass=params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("userpass","UTF-8")+"="+URLEncoder.encode(userpass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response="",line="";
                while ((line=bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                Log.d("response from script",response);
                return response;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //return null;
        }

        else if(method.equals("postcomplaint")){
            uname = params[1];
            String subject = params[2];
            String description = params[3];
            String department = params[4];

            try {
                URL url = new URL(post_complaint);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("uname", "UTF-8")+"="+URLEncoder.encode(uname,"UTF-8")+"&"+
                        URLEncoder.encode("subject", "UTF-8")+"="+URLEncoder.encode(subject,"UTF-8")+"&"+
                        URLEncoder.encode("description", "UTF-8")+"="+URLEncoder.encode(description,"UTF-8")+"&"+
                        URLEncoder.encode("department", "UTF-8")+"="+URLEncoder.encode(department,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS= httpURLConnection.getInputStream();
                IS.close();
                return "Complaint Send";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return "Registeration success...";
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("post execute", result);
        if(result.equals("Registeration success...")){
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();

            Intent intent = new Intent();
            intent.setClass(activity, Homepage.class);
            intent.putExtra("username", uname);
            activity.startActivity(intent);
            //.startActivity(new Intent(activity,Homepage.class));
            //return null;
        }
        else if(result.equals("Complaint Send")){
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
        else {
            alertDialog.setMessage(result);
            alertDialog.show();
            if(result.equals("login success")){
                //activity.startActivity(new Intent(activity,Homepage.class));
                Intent intent = new Intent();
                intent.setClass(activity,Homepage.class);
                intent.putExtra("username", username);
                activity.startActivity(intent);
            }
        }
        //return null;

    }
}
