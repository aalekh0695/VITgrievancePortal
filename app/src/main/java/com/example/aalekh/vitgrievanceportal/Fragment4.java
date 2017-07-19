package com.example.aalekh.vitgrievanceportal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class Fragment4 extends Fragment{
    private ListView listView;
    private String[] name={"AALEKH SRIVASTAVA"};
    private String[] email={"aalekh.srivastava2013@vit.ac.in"};
    private String[] phone={"+919585389485"};
    private Integer[] photo={R.drawable.crop1};
    View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_fragment4,container,false);
        CustomContactus obj=new CustomContactus(getActivity(),photo,name,email,phone);
        listView=(ListView)rootView.findViewById(R.id.listview_contactus);
        listView.setAdapter(obj);
        return rootView;
    }
}