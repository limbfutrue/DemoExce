package com.baselibrary.base;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by Libaoming on 22/1/2018.
 * 10 hour 39 minute
 * project_name : MedicalEquipment
 * <p>
 * 用于 全局定义变量，初始化三方sdk
 */

public class MyApplication extends Application {
    public static MyApplication instance;
    public ActManager activityManager;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initManager();
        OkGo.getInstance().setConnectTimeout(1000*5);
    }

    private void initManager() {
        activityManager = new ActManager();
    }
}
