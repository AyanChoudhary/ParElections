package com.example.ayan.parliamentaryelections;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.MyViewHolder> {

    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, username, uid;

        public MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            username = view.findViewById(R.id.username);
            uid = view.findViewById(R.id.uid);
        }
    }

    public UserRecyclerViewAdapter(List<User> users){
        this.userList = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item2, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        User user = userList.get(i);
        myViewHolder.name.setText(user.getName());
        myViewHolder.username.setText(user.getUsername());
        myViewHolder.uid.setText(user.getUid());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}


