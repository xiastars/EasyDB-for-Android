package com.example.xiastars.myapplication;

/**
 * Created by xiastars on 2016/3/22.
 */
public class UserInfo {

    private String userName;
    private long createTime;
    private int userAge;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }


    public long getCreateTime() {
        return createTime;
    }

    public int getUserAge() {
        return userAge;
    }

    public String getUserName() {
        return userName;
    }





}
