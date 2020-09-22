package com.example.android.golocalsellerapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AfterLoginSeller extends AppCompatActivity {
    FloatingActionButton addButton;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_login_seller);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCategories);
        addButton = (FloatingActionButton) findViewById(R.id.fabAdd);

    }
}