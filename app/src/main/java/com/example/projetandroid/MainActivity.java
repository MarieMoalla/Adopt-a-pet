package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    //inputs
    BottomNavigationView mBottomnavigationview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mBottomnavigationview = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //mBottomnavigationview.setOnNavigationItemSelectedListener(navListener);

    }

}