package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFirst extends Fragment {


    ArrayList<String> lstNames;
    Context context;

    public FragmentFirst(ArrayList<String> lstNames, Context context) {
        this.lstNames = lstNames;
        this.context = context;
    }

    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_fragment_first, container, false);

        listView=v.findViewById(R.id.listview);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,lstNames);
        listView.setAdapter(adapter);


        return v;



    }

}
