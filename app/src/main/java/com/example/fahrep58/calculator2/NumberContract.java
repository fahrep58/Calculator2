package com.example.fahrep58.calculator2;

import android.provider.BaseColumns;

/**
 * Created by Erik on 3/14/2015.
 * Explicitly specifies the layout of the schema.
 * Contains constants for URIs, tables and columns.
 *
 */
public final class NumberContract {
    //prevents someone from instantiating this class
    public NumberContract(){}

    /* By implementing the BaseColumns interface, your inner class can inherit a primary key field
    called _ID that some Android classes such as cursor adaptors will expect it to have. It's not
    required, but this can help your database work harmoniously with the Android framework.
     This defines contents of 1 table. Makes it easy to change tables/columns
     without having to find where it is everywhere in your code.
     */
    public static abstract class NumberEntry implements BaseColumns{
        public static final String TABLE_NAME = "numbers";
        public static final String COLUMN_NAME_ID = "var";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String TABLE_NAME2 = "login";
        public static final String COLUMN_NAME_ID2 = "id";
        public static final String COLUMN_NAME_VALUE2 = "pass";
    }
}
