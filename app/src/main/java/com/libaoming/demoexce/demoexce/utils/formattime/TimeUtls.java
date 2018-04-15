package com.libaoming.demoexce.demoexce.utils.formattime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Libaoming on 27/12/2017.
 * 09 hour 54 minute
 * project_name : DemoExce
 */

public class TimeUtls {

    /**
     *
     * @param time
     * @return
     */
    public static String formatTime(Object time){
        long timeFormat = 0;
        if (time instanceof String) {
            timeFormat = Long.parseLong((String) time);
        }else if (time instanceof Long || time instanceof Integer){
            timeFormat = (long) time;
        }
        int hour = (int) (timeFormat / 3600);//小时
        int balanceSecond = (int) (timeFormat % 3600);
        int minite = balanceSecond / 60;//分
        int second = balanceSecond % 60;//秒
        return hour + "时" + minite + "分" + second + "秒";
    }

    /**
     * 过滤掉字符串中所有非数字字符
     * @param str
     * @return
     */
    public static String matcherStr(String str) {
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(str);
        str = m.replaceAll("");
        return str;
    }
}
