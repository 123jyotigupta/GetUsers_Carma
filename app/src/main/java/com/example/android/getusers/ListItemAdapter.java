package com.example.android.getusers;
import com.example.android.getusers.R;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jyoti on 8/27/15.
 */
public class ListItemAdapter extends ArrayAdapter<User> {

    private Context mContext;
    private ArrayList<User> users;

    public ListItemAdapter(Context context, ArrayList<User> objects) {
        super(context, com.example.android.getusers.R.layout.item_layout, objects);
        mContext = context;
        users = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View v = inflater.inflate(com.example.android.getusers.R.layout.item_layout, null);

        ImageView image = (ImageView)v.findViewById(R.id.imageUser);
        Picasso.with(mContext).load(users.get(position).getPhoto()).into(image);

        TextView name = (TextView)v.findViewById(R.id.textName);
        name.setText(users.get(position).getName());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date lastSeen = new Date(users.get(position).getLastTimeStamp());

        TextView time = (TextView)v.findViewById(R.id.textTimeStamp);
        time.setText(format.format(lastSeen));

        return v;
    }
}
