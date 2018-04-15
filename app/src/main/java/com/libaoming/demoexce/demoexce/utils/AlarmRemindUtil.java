package com.libaoming.demoexce.demoexce.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.libaoming.demoexce.demoexce.receiver.MyReceiver;

import java.util.Calendar;

/**
 * Created by Libaoming on 8/4/2018.
 * 10 hour 58 minute
 * project_name : DemoExce
 */

public class AlarmRemindUtil {
    /**
     *  设置提醒
     * @param context 上下文
     * @param hourOfDay 小时
     * @param minute 分钟
     * @param flag 一个提醒有一个唯一的flag
     */
    public static void addAlarm(Context context, int hourOfDay, int minute,int flag) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,2018);
        calendar.set(Calendar.MONTH,1);
        calendar.set(Calendar.DAY_OF_MONTH,5);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Intent intent = new Intent(context, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, flag, intent, 0);
        AlarmManager am;
        //获取系统进程
        am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        String tmps = "设置闹钟时间为：" + format(hourOfDay) + ":" + format(minute);
        Log.e("error_code", calendar.getTimeInMillis()+"onTimeSet: "+tmps);
    }

    /**
     * 格式化时间
     * @param x
     * @return
     */
    private static String format(int x) {
        String s = "" + x;
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }
}
