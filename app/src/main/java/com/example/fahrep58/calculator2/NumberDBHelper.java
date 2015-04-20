package com.example.fahrep58.calculator2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Erik on 3/14/2015.
 */
public class NumberDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NumberReader.db";

    public NumberDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //All you need to do is call getWritableDatabase() or getReadableDatabase().
    //Note: Because they can be long-running, be sure that you call getWritableDatabase() or getReadableDatabase() in a background thread, such as with AsyncTask or IntentService.
    //To use SQLiteOpenHelper, create a subclass that overrides the onCreate(), onUpgrade() and onOpen() callback methods. You may also want to implement onDowngrade(), but it's not required.
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + NumberContract.NumberEntry.TABLE_NAME + " (" +
                NumberContract.NumberEntry._ID + " INTEGER PRIMARY KEY," +
          //      NumberContract.NumberEntry.COLUMN_NAME_USER + " TEXT," +
          //      NumberContract.NumberEntry.COLUMN_NAME_TYPE + " TEXT," +
                NumberContract.NumberEntry.COLUMN_NAME_ID + " TEXT," +
                NumberContract.NumberEntry.COLUMN_NAME_VALUE + " TEXT" + " )");

        db.execSQL("CREATE TABLE " + NumberContract.NumberEntry.TABLE_NAME2 + " (" +
                NumberContract.NumberEntry._ID + " INTEGER PRIMARY KEY," +
                NumberContract.NumberEntry.COLUMN_NAME_ID2 + " TEXT," +
                NumberContract.NumberEntry.COLUMN_NAME_VALUE2 + " TEXT" + " )");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + NumberContract.NumberEntry.TABLE_NAME);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
