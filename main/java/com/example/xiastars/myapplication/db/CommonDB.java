package com.example.xiastars.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xiastars.myapplication.UserInfo;

/**
 * Created by xiastars on 2016/3/18.
 */
public class CommonDB extends MyEasyDB{

    public CommonDB(Context context){
        super(context);
    }

    /**
     * 插入数据，单组数据
     * @param type
     * @param cacheData
     * @param createTime
     * @return
     */
    public synchronized long commonInsertData(int type,byte[] cacheData,long createTime){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        cv.put("cacheData",cacheData);
        cv.put("createTime", createTime);
        return db.insert("commonDatabase",null,cv);
    }

    /**
     * 插入数据，单个Type里存在多组数据
     * @param type
     * @param cacheData
     * @param key
     * @param createTime
     * @return
     */
    public synchronized long commonInsertData(int type,byte[] cacheData,String key,long createTime){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        cv.put("key",key);
        cv.put("cacheData",cacheData);
        cv.put("createTime", createTime);
        return db.insert("commonDatabase",null,cv);
    }

    /**
     * 插入数据，单组数据,带插入数据总数
     * @param type
     * @param cacheData
     * @param count
     * @param createTime
     * @return
     */
    public synchronized long commonInsertData(int type,byte[] cacheData,int count,long createTime){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        cv.put("cacheData",cacheData);
        cv.put("count",count);
        cv.put("createTime",createTime);
        return db.insert("commonDatabase",null,cv);
    }

    /**
     * 插入数据，单个Type里存在多组数据,带插入数据总数
     * @param type
     * @param cacheData
     * @param count
     * @param key
     * @param createTime
     * @return
     */
    public synchronized long commonInsertData(int type,byte[] cacheData,int count,String key,long createTime){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        cv.put("key",key);
        cv.put("count",count);
        cv.put("cacheData",cacheData);
        cv.put("createTime",createTime);
        return db.insert("commonDatabase",null,cv);
    }

    /**
     * 插入数据，单个Type里存在多组数据,带插入数据总数与下一页标识
     * @param type
     * @param cacheData
     * @param count
     * @param key
     * @param next
     * @param createTime
     * @return
     */
    public synchronized long commonInsertData(int type,byte[] cacheData,int count,String key,int next,long createTime){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        cv.put("key",key);
        cv.put("count",count);
        cv.put("cacheData",cacheData);
        cv.put("next",next);
        cv.put("createTime",createTime);
        return db.insert("commonDatabase", null, cv);
    }

    /**
     * 根据Type修改数据
     * @param type
     * @param cacheData
     * @return
     */
    public synchronized long updateData(int type,byte[] cacheData) {
        SQLiteDatabase db = getWritableDatabase();
        String where = "type="+type;
        String[] whereValue = { String.valueOf(type)};
        ContentValues cv = new ContentValues();
        cv.put("cacheData", cacheData);
        return db.update("commonDatabase", cv, where, whereValue);
    }

    /**
     * 根据Type 和ID 修改数据，用于有下一页的
     * @param type
     * @param cacheData
     * @param count
     * @param key
     * @param createTime
     * @return
     */
    public synchronized long updateData(int type,byte[] cacheData,int count,String key,long createTime) {
        SQLiteDatabase db = getWritableDatabase();
        String where = "type=? and key=?";
        String[] whereValue = { String.valueOf(key), key};
        ContentValues cv = new ContentValues();
        cv.put("cacheData", cacheData);
        return db.update("commonDatabase", cv, where, whereValue);
    }

    /**
     * 获取数据
     * @param type
     * @return
     */
    public synchronized Cursor commonGetData(int type){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("commonDatabase",null,"type="+type,null,null,null,"createTime desc");
        return cursor;
    }

    /**
     * 获取多组数据中的某一数据
     * @param type
     * @param key
     * @return
     */
    public synchronized Cursor commonGetData(int type, String key){
        SQLiteDatabase db = getWritableDatabase();
        String where = "type=? and key=?";
        String[] whereValue = { String.valueOf(type), key};
        Cursor cursor = db.query("commonDatabase",null, where,whereValue,null,null,null);
        return cursor;
    }

