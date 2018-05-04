package com.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;

/**
 * Created by Libaoming on 16/3/2018.
 * 09 hour 22 minute
 * project_name : MedicalEquipment
 * 网络是否连接
 */

public class NetIsConnectUtils {
    /**
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

    }

    /**
     * 获取WifiIp地址
     * @return
     */
    public static String obtainWifiIp(Context context){
        WifiManager my_wifiManager = ((WifiManager) context.getSystemService(Context.WIFI_SERVICE));
        DhcpInfo dhcpInfo = my_wifiManager.getDhcpInfo();
        WifiInfo wifiInfo = my_wifiManager.getConnectionInfo();
        return intToIp(wifiInfo.getIpAddress());
    }
    /**
     *
     * @param paramInt
     * @return
     */
    private static String intToIp(int paramInt) {
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "."
                + (0xFF & paramInt >> 24);
    }
    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent =  new Intent(Settings.ACTION_WIFI_SETTINGS);
        activity.startActivityForResult(intent,0x1111);
    }

}
