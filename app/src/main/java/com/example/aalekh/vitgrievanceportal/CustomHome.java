package com.example.aalekh.vitgrievanceportal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ashtrayaalekh on 4/20/2016.
 */
public class CustomHome extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] uname;
    private final String[] desc;
    private final String[] subject;
    private final String[] dept;
    public CustomHome(Activity context,String[] uname,String[] desc,String[] subject,String[] dept) {
        super(context,R.layout.singlelist_home,subject);
        this.context = context;
        this.uname=uname;
        this.desc=desc;
        this.subject = subject;
        this.dept=dept;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.singlelist_home, null, true);
        TextView tvUname = (TextView)rowView.findViewById(R.id.tvUname);
        TextView tvDesc = (TextView)rowView.findViewById(R.id.tvDescription);
        TextView tvSubject = (TextView)rowView.findViewById(R.id.tvSubject);
        TextView tvDept = (TextView)rowView.findViewById(R.id.tvDepartment);

        tvSubject.setText(subject[position]);
        tvDesc.setText(desc[position]);
        tvDept.setText(dept[position]);
        tvUname.setText(uname[position]);


        return rowView;
    }
}
