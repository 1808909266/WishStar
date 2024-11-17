package com.example.constellation.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.constellation.enbity.Wish;

import java.util.ArrayList;
import java.util.List;

public class WishDbOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "wishSQLite.db";
    private static final String TABLE_NAME_WISH = "wish";
    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_WISH + " (id integer primary key autoincrement, wishcontent text)";
    public WishDbOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertData(Wish wish) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("wishcontent", wish.getWishcontent());

        return db.insert(TABLE_NAME_WISH, null, values);
    }

    public List<Wish> queryAllFromDb() {

        SQLiteDatabase db = getWritableDatabase();
        List<Wish> wishList = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME_WISH, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex("id"));

                String wishcontent = cursor.getString(cursor.getColumnIndex("wishcontent"));


                Wish wish = new Wish();
                wish.setId(id);

                wish.setWishcontent(wishcontent);


                wishList.add(wish);
            }
            cursor.close();
        }

        return wishList;

    }
    public int deleteFromDbById(String id) {
        SQLiteDatabase db = getWritableDatabase();
//        return db.delete(TABLE_NAME_NOTE, "id = ?", new String[]{id});
//        return db.delete(TABLE_NAME_NOTE, "id is ?", new String[]{id});
        return db.delete(TABLE_NAME_WISH, "id like ?", new String[]{id});
    }

    public int updateData(Wish wish) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("wishcontent", wish.getWishcontent());


        return db.update(TABLE_NAME_WISH, values, "id like ?", new String[]{wish.getId()});
    }
}
