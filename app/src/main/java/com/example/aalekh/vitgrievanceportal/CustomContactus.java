package com.example.aalekh.vitgrievanceportal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by ashtrayaalekh on 4/5/2016.
 */
public class CustomContactus extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] name;
    private final String[] email;
    private final String[] phone;
    private final Integer[] photo;

    public CustomContactus(Activity context,Integer[] photo,String[] name,String[] email,String[] phone){
        super(context,R.layout.singlelist_contactus,name);
        this.context = context;
        this.photo=photo;
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.singlelist_contactus, null, true);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.img_contactus);
        TextView textname=(TextView)rowView.findViewById(R.id.text_name);
        TextView textemail=(TextView)rowView.findViewById(R.id.text_email);
        TextView textphone=(TextView)rowView.findViewById(R.id.text_phone);

        imageView.setImageResource(photo[0]);
        textname.setText(name[0]);
        textemail.setText(email[0]);
        textphone.setText(phone[0]);
        return rowView;
    }
}
