package com.example.constellation.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.constellation.enbity.Userinfo;

import java.util.ArrayList;
import java.util.List;

public class UserDbHelper extends SQLiteOpenHelper {
    private static UserDbHelper sHelper;
    private static final String DB_NAME = "user.db"; //数据库名
    private static final int VERSION = 1;    //版本号
    // 必须实现其中一个构方法
    public UserDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static UserDbHelper
    getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new UserDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user_table表
        db.execSQL("create table user_table(user_id integer primary key autoincrement," +
                "username text," +       //用户名
                "password text" +      //密码

                ")");
    }
    @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }       //TODO 在这里根据自己的业务需求，编写增删改查的方法，如下所示

    /**
     * 登录
     */
    @SuppressLint("Range")
    public Userinfo login(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        Userinfo userinfo = null;
        String sql ="select user_id,username,password,nickname  from user_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));

            userinfo = new Userinfo(user_id, name, password);
        }
        cursor.close();
        db.close();
        return userinfo;
    }

/**
 * 注册
 */

public int register(String username, String password) {
    //获取SQLiteDatabase实例
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    //填充占位符
    values.put("username", username);
    values.put("password", password);

    String nullColumnHack ="values(null,?,?,?)";
    //执行
    int insert = (int) db.insert("user_table", nullColumnHack, values);
    db.close();
    return insert;
}

/**
 *
 *修改密码  根据用户唯一 _id来修改密码
 */
    public int updatePwd(String username, String password) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("password", password);
        // 执行SQL
        int update = db.update("user_table", values, "username=?", new String[]{username+""});
        // 关闭数据库连接
        db.close();
        return update;
    }


    public boolean isUsernameExists(String username) {

        // 打开可读数据库连接（由SQLiteOpenHelper管理）
        SQLiteDatabase db = getReadableDatabase();

        // 编写SQL查询语句，使用EXISTS来优化性能
        String query = "SELECT EXISTS(SELECT 1 FROM user_table WHERE username = ?)";

        // 执行查询并获取结果（这将返回一个包含单个值的Cursor）
        Cursor cursor = db.rawQuery(query, new String[]{username});

        // 移动到结果集的第一行（如果存在的话），并获取EXISTS查询的结果
        boolean exists = cursor.moveToFirst() && cursor.getInt(0) > 0;

        // 关闭Cursor
        cursor.close();

        // 返回结果
        return exists;
    }
}

