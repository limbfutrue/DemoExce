package com.libaoming.demoexce.demoexce.utils.formattime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Libaoming on 2/1/2018.
 * 17 hour 01 minute
 * project_name : DemoExce
 */

public class DateUtil {


    /**
     * 获取当前哪一年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取当前哪一月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取当前哪一天
     *
     * @return
     */
    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取当前某小时
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * 获取当前某分
     *
     * @return
     */
    public static int getCurrentMinite() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.MINUTE);
        return hour;
    }

    /**
     * 获取当前某秒
     *
     * @return
     */
    public static int getCurrentSecond() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.SECOND);
        return hour;
    }

    /**
     * 根据月份获取每月的天数
     *
     * @return 每月的总天数
     */
    public static int getDayNum(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
        return 0;
    }

    /**
     * 是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    /**
     * 根据年月日获取星期几
     *
     * @param y 年
     * @param m 月
     * @param d 日
     * @return 星期几 0 代表周日
     */
    public static int getWeek(int y, int m, int d) {
        if (m < 3) {
            m += 12;
            --y;
        }
        int w = (d + 1 + 2 * m + 3 * (m + 1) / 5 + y + (y >> 2) - y / 100 + y / 400) % 7;
        return w;
    }

    /**
     * 格式化日期
     *
     * @param dateStr       日期字符串
     * @param dateFormatStr 格式化 模式
     * @param formatStr
     * @return
     */
    public static String formatDate(String dateStr, String dateFormatStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat s = new SimpleDateFormat(formatStr);
        return s.format(date);
    }

    /**
     * 取得今天，昨天，前天，明天，后天...的日期
     *
     * @param sel --- 0->当天    -1->昨天     -2->前天      1->明天      2->后天  ......
     * @return ------- 返回指定日期  如果sel == -1 返回的就是昨天的日期    sel==0 返回的就是当天你的日期  sel== 20 返回的就是20天后的日期
     * sel 代表的就是 sel天前   sel天后
     */
    public static String getSelDate(int sel) {
        String str = "";
        //格式化日期格式
        SimpleDateFormat df = new SimpleDateFormat("M" + "月" + "d" + "日");
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, sel);
        str = df.format(calendar.getTime());
        return str;
    }


    public static String getSelDateStr(int sel) {
        switch (sel) {
            case -2:
                return getSelDate(sel);
            case -1:
                return getSelDate(sel);
            case 0:
                return getSelDate(sel);
            case 1:
                return getSelDate(sel);
            case 2:
                return getSelDate(sel);
            default:
                return getSelDate(sel);
        }
    }
}
