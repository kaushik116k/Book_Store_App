package com.example.sql_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList id, title, author, description, pages;
    Activity activity;

    public MyAdapter(Context context, ArrayList id, ArrayList title, ArrayList author, ArrayList description, ArrayList pages, Activity activity) {
        this.context = context;
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.pages = pages;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.book_title.setText(String.valueOf(title.get(position)));
        holder.book_author.setText(String.valueOf(author.get(position)));
        holder.book_description.setText(String.valueOf(description.get(position)));
        holder.book_pages.setText(String.valueOf(pages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, UpdateActivity.class);
                i.putExtra("id", String.valueOf(id.get(position)));
                i.putExtra("title",String.valueOf(title.get(position)));
                i.putExtra("author",String.valueOf(author.get(position)));
                i.putExtra("description",String.valueOf(description.get(position)));
                i.putExtra("pages",String.valueOf(pages.get(position)));
                activity.startActivityForResult(i, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView book_title, book_author, book_description, book_pages;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            book_title = itemView.findViewById(R.id.book_title);
            book_author = itemView.findViewById(R.id.book_author);
            book_description = itemView.findViewById(R.id.book_description);
            book_pages = itemView.findViewById(R.id.book_pages);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
