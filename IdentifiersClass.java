package com.example.codelist;

import android.provider.BaseColumns;

public class IdentifiersClass {
    private IdentifiersClass () {}

    public static final class Collection implements BaseColumns {
        public static final String TABLE_NAME = "codelist";
        public static final String COLUMN_NAME = "name";
        public static final String TIMESTAMP = "timestamp";
    }
}
