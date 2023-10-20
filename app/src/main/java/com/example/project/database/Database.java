package com.example.project.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    //Truy van khong tra ket qua --> Create, Insert, Update,Delete
    public  void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    //truy van co tra ket qua --> select
    public Cursor GetData(String sql){
        SQLiteDatabase database = getWritableDatabase();//neu chon write thi dung ca 2 truong hop read va write
        return  database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
}
