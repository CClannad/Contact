package com.example.zc.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends ArrayAdapter<People> {

    private int resourceId;

    public MyAdapter(Context context, int textViewResourceId, List<People> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        People addressList = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView name = view.findViewById(R.id.item_name);
        name.setText(addressList.getName());
        return view;
    }
}
