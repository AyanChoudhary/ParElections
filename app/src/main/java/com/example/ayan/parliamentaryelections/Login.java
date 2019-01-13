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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayan.parliamentaryelections.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {

    FirebaseDatabase database;
    DatabaseReference reference;


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.activity_login, container, false);
        Button button = rootview.findViewById(R.id.login);
        RadioGroup radioGroup = rootview.findViewById(R.id.radioGroup);
        final EditText username = rootview.findViewById(R.id.username);
        final EditText password = rootview.findViewById(R.id.password);
        final RadioButton radioButton = rootview.findViewById(R.id.checkBox);
        radioGroup.check(radioButton.getId());

        database = FirebaseDatabase.getInstance();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username.getText().toString(), password.getText().toString(), radioButton.isChecked());
            }
        });

        Button button1 = rootview.findViewById(R.id.register);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register register = new Register();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment_container, register);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        });
       return rootview;
    }

    private void login(final String username, final String password, boolean checked) {
        if (checked){

            final Voter voter = new Voter();
            reference = database.getReference("Voters");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(username).exists()){
                        if (!username.isEmpty()){
                            User login = dataSnapshot.child(username).getValue(User.class);
                            if (login.getPassword().equals(password)){
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                                transaction.replace(R.id.fragment_container, voter);
                                transaction.addToBackStack(null);

                                transaction.commit();
                            }
                            else
                                Toast.makeText(getActivity(),"Wrong username or password", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getActivity(), "Username is Empty", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getActivity(), "User does not Exist", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //
                }
            });

        }

        else {

            final Elections elections = new Elections();
            reference = database.getReference("Officials");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(username).exists()){
                        if (!username.isEmpty()){
                            User login = dataSnapshot.child(username).getValue(User.class);
                            if (login.getPassword().equals(password)){
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                                transaction.replace(R.id.fragment_container, elections);
                                transaction.addToBackStack(null);

                                transaction.commit();
                            }
                            else
                                Toast.makeText(getActivity(),"Wrong username or password", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getActivity(), "Username is Empty", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getActivity(), "User does not Exist", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    //
                }
            });
        }
    }

}
