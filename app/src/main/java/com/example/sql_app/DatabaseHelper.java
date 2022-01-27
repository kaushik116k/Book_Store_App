package com.example.sql_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "Book_Library.db";
    static final int VERSION = 1;
    private Context context;

    static final String TABLE_NAME = "my_library";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_BOOK = "book_title";
    static final String COLUMN_AUTHOR = "book_author";
    static final String COLUMN_PAGES = "book_pages";
    static final String COLUMN_DESCRIPTION = "book_description";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOK + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // create
    public void create(String title, String author, String description, int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PAGES, pages);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
    }

    // read
    public Cursor read(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void update(String row_id, String title, String author, String description, String pages){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_BOOK, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_PAGES, pages);

        long result = database.update(TABLE_NAME, cv, "_id=?", new String[] {row_id});
        if (result == -1)
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,  title + " updated Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void delete(String row_id, String title){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[] {row_id});

        if (result == -1)
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,  title + " deleted successfully!", Toast.LENGTH_SHORT).show();
    }
}
