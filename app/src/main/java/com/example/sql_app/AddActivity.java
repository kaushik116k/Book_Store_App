package com.example.sql_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title, author, description, pages;
    Button add_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = findViewById(R.id.BookTitle);
        author = findViewById(R.id.BookAuthor);
        description = findViewById(R.id.Description);
        pages = findViewById(R.id.Pages);

        add_btn = findViewById(R.id.Add_Button);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(AddActivity.this);
                databaseHelper.create(title.getText().toString().trim(),
                        author.getText().toString().trim(),
                        description.getText().toString().trim(),
                        Integer.valueOf(pages.getText().toString().trim()));
            }
        });
    }
}