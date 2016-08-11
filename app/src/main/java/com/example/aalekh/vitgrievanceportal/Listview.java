package com.example.aalekh.vitgrievanceportal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Listview extends Activity {

    ListView list;
    String[] web={"nokia","blackberry","samsung","apple","motorola","microsoft","google","flipkart"};
    Integer[] imageId = {R.drawable.sample_0,R.drawable.sample_1,R.drawable.sample_2,R.drawable.sample_3,R.drawable.sample_4,
            R.drawable.sample_5,R.drawable.sample_6,R.drawable.sample_7};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        CustomList adapter = new CustomList(Listview.this,web,imageId);
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listview, menu);
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
