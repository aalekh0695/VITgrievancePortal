package com.example.aalekh.vitgrievanceportal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.acl.LastOwnerException;

public class Fragment1 extends Fragment{
    View rootView;
    String username,JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_fragment1,container,false);

        Intent intent = getActivity().getIntent();
        if(intent!=null){
            username = intent.getExtras().getString("username");
            Log.d("username", username);
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences(getString(R.string.PREF_FILE), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.USERNAME), username);
            editor.commit();
        }
        new FetchJSONdata().execute();
        return rootView;
    }
    class FetchJSONdata extends AsyncTask<Void, Void, String> {
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url = "http://10.0.2.2/vit_grievance/getjsondata.php";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine())!=null){

                    stringBuilder.append(JSON_STRING+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("result", result);
            int i=0;

            try {
                //jsonObject = new JSONObject(result);
                jsonArray = new JSONArray(result);
                //jsonArray = jsonObject.getJSONArray("server_response");
                //jsonObject = jsonArray.getJSONObject(i++);
                int count=0;
                int length = jsonArray.length();
                String[] uname=new String[length];
                String[] desc=new String[length];
                String[] subject=new String[length];
                String[] dept=new String[length];

                while (count<jsonArray.length()){
                    JSONObject JO = jsonArray.getJSONObject(count);
                    uname[count] = "Posted by~ \n"+JO.getString("uname");
                    desc[count] = "Description: "+JO.getString("desc");
                    subject[count] = "Subject: "+JO.getString("subject");
                    dept[count] = "Concerned department: "+JO.getString("dept");
                    Log.d("uname",uname[count]);
                    count++;
                }

                CustomHome obj = new CustomHome(getActivity(),uname,desc,subject,dept);
                ListView listviewhome = (ListView)rootView.findViewById(R.id.listviewhome);
                listviewhome.setAdapter(obj);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}