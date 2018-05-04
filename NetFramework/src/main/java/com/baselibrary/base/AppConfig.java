package com.baselibrary.base;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
/*
 * Libaoming
 *
 */
public class AppConfig {
    private static final String TAG = "AppConfig";
    private static final boolean D = false;
    private static AppConfig mySelf = null;
    SharedPreferences preferences = null;
    public static final String PREFERENCES_FILE_NAME = "Preferences";
    public static final String USER_ID = "userId";
    public static final String KEEP_PASSWORD = "keep_password";//记住密码

    //是否登录
    public static final String IS_LOGIN = "isLogin";
    //是否已经第一次登录过了
    public static final String HAS_LOGIN_FIRST = "hasLoginFirst";
    //第一次启动应用
    public static final String IS_FIRST = "isFirst";
    //当前版本号
    public static final String CURR_VERSION = "CurrVersion";


    public static AppConfig getInstance() {
        if (mySelf == null) {
            mySelf = new AppConfig();
        }
        return mySelf;
    }

    private AppConfig() {
        try {
            preferences = MyApplication.instance.getSharedPreferences(PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        } catch (Exception ex) {
            if (D) {
                Log.e(TAG, ex.getMessage());
            }
        }
    }

    public void preferencesSetString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    private void preferenceDelete(String key) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }

    private void preferencesSetInt(String key, int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private void preferencesSetBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private int preferencesGetInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public String preferencesGetString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    private boolean preferencesGetBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }


    public void setUserId(String userId) {
        preferencesSetString(USER_ID, userId);
    }

    public String getUserId() {
        return preferencesGetString(USER_ID, "");
    }

    public void setToken(String token) {
        preferencesSetString("token", token);
    }

    public String getToken() {
        return preferencesGetString("token", "");
    }

    //存登录手机号和密码start

    public void setKeepPassword(boolean b) {
        preferencesSetBoolean(KEEP_PASSWORD, b);
    }

    public boolean getKeepPassword() {
        return preferencesGetBoolean(KEEP_PASSWORD, false);
    }

    public void setLoginMobile(String userId) {
        preferencesSetString("LoginMobile", userId);
    }

    public String getLoginMobile() {
        return preferencesGetString("LoginMobile", "");
    }

    public void setLoginPwd(String userId) {
        preferencesSetString("LoginPwd", userId);
    }

    public String getLoginPwd() {
        return preferencesGetString("LoginPwd", "");
    }
    //存登录手机号和密码end


    public boolean getIsFirst() {
        return preferencesGetBoolean(IS_FIRST, true);
    }

    public void setIsFirst(boolean b) {
        preferencesSetBoolean(IS_FIRST, b);
    }


    public int getCurrVersion() {
        return preferencesGetInt(CURR_VERSION, -1);
    }

    public void setCurrVersion(int version) {
        preferencesSetInt(CURR_VERSION, version);
    }

    public void setIsLogin(boolean b) {
        preferencesSetBoolean(IS_LOGIN, b);
    }

    public boolean getIsLogin() {
        return preferencesGetBoolean(IS_LOGIN, false);
    }

    public void setHasLoginSuccess(boolean b) {
        preferencesSetBoolean(HAS_LOGIN_FIRST + "_" + getUserId(), b);
    }

    public boolean getHasLoginSuccess() {
        return preferencesGetBoolean(HAS_LOGIN_FIRST + "_" + getUserId(), false);
    }

    public void setLoginInfo(String userInfo){
        preferencesSetString("userInfo",userInfo);
    }
//    public LoginBean getLoginInfo(){
//        Gson gson = new Gson();
//        LoginBean loginBean = gson.fromJson(preferencesGetString("userInfo",""),LoginBean.class);
//        return loginBean;
//    }
}