package com.example.codelist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SQLiteDatabase database;
    EditText editText;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelperClass helperClass = new HelperClass(this);
        database = helperClass.getWritableDatabase();

        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        Button addbutton = findViewById(R.id.button);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this, getAllItems());
        recyclerView.setAdapter(listAdapter);

        editText.setBackgroundColor(Color.TRANSPARENT);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem( (Long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    public void addItem () {
        if (editText.getText().toString().trim().length() == 0) {
            return;
        }
        String name = editText.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(IdentifiersClass.Collection.COLUMN_NAME, name);
        database.insert(IdentifiersClass.Collection.TABLE_NAME, null, cv);

        listAdapter.swapCursor(getAllItems());

        editText.getText().clear();
    }

    private Cursor getAllItems () {
        return database.query(
                IdentifiersClass.Collection.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                IdentifiersClass.Collection.TIMESTAMP + " DESC"
        );
    }
    private void removeItem (Long id) {
        database.delete(IdentifiersClass.Collection.TABLE_NAME, IdentifiersClass.Collection._ID + "=" + id, null);
        listAdapter.swapCursor(getAllItems());
    }
}