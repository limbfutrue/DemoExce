package com.libaoming.demoexce.demoexce;

import android.app.Application;

/**
 * Created by Libaoming on 19/12/2017.
 * 16 hour 47 minute
 * project_name : DemoExce
 */

public class MyApp extends Application {
    public static MyApp myApp;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }
}
