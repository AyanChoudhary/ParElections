package com.example.ayan.parliamentaryelections;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        if (findViewById(R.id.fragment_container) != null){

            if (savedInstanceState != null){
                return;
            }
        }
        Login login = new Login();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, login).commit();
    }
}
