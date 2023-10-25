package com.example.project.database;

import android.content.ContentValues;
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

    public boolean insertToCart(String name, double price, int quantity, int image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("price", price);
        values.put("quantity", quantity);
        values.put("image", image);

        long result = db.insert("Cart", null, values);
        db.close();

        return result != -1; // Check if the insert was successful
    }

    public boolean checkProductExist(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Cart WHERE Name = ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


    public boolean updateCartItemQuantity(String name, int newQuantity, double newPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("quantity", newQuantity);
        values.put("price", newPrice);

        String whereClause = "name = ?";
        String[] whereArgs = {name};

        int rowsUpdated = db.update("Cart", values, whereClause, whereArgs);
        db.close();

        return rowsUpdated > 0;
    }




}
