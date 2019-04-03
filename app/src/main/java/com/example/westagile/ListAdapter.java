package com.example.westagile;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    private final Activity context;
    ArrayList<StudentDetails> u;

    public ListAdapter(Activity context, ArrayList<StudentDetails> u) {
        this.context = context;
        this.u = u;
    }

    @Override
    public int getCount() {
        return u.size();
    }

    @Override
    public Object getItem(int position) {
        return u.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null,true);

        TextView name = rowView.findViewById(R.id.name);
        TextView gender = rowView.findViewById(R.id.gender);
        TextView email = rowView.findViewById(R.id.email);
        TextView dob = rowView.findViewById(R.id.dob1);
        TextView mobile = rowView.findViewById(R.id.mobile);
        Log.i("yo1",u.get(position).get_name());

        name.setText(u.get(position).get_name());
        email.append(u.get(position).get_email_id());
        gender.append(u.get(position).get_gender());
        dob.append(u.get(position).get_dob());
        mobile.append(u.get(position).get_mobile_no());


        return rowView;
    }
}
