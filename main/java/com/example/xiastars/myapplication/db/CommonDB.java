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
     * �������ݣ���������
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
     * �������ݣ�����Type����ڶ�������
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
     * �������ݣ���������,��������������
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
     * �������ݣ�����Type����ڶ�������,��������������
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
     * �������ݣ�����Type����ڶ�������,������������������һҳ��ʶ
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
     * ����Type�޸�����
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
     * ����Type ��ID �޸����ݣ���������һҳ��
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
     * ��ȡ����
     * @param type
     * @return
     */
    public synchronized Cursor commonGetData(int type){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("commonDatabase",null,"type="+type,null,null,null,"createTime desc");
        return cursor;
    }

    /**
     * ��ȡ���������е�ĳһ����
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
     * ��������ɾ������
     * @param type
     * @return
     */
    public synchronized int commonDeleteData(int type ){
        SQLiteDatabase db = getWritableDatabase();
        String where = "type="+type;
        return db.delete("commonDatabase",where,null);
    }

    /**
     * ����ID������ɾ������
     */
    public synchronized int commonDeleteData(int type,String key ){
        SQLiteDatabase db = getWritableDatabase();
        String where = "type=? and key=?";
        String[] whereValue = { String.valueOf(type), key };
        return db.delete("commonDatabase",where,whereValue);
    }

    /**
     * ɾ���������ݣ�������
     * @return
     */
    public synchronized int commonDeleteData(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("commonDatabase",null,null);
    }

    /**
     * ����type��ȡnext
     * @param type
     * @return
     */
    public synchronized Cursor getNext(int type){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("commonDatabase",null,"type="+type,null,null,null,null);
        return cursor;
    }

    /**
     * �����õ�һ�����������ж�ʱ����Next��Ϊ����
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
     * �����Զ���ı�����
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
     * �޸��Զ���ı�����
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
     * ɾ��һ���Զ���ı�����
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
     * ��������ɾ������
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
     * ��������ظ����жϸö����Ƿ����
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
