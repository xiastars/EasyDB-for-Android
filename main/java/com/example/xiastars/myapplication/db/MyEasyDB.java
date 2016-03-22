package com.example.xiastars.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xiastars on 2016/3/18.
 */
public class MyEasyDB extends SQLiteOpenHelper{

    private SQLiteDatabase db = null;

    /** 请修改成你自己的数据库名称 */
    private final static String DB = "myfirstdbname";

    /** 当你修改数据表时，请递增版本号 */
    private final static int VERSIONCODE = 1;

    public MyEasyDB(Context context){
        super(context,DB,null,VERSIONCODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /** 懒惰型数据表，这个表最简单易用，如果对性能没那么吹毛求疵，就尽情用这个吧 */
        String commonDatabase = "create table commonDatabase(id integer primary key autoincrement,"
                + "cacheData blob,"
                + "type integer,"
                + "count integer,"
                + "key text,"
                + "next integer,"
                + "createTime integer)";
        db.execSQL(commonDatabase);

        /**
         * 自定义型数据表，如果对一个对象进行频繁操作，那么推荐使用这一个
         */
        String shortcutDatabase = "create table "+MyCustomTable.MY_CUSTOM_TABLE+"(id integer primary key autoincrement,"
                + "type integer,"
                + MyCustomTable.STRING_FISRT+" text,"
                + MyCustomTable.INT_FIRST+" integer,"
                + MyCustomTable.LONG_FIRST+" integer)";
        db.execSQL(shortcutDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists commonDatabase";
        db.execSQL(sql);
        sql = "drop table if exists "+MyCustomTable.MY_CUSTOM_TABLE;
        db.execSQL(sql);
        onCreate(db);

    }

    /** 开启读事务处理 */
    public void beginTransaction() {
        if(db == null)
            return;
        db = getWritableDatabase();
        db.beginTransaction();
    }

    /**  停止事务处理 */
    public void endTransaction() {
        if(db == null)
            return;
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
