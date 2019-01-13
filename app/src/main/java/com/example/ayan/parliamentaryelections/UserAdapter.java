package com.example.ayan.parliamentaryelections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {

    public UserAdapter(Context context, List<User> list) {
        super(context, 0, list);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item2, parent, false);
        }

        User user = getItem(position);

        TextView name = listItemView.findViewById(R.id.name);
        TextView username = listItemView.findViewById(R.id.username);
        TextView uid = listItemView.findViewById(R.id.uid);

        name.setText(user.getName());
        username.setText(user.getUsername());
        uid.setText(user.getUid());

            return listItemView;
    }
}
