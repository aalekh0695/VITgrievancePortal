package com.example.aalekh.vitgrievanceportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class Fragment2 extends Fragment {
    ListView list;
    String[] web={"Hostel Discipline","Hostel Food","Finance","Electricity Complaints","Water Complaints","SCOPE","SELECT",
                    "SMBS","SENSE","SBST","Ragging Report","Hostel Sanitaion","SCALE","Security"};

    Integer[] imageId = {R.drawable.displine,R.drawable.food,R.drawable.finance,R.drawable.electrica_,
            R.drawable.water, R.drawable.scope,R.drawable.select,R.drawable.smbs,
            R.drawable.sense,R.drawable.sbst,R.drawable.raggign,R.drawable.sanitation,R.drawable.scale,R.drawable.security};
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_listview,container,false);
        CustomList adapter= new CustomList(getActivity(),web,imageId);
        list = (ListView)rootView.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String department=String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getActivity(),department,Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getActivity(),GetComplaint.class);
                intent.putExtra("data",department);
                getActivity().startActivity(intent);
            }
        });
        return rootView;
    }
}