package com.libaoming.demoexce.demoexce.utils.customview;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.libaoming.demoexce.demoexce.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dada on 2017/5/26.
 */

public class CalenderUtil {
    //年
    private int year;
    //月
    private int month;
    //日
    private int day;
    //周几
    private int week;

    private Activity context;

    private ArrayList<CalenderDateBean> dateStr = new ArrayList<>();
    private int lastMonthDayNum;//上月天数
    private int monthNum;//本月天数
    private int wnum;//上个月日期在本月显示的天数
    private int nextOrLastMonth;
    public MyGAdapter adapter;

    public CalenderUtil(Activity context) {
        Calendar calendar = Calendar.getInstance();
        week = calendar.get(Calendar.DAY_OF_WEEK) - 1;//星期几
        year = calendar.get(Calendar.YEAR);//年
        month = calendar.get(Calendar.MONTH) + 1;//月 0~11
        day = calendar.get(Calendar.DAY_OF_MONTH);//日
        this.context = context;
    }

    /**
     * 初始化一个月的日历图              appointDay应该传一个日期的数组
     *
     * @param gridview
     * @param nextOrLastMonth 月份
     */
    public void initView(GridView gridview, int nextOrLastMonth) {
        dateStr.clear();
        isSelect = -1;//选择下个月时去掉上个月选中记录
        this.nextOrLastMonth = nextOrLastMonth;
        CalenderUtil calenderUtil = new CalenderUtil(context);
        monthNum = calenderUtil.getDayNum(nextOrLastMonth);//获取月份天数
        wnum = calenderUtil.getWeek(calenderUtil.getYear(), nextOrLastMonth, 1);//获取本月第一天周几
        for (int j = 0; j < 7; j++) {
            switch (j) {
                case 0:
                    dateStr.add(new CalenderDateBean("星期日", 0));
                    break;
                case 1:
                    dateStr.add(new CalenderDateBean("星期一", 0));
                    break;
                case 2:
                    dateStr.add(new CalenderDateBean("星期二", 0));
                    break;
                case 3:
                    dateStr.add(new CalenderDateBean("星期三", 0));
                    break;
                case 4:
                    dateStr.add(new CalenderDateBean("星期四", 0));
                    break;
                case 5:
                    dateStr.add(new CalenderDateBean("星期五", 0));
                    break;
                case 6:
                    dateStr.add(new CalenderDateBean("星期六", 0));
                    break;
            }
        }
        lastMonthDayNum = 0;
        if (nextOrLastMonth - 1 == 0) {
            lastMonthDayNum = calenderUtil.getDayNum(12);
        } else {
            lastMonthDayNum = calenderUtil.getDayNum(nextOrLastMonth - 1);
        }

        //本月上一个月的显示日期
        for (int k = wnum; k > 0; k--) {
            dateStr.add(new CalenderDateBean((lastMonthDayNum - k + 1) + "", 0));
        }
        //本月日期显示
        for (int j = 1; j <= monthNum; j++) {
            dateStr.add(new CalenderDateBean(j + "", 0));
        }
        //下个月日期显示
        int in = 49 - dateStr.size();
        for (int j = 1; j <= in; j++) {
            dateStr.add(new CalenderDateBean(j + "", 0));
        }

        adapter = new MyGAdapter();
        gridview.setAdapter(adapter);
    }

    public static int isSelect = -1;
    public class MyGAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dateStr.size();
        }

        @Override
        public Object getItem(int position) {
            return dateStr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder;
            if (convertView == null) {
                convertView = context.getLayoutInflater().inflate(R.layout.calender_item, parent, false);
                mHolder = new ViewHolder(convertView);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }
            if (nextOrLastMonth == month && dateStr.get(position).getDay().equals("" + day)) {
                mHolder.tv_cal_item.setText("今天");
                mHolder.tv_cal_item.setTextColor(Color.RED);
            } else {
                mHolder.tv_cal_item.setText(dateStr.get(position).getDay());
            }
            if(isSelect == position){
                //选中的背景

            }else {
                //未选中的背景
            }
            //日期字体颜色设置
//            if (nextOrLastMonth == Calendar.getInstance().get(Calendar.MONTH) + 1) {//是否是本月
                if ((position >= 7 && position <= wnum + 6) || position > monthNum + wnum + 6) {
                    mHolder.tv_cal_item.setTextColor(Color.parseColor("#999999"));
                }
//            }

            //设置“周一至周日日期提示”背景色为蓝色
            if (position < 7) {
                mHolder.tv_cal_item.setBackgroundColor(Color.parseColor("#5d9cec"));
                mHolder.tv_cal_item.setTextColor(Color.WHITE);
            }
            return convertView;
        }

        public class ViewHolder {
            private TextView tv_cal_item;
            private TextView tv_yue;

            public ViewHolder(View view) {
                tv_cal_item = (TextView) view.findViewById(R.id.tv_cal_item);
            }
        }
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getWeek() {
        return week;
    }

    /**
     * 根据月份获取每月的天数
     *
     * @return 每月的总天数
     */
    public int getDayNum(int month) {
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
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
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
     * 根据年月日获取星期几
     *
     * @param y 年
     * @param m 月
     * @param d 日
     * @return 星期几
     */
    public int getWeek(int y, int m, int d) {
        if (m < 3) {
            m += 12;
            --y;
        }
        int w = (d + 1 + 2 * m + 3 * (m + 1) / 5 + y + (y >> 2) - y / 100 + y / 400) % 7;
        return w;
    }


}
