package com.itesm.ronald.mobileappsjsonhw;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ronald on 2/16/2015.
 */
public class FriendAdapter extends BaseAdapter implements ListAdapter{
    private int layoutType;
    private ArrayList<Friend> friends;
    private Activity activity;

    public FriendAdapter(Activity activity, ArrayList<Friend> friends, int layoutType){
        this.layoutType = layoutType;
        this.activity = activity;
        this.friends = friends;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.layoutType == 0) {
            if (convertView == null) {
                convertView = activity.getLayoutInflater().inflate(R.layout.row, null);
            }

            TextView name = (TextView) convertView.findViewById(R.id.textName);
            TextView hobby = (TextView) convertView.findViewById(R.id.textHobby);

            Friend f = friends.get(position);
            name.setText(f.getName());
            hobby.setText(f.getHobby());
        }
        else {
            if (convertView == null) {
                convertView = activity.getLayoutInflater().inflate(R.layout.row_extended, null);
            }

            TextView name = (TextView) convertView.findViewById(R.id.textNameE);
            TextView hobby = (TextView) convertView.findViewById(R.id.textHobbyE);
            TextView age = (TextView) convertView.findViewById(R.id.textAge);
            TextView phone = (TextView) convertView.findViewById(R.id.textPhone);
            TextView address = (TextView) convertView.findViewById(R.id.textAddress);

            Friend f = friends.get(position);
            name.setText(f.getName());
            hobby.setText(f.getHobby());
            age.setText(f.getAge());
            phone.setText(f.getPhone());
            address.setText(f.getAddress());
        }
        return convertView;
    }
}
