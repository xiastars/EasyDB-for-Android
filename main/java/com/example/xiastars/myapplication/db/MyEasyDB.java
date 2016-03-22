package com.example.xiastars.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xiastars on 2016/3/18.
 */
public class MyEasyDB extends SQLiteOpenHelper{

    private SQLiteDatabase db = null;

    /** ���޸ĳ����Լ������ݿ����� */
    private final static String DB = "myfirstdbname";

    /** �����޸����ݱ�ʱ��������汾�� */
    private final static int VERSIONCODE = 1;

    public MyEasyDB(Context context){
        super(context,DB,null,VERSIONCODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /** ���������ݱ������������ã����������û��ô��ë��ã��;���������� */
        String commonDatabase = "create table commonDatabase(id integer primary key autoincrement,"
                + "cacheData blob,"
                + "type integer,"
                + "count integer,"
                + "key text,"
                + "next integer,"
                + "createTime integer)";
        db.execSQL(commonDatabase);

        /**
         * �Զ��������ݱ������һ���������Ƶ����������ô�Ƽ�ʹ����һ��
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

    /** ������������ */
    public void beginTransaction() {
        if(db == null)
            return;
        db = getWritableDatabase();
        db.beginTransaction();
    }

    /**  ֹͣ������ */
    public void endTransaction() {
        if(db == null)
            return;
        db.setTransactionSuccessful();
        db.endTransaction();
    }
}
