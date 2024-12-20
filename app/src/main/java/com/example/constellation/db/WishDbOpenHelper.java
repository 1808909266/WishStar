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
    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_WISH + " (id integer primary key autoincrement, wishcontent text, user_id text)";
    public WishDbOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertData(Wish wish,String userId) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("wishcontent", wish.getWishcontent());
        values.put("user_id", userId);

        return db.insert(TABLE_NAME_WISH, null, values);
    }

    public List<Wish> queryAllFromDb(String userId) {

        SQLiteDatabase db = getWritableDatabase();
        List<Wish> wishList = new ArrayList<>();
        String selection = "user_id = ?";
        String[] selectionArgs = { userId };

        Cursor cursor = db.query(TABLE_NAME_WISH, null, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));

                String wishcontent = cursor.getString(cursor.getColumnIndexOrThrow("wishcontent"));


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
        return db.delete(TABLE_NAME_WISH, "id like ?", new String[]{id});
    }

    public int updateData(Wish wish) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("wishcontent", wish.getWishcontent());


        return db.update(TABLE_NAME_WISH, values, "id like ?", new String[]{wish.getId()});
    }
}
