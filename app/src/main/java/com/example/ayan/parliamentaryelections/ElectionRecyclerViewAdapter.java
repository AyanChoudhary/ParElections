package com.example.ayan.parliamentaryelections;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ElectionRecyclerViewAdapter extends RecyclerView.Adapter<ElectionRecyclerViewAdapter.MyViewHolder> {

    private List<Election> electionList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView place, date_time, party1, party2, party3, vote1, vote2, vote3;

        public MyViewHolder(View view){
            super(view);
            place = view.findViewById(R.id.place);
            date_time = view.findViewById(R.id.date_and_time);
            party1 = view.findViewById(R.id.party1);
            party2 = view.findViewById(R.id.party2);
            party3 = view.findViewById(R.id.party3);
            vote1 = view.findViewById(R.id.votes1);
            vote2 = view.findViewById(R.id.votes2);
            vote3 = view.findViewById(R.id.votes3);
        }
    }

    public ElectionRecyclerViewAdapter(List<Election> electionList){
        this.electionList = electionList;
    }

    @NonNull
    @Override
    public ElectionRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item1, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Election election = electionList.get(i);
        myViewHolder.place.setText(election.getPlace());
        myViewHolder.date_time.setText(election.getDate_time());
        myViewHolder.party1.setText(election.getParty1());
        myViewHolder.party2.setText(election.getParty2());
        myViewHolder.party3.setText(election.getParty3());
        myViewHolder.vote1.setText(election.getVote1());
        myViewHolder.vote2.setText(election.getVote2());
        myViewHolder.vote3.setText(election.getVote3());
    }

    @Override
    public int getItemCount() {
        return electionList.size();
    }
}
