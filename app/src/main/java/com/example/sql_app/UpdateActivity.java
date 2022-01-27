package com.example.sql_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText EtTitle, EtAuthor, EtDescription, EtPages;
    Button update, delete;

    String id, title, author, description, pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        
        EtTitle = findViewById(R.id.BookTitle2);
        EtAuthor = findViewById(R.id.BookAuthor2);
        EtDescription = findViewById(R.id.Description2);
        EtPages = findViewById(R.id.Pages2);

        getSetIntents();

        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);

        update = findViewById(R.id.Update_Button);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateActivity.this);
                title = EtTitle.getText().toString().trim();
                author = EtAuthor.getText().toString().trim();
                description = EtDescription.getText().toString().trim();
                pages = EtPages.getText().toString().trim();
                dbHelper.update(id, title, author, description, pages);
            }
        });

        delete = findViewById(R.id.Delete_Button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog();
            }
        });
    }
    public void getSetIntents(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("pages")) {

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            description = getIntent().getStringExtra("description");
            pages = getIntent().getStringExtra("pages");

            EtTitle.setText(title);
            EtAuthor.setText(author);
            EtDescription.setText(description);
            EtPages.setText(pages);
        }
        else
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
    }

    void ConfirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + "?");
        builder.setMessage("Are you sure you want to delete " + title + "?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateActivity.this);
                dbHelper.delete(id, title);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }
}