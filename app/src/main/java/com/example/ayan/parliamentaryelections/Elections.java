package com.example.ayan.parliamentaryelections;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Elections extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ListView listView;
    ArrayList<Election> list;
    ElectionAdapter electionAdapter;
    Election election;


    public Elections() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_election, container, false);
        final Voter_Info voter_info = new Voter_Info();

        TextView Voters = rootview.findViewById(R.id.Voters);

        int color = ContextCompat.getColor(getActivity(), R.color.text);
        Voters.setTextColor(color);

        Voters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, voter_info);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Elections");

        TextView add_election = rootview.findViewById(R.id.add_election);
        add_election.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateElection(reference);
            }
        });

        election = new Election();
        list = new ArrayList<>();
        listView = rootview.findViewById(R.id.election_view);


        electionAdapter = new ElectionAdapter(getActivity(), list);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Elections");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    election = ds.getValue(Election.class);
                    list.add(election);
                }

                listView.setAdapter(electionAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Election election = list.get(position);
                UpdateVote(reference, election);
            }
        });

        return rootview;
    }

    private void UpdateElection(final DatabaseReference reference) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogueView = inflater.inflate(R.layout.update_election, null);
        builder.setView(dialogueView);
        builder.setTitle("Add Election");

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final TextView place = dialogueView.findViewById(R.id.Place);
        final TextView date = dialogueView.findViewById(R.id.datetime);
        final TextView party1 = dialogueView.findViewById(R.id.party_1);
        final TextView party2 = dialogueView.findViewById(R.id.party_2);
        final TextView party3 = dialogueView.findViewById(R.id.party_3);
        final TextView votes1 = dialogueView.findViewById(R.id.vote1);
        final TextView votes2 = dialogueView.findViewById(R.id.vote2);
        final TextView votes3 = dialogueView.findViewById(R.id.vote3);
        TextView update = dialogueView.findViewById(R.id.update);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        final Election election = new Election(place.getText().toString(), date.getText().toString(), party1.getText().toString(),
                                party2.getText().toString(), party3.getText().toString(), votes1.getText().toString(), votes2.getText().toString(), votes3.getText().toString());
                        reference.child(election.getPlace()).setValue(election);
                        alertDialog.dismiss();

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new Elections());
                        transaction.addToBackStack(null);

                        transaction.commit();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //
                    }
                });
            }
        });


    }

    private void UpdateVote(final DatabaseReference reference, final Election election) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogueView = inflater.inflate(R.layout.vote_update, null);
        builder.setView(dialogueView);
        builder.setTitle("Update Vote");

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText votes1 = dialogueView.findViewById(R.id.vote1);
        final EditText votes2 = dialogueView.findViewById(R.id.vote2);
        final EditText votes3 = dialogueView.findViewById(R.id.vote3);
        TextView update = dialogueView.findViewById(R.id.update);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        database.getReference("Elections").child(election.getPlace()).child("vote1").setValue(votes1.getText().toString());
                        database.getReference("Elections").child(election.getPlace()).child("vote2").setValue(votes2.getText().toString());
                        database.getReference("Elections").child(election.getPlace()).child("vote3").setValue(votes3.getText().toString());
                        databaseReference.keepSynced(true);
                        alertDialog.dismiss();

                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, new Elections());
                        transaction.addToBackStack(null);

                        transaction.commit();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //
                    }
                });
            }
        });

    }
}
