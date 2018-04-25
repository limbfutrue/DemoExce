package com.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by dada on 2017/7/14.
 */

public class PermissionsJugeUtils {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    //SD卡读写权限
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};
    //录音权限
    private static String[] PERMISSIONS_RECORD_AUDIO = {
            "android.permission.RECORD_AUDIO"};
    //短信权限
    private static String[] PERMISSIONS_SEND_SMS = {
            "android.permission.SEND_SMS"};
    //读取短信权限
    private static String[] PERMISSIONS_READ_SMS = {
            "android.permission.READ_SMS"};
    //打电话权限
    private static String[] PERMISSIONS_PHONE = {
            "android.permission.CALL_PHONE"};
    //打开相机权限
    private static String[] PERMISSIONS_TOKEN_CAMERA = {
            "android.permission.CAMERA"};
    //定位权限
    private static String[] PERMISSIONS_LOCATION = {
            "android.permission.ACCESS_COARSE_LOCATION",
            "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.CHANGE_WIFI_STATE",
            "android.permission.ACCESS_WIFI_STATE"};

    /**
     * 是否有发送短信权限
     *
     * @param activity
     * @return
     */
    public static boolean verifySendMsmPermissions(Activity activity) {

        try {
            //检测是否有短信权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.SEND_SMS");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有短信权限，去申请短信权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_RECORD_AUDIO, REQUEST_EXTERNAL_STORAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 是否有发送短信权限
     *
     * @param context
     * @return
     */
    public static boolean verifyReadMsmPermissions(Context context) {

        try {
            //检测是否有短信权限
            int permission = ActivityCompat.checkSelfPermission(context,
                    "android.permission.READ_SMS");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有短信权限，去申请短信权限，会弹出对话框
                ActivityCompat.requestPermissions((Activity) context, PERMISSIONS_READ_SMS, REQUEST_EXTERNAL_STORAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 是否有拍照权限
     *
     * @param context
     * @return
     */
    public static boolean verifyCAMERAPermissions(Context context) {

        try {
            //检测是否有短信权限
            int permission = ActivityCompat.checkSelfPermission(context,
                    "android.permission.CAMERA");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有短信权限，去申请短信权限，会弹出对话框
                ActivityCompat.requestPermissions((Activity) context, PERMISSIONS_TOKEN_CAMERA, REQUEST_EXTERNAL_STORAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 是否有录音权限
     *
     * @param activity
     * @return
     */
    public static boolean verifyRecodePermissions(Activity activity) {

        try {
            //检测是否有录音权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.RECORD_AUDIO");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有录音权限，去申请录音权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_RECORD_AUDIO, REQUEST_EXTERNAL_STORAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 是否有写入SD卡的权限
     *
     * @param activity
     * @return
     */
    public static boolean verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 是否有打电话的权限
     *
     * @param activity
     * @return
     */
    public static boolean verifyPhonePermissions(Activity activity) {

        try {
            //检测是否有拨打电话权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.CALL_PHONE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有打电话权限，去申请电话权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_PHONE, REQUEST_EXTERNAL_STORAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 是否有定位的权限
     *
     * @param activity
     * @return
     */
    public static boolean verifyLocationPermissions(Activity activity) {

        try {
            //检测是否有定位权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.ACCESS_COARSE_LOCATION");
            int permission1 = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.ACCESS_FINE_LOCATION");
            int permission2 = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.CHANGE_WIFI_STATE");
            int permission3 = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.ACCESS_WIFI_STATE");
            if (permission != PackageManager.PERMISSION_GRANTED
                    || permission1 != PackageManager.PERMISSION_GRANTED
                    || permission2 != PackageManager.PERMISSION_GRANTED
                    || permission3 != PackageManager.PERMISSION_GRANTED) {
                // 没有定位权限，去申请定位权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_LOCATION, REQUEST_EXTERNAL_STORAGE);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
