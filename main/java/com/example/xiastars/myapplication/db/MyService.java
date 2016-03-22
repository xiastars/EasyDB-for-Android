package com.example.xiastars.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;

import com.example.xiastars.myapplication.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiastars on 2016/3/22.
 */
public class MyService {

    private Cursor mCursor ;
    private CommonDB commonDB ;

    public MyService(Context context) {
        super();
        commonDB = new CommonDB(context);
    }

    /**
     * �������
     * @param type
     * @param cacheData
     */
    public synchronized void insert(final int type,final Object cacheData){
        try {
            commonDB.commonDeleteData(type);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeDB();
            commonDB.commonInsertData(type, SerializeUtil.serializeObject(cacheData), System.currentTimeMillis());
        }
    }

    /**
     * �����Զ������
     * @param info
     */
    private synchronized void insertMyObject(UserInfo info,int type){
        if(info == null)return;
        try {
            commonDB.insertNewMyObject(type, info);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeDB();
        }
    }

    /**
     * �����Զ������
     * @param infos
     * @param type
     */
    public synchronized void insertMyObjects(List<UserInfo> infos,int type){
        for(UserInfo info : infos){
            insertOrUpdateMyObject(info, type);
        }
    }

    /**
     * ����IDɾ������
     */
    public synchronized void deleteMyObjectByType(final int type){
        try {
            commonDB.deleteMyObjectByType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeDB();
        }
    }

    /**
     * ��ȡ��������ļ�������
     * @param count ��ѯ����
     * @param type
     * @return
     */
    public List<UserInfo> getRecentMyObject(int count,int type){
        List<UserInfo> activitys = new ArrayList<UserInfo>();
        try {
            mCursor = commonDB.getMyObject(type);
            int index = 0;
            while(mCursor.moveToNext()){
                index ++;
                UserInfo info = new UserInfo();
                String username = mCursor.getString(mCursor.getColumnIndex(MyCustomTable.STRING_FISRT));
                long createTime = mCursor.getLong(mCursor.getColumnIndex(MyCustomTable.LONG_FIRST));
                int userage = mCursor.getInt(mCursor.getColumnIndex(MyCustomTable.INT_FIRST));
                info.setCreateTime(createTime);
                info.setUserAge(userage);
                info.setUserName(username);

                activitys.add(info);
                if(index  == count){
                    break;
                }
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }catch(OutOfMemoryError e){

        }finally{
            closeDB();
        }
        return activitys != null ? activitys : null;
    }

    /**
     * ������޸Ķ���
     * @return
     */
    public boolean insertOrUpdateMyObject(UserInfo info,int type){
        try {
            mCursor = commonDB.checkDataExist(info.getUserName(), type);
            if(mCursor.getCount() == 0){
                insertMyObject(info, type);
            }else{
                updateMyObject(info, type);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
        return false;
    }

    /**
     * �����ҵĶ���
     * @param info
     */
    public void updateMyObject(UserInfo info,int type) {
        try {
            commonDB.updateMyObject(info, type);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    /**
     * ɾ����������
     * @param info
     */
    public void deleteSingleMyObject(UserInfo info,int type) {
        try {
            if(info == null || TextUtils.isEmpty(info.getUserName())){
                return;
            }
            commonDB.deleteMyObject(info.getUserName(), type);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    /**
     * ����ID��������
     * @param type
     * @param cacheData
     * @param createTime
     */
    private synchronized void commonInsertData(final int type,Object cacheData,int count ,int next,final String id ,final long createTime){
        try {
            commonDB.commonInsertData(type, SerializeUtil.serializeObject(cacheData), count, id, next, createTime);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
    }

    /**
     * ����ID��������,����������ɾ��֮ǰ������
     */
    public synchronized void insert(final int type,final Object cacheData,final int count){
        try {
            commonDB.commonDeleteData(type);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeDB();
            commonInsertData(type, cacheData, count, 0, "", System.currentTimeMillis());
        }
    }

    /**
     * ����ID��������,����������ɾ��֮ǰ������
     */
    public synchronized void insert(final int type,final Object cacheData,final String id) {
        try {
            commonDB.commonDeleteData(type, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDB();
            commonInsertData(type, cacheData, 0, 0, id, System.currentTimeMillis());
        }
    }

    /**
     * ��ȡ��������
     * @param
     * @return
     */
    public Object getData(int type) {
        Object activitys = null;
        try {
            mCursor = commonDB.commonGetData(type);
            if (mCursor != null && mCursor.moveToNext()) {
                activitys = SerializeUtil.deserializeObject(mCursor.getBlob(
                        mCursor.getColumnIndex("cacheData")));
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return activitys != null ? activitys : null;
    }

    /**
     * ����ID��ȡ�϶���Ļ���
     * @param
     * @return
     */
    public Object getData(int type, String id){
        Object activitys = null;
        try {
            mCursor = commonDB.commonGetData(type,id);
            if(mCursor.moveToNext()){
                activitys =  SerializeUtil.deserializeObject(mCursor.getBlob(
                        mCursor.getColumnIndex("cacheData")));
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
        return activitys != null ? activitys : null;
    }

    /**
     * ��������ɾ������
     */
    public synchronized void commonDeleteData(final int type){
        try {
            commonDB.commonDeleteData(type);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeDB();
        }
    }

    /**
     * ɾ����������
     */
    public synchronized void commonDeleteData(){
        try {
            commonDB.commonDeleteData();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeDB();
        }
    }

    /**
     * ����IDɾ������
     */
    public synchronized void commonDeleteData(final int type, final String id){
        try {
            commonDB.commonDeleteData(type,id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeDB();
        }
    }

    /**
     * ��ȡCount
     */
    public int getCount(int type){
        int count = 0;
        try {
            mCursor = commonDB.commonGetData(type);
            if( mCursor.moveToNext()){
                count = mCursor.getInt(mCursor.getColumnIndex("count"));
                return count ;
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
        return 0;
    }

    /**
     * ����ID��ȡCount
     */
    public int getCount(int type,String id){
        int count = 0;
        try {
            mCursor = commonDB.commonGetData(type,id);
            if(mCursor.moveToNext()){
                count = mCursor.getInt(mCursor.getColumnIndex("count"));
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
        return count  ;
    }

    /**
     * ����type��ȡnext
     */
    public int getNext(int type){
        int next = 0;
        try {
            mCursor = commonDB.commonGetData(type);
            if(mCursor.moveToNext()){
                next = mCursor.getInt(mCursor.getColumnIndex("next"));
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
        return next  ;
    }

    /**
     * ����ID��type��ȡnext
     */
    public int getNext(int type,String id){
        int next = 0;
        try {
            mCursor = commonDB.commonGetData(type, id);
            if(mCursor.moveToNext()){
                next = mCursor.getInt(mCursor.getColumnIndex("next"));
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
        return next  ;
    }

    /**
     * ����ID��type��ȡnext
     */
    public int getNext(int type,long id){
        int next = 0;
        try {
            mCursor = commonDB.commonGetData(type, id + "");
            if(mCursor.moveToNext()){
                next = mCursor.getInt(mCursor.getColumnIndex("next"));
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally{
            closeDB();
        }
        return next  ;
    }

    /**
     * �޸�Next
     * @param type
     * @param next
     * @return
     */
    public int updateNext(int type, int next) {
        int i = -1;
        try {
            i = commonDB.updateNext(type, next);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return i;
    }

    /**
     * �ر����ݿ�
     */
    public void closeDB() {
        if (mCursor != null) {
            mCursor.close();
        }
        if (commonDB != null) {
            commonDB.endTransaction();
            commonDB.close();
        }
    }
}
