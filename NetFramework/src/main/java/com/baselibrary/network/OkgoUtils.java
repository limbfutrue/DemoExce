package com.baselibrary.network;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.baselibrary.base.AppConfig;
import com.baselibrary.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.PostRequest;
import com.baselibrary.base.MyApplication;

import java.util.Map;

/**
 * Created by Libaoming on 25/1/2018.
 * 15 hour 34 minute
 * project_name : visitors
 */

public class OkgoUtils {

    /**
     * 数据请求
     * @param context
     * @param url
     * @param biz
     * @return
     */
    public static PostRequest postNet(Context context, String url, Map biz){
        Log.e("callback", "请求参数 : "+biz);
        Log.e("callback", "请求URL == : "+url);
        return OkGo.post(url)
                .tag(context)
                .params("companyId", AppConfig.getInstance().obtainCompanyId())
                .params(biz);
    }
    /**
     * 数据请求
     * @param context
     * @param url
     * @return
     */
    public static PostRequest postNet(Context context, String url){
        Log.e("callback", "请求URL == : "+url);
        return OkGo.post(url)
                .tag(context)
                .params("companyId", AppConfig.getInstance().obtainCompanyId());
    }

    /**
     * 获取wlan芯片的mac地址，是另一个唯一ID。但是你需要为你的工程加入android.permission.ACCESS_WIFI_STATE 权限，否则这个地址会为null
     * @param context
     * @return
     */
    public static String getWlanId(Context context){
        WifiManager wm = (WifiManager)context.getSystemService(MyApplication.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();
        return m_szWLANMAC;
    }
}
