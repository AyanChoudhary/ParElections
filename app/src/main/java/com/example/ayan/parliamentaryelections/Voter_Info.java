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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Voter_Info extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;
    ListView listView;
    ArrayList<User> list ;
    UserAdapter userAdapter;
    User user;


    public Voter_Info() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_voter_info, container, false);

        user = new User();
        list = new ArrayList<>();
        listView = rootview.findViewById(R.id.list_view);


        userAdapter = new UserAdapter(getActivity(), list);

        final Elections elections = new Elections();
        TextView Elections = rootview.findViewById(R.id.Elections);

        int color = ContextCompat.getColor(getActivity(), R.color.text);
        Elections.setTextColor(color);

        Elections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, elections);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Voters");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    user = ds.getValue(User.class);
                    list.add(user);
                }

                listView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final User user = list.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View dialogueView = inflater.inflate(R.layout.delete_user, null);
                builder.setView(dialogueView);
                builder.setTitle("Delete User");

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                TextView name = dialogueView.findViewById(R.id.Name);
                TextView username = dialogueView.findViewById(R.id.Username);
                TextView uid = dialogueView.findViewById(R.id.Uid);
                TextView mob = dialogueView.findViewById(R.id.Mob);
                TextView address = dialogueView.findViewById(R.id.Password);

                name.setText(user.getName());
                username.setText(user.getUsername());
                uid.setText(user.getUid());
                mob.setText(user.getMob());
                address.setText(user.getPassword());

                TextView delete = dialogueView.findViewById(R.id.delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                database.getReference("Voters").child(user.getUsername()).removeValue();
                                alertDialog.dismiss();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.fragment_container, new Voter_Info());
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
        });
        return rootview;
    }
}
