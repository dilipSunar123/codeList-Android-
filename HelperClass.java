package com.example.codelist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "codelist.db";
    public static final int DATABASE_VERSION = 1;

    public HelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " +
                IdentifiersClass.Collection.TABLE_NAME + " (" +
                IdentifiersClass.Collection._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IdentifiersClass.Collection.COLUMN_NAME + " TEXT NOT NULL, " +
                IdentifiersClass.Collection.TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" + ");";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IdentifiersClass.Collection.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
