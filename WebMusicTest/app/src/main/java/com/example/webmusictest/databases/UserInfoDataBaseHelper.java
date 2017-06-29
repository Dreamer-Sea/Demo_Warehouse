package com.example.webmusictest.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by King on 2017/6/24.
 */

public class UserInfoDataBaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_USER = "create table User(" +
            "id integer primary key autoincrement," +
            "username text," +
            "password text)";

    private Context mContext;

    public UserInfoDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        
    }
}
