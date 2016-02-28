package com.shyrokovkaya.anastasiia.testapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "zurmansor.db";
    private static final String DATABASE_TABLE = "users";

    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final String PHONE_COLUMN = "phone";
    public static final String EMAIL_COLUMN = "email";
    public static final String WEB_COLUMN = "web";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + FIRST_NAME_COLUMN + " text not null, "
            + LAST_NAME_COLUMN + " text not null, "
            + PHONE_COLUMN + " text not null, "
            + EMAIL_COLUMN + " text not null, "
            + WEB_COLUMN + " text not null);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        no need yet
    }

    /*
    public long getRowsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, DATABASE_TABLE);
    }
    */

    public Cursor fetchAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.query(DATABASE_TABLE, new String[]{_ID, FIRST_NAME_COLUMN, LAST_NAME_COLUMN,
                        PHONE_COLUMN, EMAIL_COLUMN, WEB_COLUMN},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean addUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.FIRST_NAME_COLUMN, user.getFirstName());
        cv.put(DatabaseHelper.LAST_NAME_COLUMN, user.getLastName());
        cv.put(DatabaseHelper.PHONE_COLUMN, user.getPhone());
        cv.put(DatabaseHelper.EMAIL_COLUMN, user.getEmail());
        cv.put(DatabaseHelper.WEB_COLUMN, user.getWeb());

        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert(DATABASE_TABLE, null, cv) != -1;
    }
}