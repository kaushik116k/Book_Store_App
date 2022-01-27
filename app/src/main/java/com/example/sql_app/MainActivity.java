package com.example.sql_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private DatabaseHelper dbHelper;
    ArrayList<String> id, title, author, description, pages;

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });

        dbHelper = new DatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        title = new ArrayList<>();
        author = new ArrayList<>();
        description = new ArrayList<>();
        pages = new ArrayList<>();

        StoringDataInArrayList();

        adapter = new MyAdapter(this, id, title, author, description, pages, MainActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        recreate();
    }

    void StoringDataInArrayList(){
        Cursor cursor = dbHelper.read();
        if(cursor.getCount() == 0)
            Toast.makeText(this, "No data available", Toast.LENGTH_SHORT).show();
        else{
            while(cursor.moveToNext()) {
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                author.add(cursor.getString(2));
                description.add(cursor.getString(3));
                pages.add(cursor.getString(4));
            }
        }
    }
}