package com.example.xiastars.myapplication;

import android.app.Activity;
import android.os.Bundle;

import com.example.xiastars.myapplication.db.MyDBType;
import com.example.xiastars.myapplication.db.MyService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private MyService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = new MyService(this);

        /** ���ҵ����ݲ������ӣ�����һ�������б���ֻ��Ҫ��ʾ�͹��ˣ���ô����ʹ�ù��õ����ݿ�*/
        List<UserInfo> users = new ArrayList<UserInfo>();
        /** �������ݿ� */
        mService.insert(MyDBType.NEWS,users);
        /** ��ȡ����*/
        List<UserInfo> cacheUser = (List<UserInfo>) mService.getData(MyDBType.NEWS);
        /**ɾ������*/
        mService.commonDeleteData(MyDBType.NEWS);
        /** �������ŷ�Ϊ�������ź͹������� */
        mService.insert(MyDBType.NEWS,users,"world");


        /** �����ݸ���ʱ����Ҫ�������������ҵĺ����б���Ҫ�ı��ע״̬ */
        UserInfo info = new UserInfo();
        /** ���������ݣ��޸ľ�����*/
        mService.insertOrUpdateMyObject(info,MyDBType.MY_FRIENDS);
        /** ��ȡ20������ **/
        mService.getRecentMyObject(20,MyDBType.MY_FRIENDS);


    }

}
