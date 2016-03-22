# EasyDB-for-Android
简单的数据库操作，应用于Android

        MyService mService = new MyService(this);

        /** 当我的数据并不复杂，比如一串新闻列表，我只需要显示就够了，那么，我使用公用的数据库*/
        List<UserInfo> users = new ArrayList<UserInfo>();
        /** 插入数据库 */
        mService.insert(MyDBType.NEWS,users);
        /** 获取数据*/
        List<UserInfo> cacheUser = (List<UserInfo>) mService.getData(MyDBType.NEWS);
        /**删除数据*/
        mService.commonDeleteData(MyDBType.NEWS);
        /** 假如新闻分为国际新闻和国内新闻 */
        mService.insert(MyDBType.NEWS,users,"world");


        /** 当数据复杂时，需要单条处理，比如我的好友列表，需要改变关注状态 */
        UserInfo info = new UserInfo();
        /** 插入新数据，修改旧数据*/
        mService.insertOrUpdateMyObject(info,MyDBType.MY_FRIENDS);
        /** 获取20个好友 **/
        mService.getRecentMyObject(20,MyDBType.MY_FRIENDS);
