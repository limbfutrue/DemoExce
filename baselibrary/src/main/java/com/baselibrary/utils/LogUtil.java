package com.baselibrary.utils;

import android.util.Log;


/**
 * 创建： dongshuaijun .
 * 日期：2016/6/17.
 * 注释：Log统一管理类
 */
public class LogUtil {
    private static final boolean isLog = true;
    private static final String DEFAULT_TAG = "callback";

    public static void log(String tag, int level, String msg, Throwable tr) {
        if (isLog) {
            switch (level) {
                case Log.VERBOSE:
                    if (tr == null) {
                        Log.v(tag, msg);
                    } else {
                        Log.v(tag, msg, tr);
                    }
                    break;
                case Log.INFO:
                    if (tr == null) {
                        Log.i(tag, msg);
                    } else {
                        Log.i(tag, msg, tr);
                    }
                    break;
                case Log.DEBUG:
                    if (tr == null) {
                        Log.d(tag, msg);
                    } else {
                        Log.d(tag, msg, tr);
                    }
                    break;
                case Log.WARN:
                    if (tr == null) {
                        Log.w(tag, msg);
                    } else {
                        Log.w(tag, msg, tr);
                    }
                    break;
                case Log.ERROR:
                    if (tr == null) {
                        Log.e(tag, msg, tr);
                    } else {
                        Log.e(tag, msg, tr);
                    }

                    break;
            }
        }

    }

    public static void log(String tag, int level, String msg) {
        if (isLog) {
            log(tag, level, msg, null);
        }

    }

    public static void log(String msg) {
        if (isLog) {
            log(DEFAULT_TAG, Log.INFO, msg, null);
        }
    }


    private static final String TAG = "callback";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isLog)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (isLog)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isLog)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isLog)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isLog)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isLog)
            Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isLog)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isLog)
            Log.i(tag, msg);
    }

}
