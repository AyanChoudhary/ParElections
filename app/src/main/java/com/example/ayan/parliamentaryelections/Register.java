package com.example.ayan.parliamentaryelections;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {


    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public Register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.activity_main, container, false);

        database = FirebaseDatabase.getInstance();



        Button button = rootview.findViewById(R.id.register);
        final EditText editText = rootview.findViewById(R.id.email);
        final EditText editText1 = rootview.findViewById(R.id.uid);
        final EditText editText2 = rootview.findViewById(R.id.username);
        final RadioGroup radioGroup = rootview.findViewById(R.id.radioGroup2);
        final RadioButton radioButton = rootview.findViewById(R.id.checkBox);
        final RadioButton radioButton1 = rootview.findViewById(R.id.checkBox2);
        final EditText mob = rootview.findViewById(R.id.mob);
        final EditText password = rootview.findViewById(R.id.password);
        final EditText first = rootview.findViewById(R.id.firstname);
        final EditText last = rootview.findViewById(R.id.lastname);
        final String name = first.getText().toString().concat(last.getText().toString());

        radioGroup.check(radioButton.getId());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User(editText.getText().toString(), editText1.getText().toString(), editText2.getText().toString(),
                        first.getText().toString() + " " + last.getText().toString(), password.getText().toString(), mob.getText().toString(), radioGroup);

                final Login login = new Login();

                if (radioButton.isChecked()) {
                    databaseReference = database.getReference().child("Voters");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(user.getUsername()).exists())
                                Toast.makeText(getActivity(), "Username already taken!!", Toast.LENGTH_LONG).show();

                            else if (dataSnapshot.child(user.getUid()).exists())
                                Toast.makeText(getActivity(), "Pre-Registered UID", Toast.LENGTH_LONG).show();

                            else {

                                if (editText.getText().toString().length() != 0) {
                                    if (editText1.getText().toString().length() != 0) {
                                        if (editText2.getText().toString().length() != 0) {
                                            if (editText1.getText().toString().length() == 10) {

                                                databaseReference.child(user.getUsername()).setValue(user);
                                                user.setRadioButton(radioButton);
                                                user.setRadioButton1(radioButton1);

                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            transaction.replace(R.id.fragment_container, login);
                                            transaction.addToBackStack(null);

                                            transaction.commit();

                                            } else
                                                Toast.makeText(getActivity(), "Enter 10-digit UID", Toast.LENGTH_LONG).show();
                                        } else
                                            Toast.makeText(getActivity(), "Enter a valid Username", Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(getActivity(), "Enter a valid ID", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(getActivity(), "Enter a valid E-Mail", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //
                        }
                    });

                }

                else if (radioButton.isChecked() == false){
                    databaseReference = database.getReference("Officials");
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(user.getUsername()).exists())
                                Toast.makeText(getActivity(), "Username already taken!!", Toast.LENGTH_LONG).show();

                            else if (dataSnapshot.child(user.getUid()).exists())
                                Toast.makeText(getActivity(), "Pre-Registered UID", Toast.LENGTH_LONG).show();

                            else {

                                if (editText.getText().toString().length() != 0) {
                                    if (editText1.getText().toString().length() != 0) {
                                        if (editText2.getText().toString().length() != 0) {
                                            if (editText1.getText().toString().length() == 10) {

                                                databaseReference.child(user.getUsername()).setValue(user);
                                                user.setRadioButton(radioButton);
                                                user.setRadioButton1(radioButton1);

                                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                            transaction.replace(R.id.fragment_container, login);
                                            transaction.addToBackStack(null);

                                            transaction.commit();

                                            } else
                                                Toast.makeText(getActivity(), "Enter 10-digit UID", Toast.LENGTH_LONG).show();
                                        } else
                                            Toast.makeText(getActivity(), "Enter a valid Username", Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(getActivity(), "Enter a valid ID", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(getActivity(), "Enter a valid E-Mail", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //
                        }
                    });
                }
            }
        });


        Button button1 = rootview.findViewById(R.id.login);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login login = new Login();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, login);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
        return rootview;
    }

}