    /**
     * 根据类型删除数据
     * @param type
     * @return
     */
    public synchronized int commonDeleteData(int type ){
        SQLiteDatabase db = getWritableDatabase();
        String where = "type="+type;
        return db.delete("commonDatabase",where,null);
    }

    /**
     * 根据ID与类型删除数据
     */
    public synchronized int commonDeleteData(int type,String key ){
        SQLiteDatabase db = getWritableDatabase();
        String where = "type=? and key=?";
        String[] whereValue = { String.valueOf(type), key };
        return db.delete("commonDatabase",where,whereValue);
    }

    /**
     * 删除所有数据，请慎用
     * @return
     */
    public synchronized int commonDeleteData(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("commonDatabase",null,null);
    }

    /**
     * 根据type获取next
     * @param type
     * @return
     */
    public synchronized Cursor getNext(int type){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("commonDatabase",null,"type="+type,null,null,null,null);
        return cursor;
    }

    /**
     * 当运用到一个参数进行判断时，就Next设为参数
     * @param type
     * @param next
     * @return
     */
    public int updateNext(int type, int next) {
        SQLiteDatabase db = getWritableDatabase();
        String where = "type=?";
        String[] whereValue = { String.valueOf(type) };
        ContentValues cv = new ContentValues();
        cv.put("next", next);
        return db.update("commonDatabase", cv, where, whereValue);
    }

    ////////////////////////////////////////////////////////////////////////

    /**
     * 插入自定义的表数据
     * @param type
     * @param info
     * @return
     */
    public synchronized long insertNewMyObject(int type,UserInfo info){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type",type);
        cv.put(MyCustomTable.INT_FIRST,info.getUserAge());
        cv.put(MyCustomTable.LONG_FIRST,info.getCreateTime());
        cv.put(MyCustomTable.STRING_FISRT,info.getUserName());
        return db.insert(MyCustomTable.MY_CUSTOM_TABLE, null, cv);
    }

    /**
     * 修改自定义的表数据
     * @param info
     * @param type
     * @return
     */
    public synchronized long updateMyObject(UserInfo info,int type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(MyCustomTable.INT_FIRST,info.getUserAge());
        cv.put(MyCustomTable.LONG_FIRST,info.getCreateTime());
        cv.put(MyCustomTable.STRING_FISRT,info.getUserAge());
        String where = MyCustomTable.STRING_FISRT+"=?"+" and type = ?";
        String[] whereValue = {MyCustomTable.STRING_FISRT, String.valueOf(type)};
        return db.update(MyCustomTable.MY_CUSTOM_TABLE, cv, where, whereValue);
    }

    /**
     * 删除一条自定义的表数据
     * @param firstString
     * @param type
     * @return
     */
    public synchronized long deleteMyObject(String firstString,int type) {
        SQLiteDatabase db = getWritableDatabase();
        String where = MyCustomTable.STRING_FISRT+"=?"+" and type =?";
        String[] whereValue = {firstString,String.valueOf(type)};
        return  db.delete(MyCustomTable.MY_CUSTOM_TABLE, where, whereValue);
    }

    /**
     * 根据类型删除数据
     * @param type
     * @return
     */
    public synchronized int deleteMyObjectByType(int type ){
        SQLiteDatabase db = getWritableDatabase();
        String where = "type="+type;
        return db.delete(MyCustomTable.MY_CUSTOM_TABLE, where, null);
    }

    public synchronized Cursor getMyObject(int type){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(MyCustomTable.MY_CUSTOM_TABLE,null,"type="+type + " order by createTime desc",null,null,null,null);
        return cursor;
    }

    /**
     * 避免插入重复，判断该对象是否存在
     * @param firstString
     * @param type
     * @return
     */
    public synchronized Cursor checkDataExist(String firstString,int type){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query(MyCustomTable.MY_CUSTOM_TABLE,null,MyCustomTable.STRING_FISRT+"='"+firstString+"'"+" and type ="+type,null,null,null,null);
        return cursor;
    }

}
