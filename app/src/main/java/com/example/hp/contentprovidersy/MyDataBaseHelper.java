package com.example.hp.contentprovidersy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by deepice on 2018/6/3.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CONTACTS = "create table Contacts(" +
            "id integer primary key autoincrement,"+
            "name text," +
            "number integer," +
            "gender text)";

    private Context mContext;

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Contacts");
        onCreate(db);
    }
}
