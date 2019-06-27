package com.example.kaoa;

import android.app.Application;

import com.jy.zsm.db.DaoMaster;
import com.jy.zsm.db.DaoSession;

public class MyApplication extends Application {

    private  static DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();
        initDb();
    }

    private void initDb() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "re.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());
        session = daoMaster.newSession();

    }

    public static DaoSession getSession(){
        return session;
    }

}
