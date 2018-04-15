package com.libaoming.demoexce.demoexce.utils;

import android.widget.Toast;

import com.libaoming.demoexce.demoexce.MyApp;

/**
 * Created by Libaoming on 19/12/2017.
 * 16 hour 46 minute
 * project_name : DemoExce
 */

public class ToastUtil {
    public static void showShortToast(String toastContent){
        Toast.makeText(MyApp.myApp,toastContent,Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(String toastContent){
        Toast.makeText(MyApp.myApp,toastContent,Toast.LENGTH_LONG).show();
    }
}
