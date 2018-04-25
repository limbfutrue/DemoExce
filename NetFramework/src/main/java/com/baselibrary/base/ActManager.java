package com.baselibrary.base;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;

import com.baselibrary.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * mBaseContext 管理器
 * Created by wdf on 2016/7/8.
 */
public class ActManager {

    /**
     * 维护activity列表
     */
    private List<Activity> undestroyActivities;

    public ActManager() {
        undestroyActivities = new ArrayList<Activity>();
    }

    /**
     * 获取管理器
     *
     * @return
     */
    public static ActManager getManager() {
        return MyApplication.getInstance().activityManager;
    }

    /**
     * 获取activity
     *
     * @param activityClazz
     * @return
     */
    public <T extends Activity> T getActivity(Class<T> activityClazz) {
        if (CommonUtils.isListNull(undestroyActivities))
            return null;
        for (Activity activity : undestroyActivities) {
            if (activity != null && activity.getClass() == activityClazz) {
                return (T) activity;
            }
        }
        return null;
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        undestroyActivities.add(activity);
    }

    /**
     * 移除activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        undestroyActivities.remove(activity);
    }

    /**
     * 获取最后一个Activity
     *
     * @return
     */
    public Activity getLastActivity() {
        int size = undestroyActivities.size();
        if (size > 0) {
            return undestroyActivities.get(size - 1);
        }
        return null;
    }

    /**
     * 关闭除此之外所有activity
     *
     * @param calzz
     */
    public void finishActivityExceptThis(Class<?> calzz) {
        if (CommonUtils.isListNull(undestroyActivities))
            return;
        for (Activity activity : undestroyActivities) {
            if (activity != null && activity.getClass() != calzz) {
                activity.finish();
            }
        }
    }

    /**
     * finish指定activity
     *
     * @param calzz
     */
    public <T extends Activity> void finishActivity(Class<T> calzz) {
        T activity = getActivity(calzz);
        if (activity != null) {
            activity.finish();
        }
    }

    /**
     * 关闭所有activity
     * @param <T>
     */
    public <T extends Activity> void finishAllActivity() {
        if (CommonUtils.isListNull(undestroyActivities))
            return;
        for (Activity activity : undestroyActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
    }
    //---------------------------
    public static String getCurrentActivity(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        return cn.getShortClassName();
    }
    public static String getCurrentApp(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        return cn.getPackageName();
    }


    /**
     * 是否在后台运行
     * @param context
     * @return
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            for (ActivityManager.RunningTaskInfo item : tasks) {
                if(item.topActivity.getPackageName().equals(context.getPackageName())){
                    return true;
                }
            }
        }
        return false;

    }
}
