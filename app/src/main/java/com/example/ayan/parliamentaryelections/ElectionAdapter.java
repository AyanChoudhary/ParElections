package com.example.ayan.parliamentaryelections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ElectionAdapter extends ArrayAdapter<Election> {

    public ElectionAdapter(Context context, List<Election> list) {
        super(context, 0, list);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item1, parent, false);
        }

        Election election = getItem(position);

        TextView place = listItemView.findViewById(R.id.place);
        TextView date = listItemView.findViewById(R.id.date_and_time);
        TextView party1 = listItemView.findViewById(R.id.party1);
        TextView party2 = listItemView.findViewById(R.id.party2);
        TextView party3 = listItemView.findViewById(R.id.party3);
        TextView vote1 = listItemView.findViewById(R.id.votes1);
        TextView vote2 = listItemView.findViewById(R.id.votes2);
        TextView vote3 = listItemView.findViewById(R.id.votes3);

        place.setText(election.getPlace());
        date.setText(election.getDate_time());
        party1.setText(election.getParty1());
        party2.setText(election.getParty2());
        party3.setText(election.getParty3());
        vote1.setText(election.getVote1());
        vote2.setText(election.getVote2());
        vote3.setText(election.getVote3());


        return listItemView;
    }
}
