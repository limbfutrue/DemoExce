package com.baselibrary.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baselibrary.utils.LogUtil;
import com.baselibrary.utils.ScreenUtils;
import com.baselibrary.utils.wheelView.OnWheelScrollListener;
import com.baselibrary.utils.wheelView.WheelView;
import com.baselibrary.utils.wheelView.adapter.NumericWheelAdapter;
import com.visitor.lc.baselibrary.R;

import java.util.Calendar;

public class BottomTimeDialog extends Dialog implements View.OnClickListener {

    private WheelView year;
    private WheelView month;
    private WheelView day;
    private WheelView time;
    private WheelView min;
    private WheelView sec;
    private TextView tv_title;

    private int mYear = 1996;
    private int mMonth = 0;
    private int mDay = 1;
    private Context ct;
    private int n_year;
    private int n_day;
    private int n_min;
    private int n_sec;
    private int curYear;
    private int curMonth;
    private int curDate;
    private int curHour;
    private int curMin;

    private String title;
    private NumericWheelAdapter numericWheelAdapter;
    private int norYear;

    public BottomTimeDialog(Context context, int theme) {
        super(context, theme);
        this.ct = context;
        initView();
    }

    public BottomTimeDialog(Context context, String title, int theme) {
        super(context, theme);
        this.ct = context;
        this.title = title;
        initView();
    }

    private void initView() {
        View view = View.inflate(ct, R.layout.time_dialog, null);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        year = (WheelView) view.findViewById(R.id.year);
        month = (WheelView) view.findViewById(R.id.month);
        day = (WheelView) view.findViewById(R.id.day);
        time = (WheelView) view.findViewById(R.id.time);
        min = (WheelView) view.findViewById(R.id.min);
        sec = (WheelView) view.findViewById(R.id.sec);

        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        Calendar c = Calendar.getInstance();
        norYear = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        curYear = norYear;
        curMonth = month + 1;
        curDate = day;
        curHour = hour;
        curMin = minute;

        if (!TextUtils.isEmpty(title)) {
            tv_title.setText(title);
        }

        // 日
        initDay();
        this.day.setCyclic(true);
        this.day.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter1 = new NumericWheelAdapter(ct, norYear, norYear + 20);
        numericWheelAdapter1.setLabel("年");
        year.setViewAdapter(numericWheelAdapter1);
        year.setCyclic(true);//是否可循环滑动
        year.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter2 = new NumericWheelAdapter(ct, 1, 12, "%02d");
        numericWheelAdapter2.setLabel("月");
        this.month.setViewAdapter(numericWheelAdapter2);
        this.month.setCyclic(true);
        this.month.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter3 = new NumericWheelAdapter(ct, 0, 59, "%02d");
        numericWheelAdapter3.setLabel("分");
        min.setViewAdapter(numericWheelAdapter3);
        min.setCyclic(true);
        min.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter5 = new NumericWheelAdapter(ct, 0, 23, "%02d");
        numericWheelAdapter5.setLabel("时");
        time.setViewAdapter(numericWheelAdapter5);
        time.setCyclic(true);
        time.addScrollingListener(scrollListener);

        NumericWheelAdapter numericWheelAdapter4 = new NumericWheelAdapter(ct, 0, 59, "%02d");
        numericWheelAdapter4.setLabel("秒");
        sec.setViewAdapter(numericWheelAdapter4);
        sec.setCyclic(true);
        sec.addScrollingListener(scrollListener);

        year.setVisibleItems(7);//设置显示行数
        this.month.setVisibleItems(7);
        this.day.setVisibleItems(7);
		time.setVisibleItems(7);
        min.setVisibleItems(7);
        sec.setVisibleItems(7);

        year.setCurrentItem(0);
        this.month.setCurrentItem(curMonth - 1);
        this.day.setCurrentItem(curDate - 1);
        time.setCurrentItem(curHour);
        min.setCurrentItem(curMin);
        sec.setCurrentItem(curSec);

        int width = ScreenUtils.getScreenWidth(ct);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, -2);
        view.setLayoutParams(params);
        setContentView(view, params);
        setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams p = getWindow().getAttributes();
        p.gravity = Gravity.BOTTOM;
        getWindow().setAttributes(p);
//        getWindow().setWindowAnimations(R.style.AnimationDialog);

    }

    /**
     * 是否显示年滑轮
     */
    public void showYear(boolean bln) {
        if (year != null) {
            if (bln) {
                year.setVisibility(View.VISIBLE);
            } else {
                year.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示月滑轮
     */
    public void showMonth(boolean bln) {
        if (month != null) {
            if (bln) {
                month.setVisibility(View.VISIBLE);
            } else {
                month.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示日滑轮
     */
    public void showDay(boolean bln) {
        if (day != null) {
            if (bln) {
                day.setVisibility(View.VISIBLE);
            } else {
                day.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示小时滑轮
     */
    public void showHour(boolean bln) {
        if (time != null) {
            if (bln) {
                time.setVisibility(View.VISIBLE);
            } else {
                time.setVisibility(View.GONE);
                min.setVisibility(View.GONE);
                sec.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示分钟滑轮
     */
    public void showMin(boolean bln) {
        if (min != null) {
            if (bln) {
                min.setVisibility(View.VISIBLE);
            } else {
                min.setVisibility(View.GONE);
                sec.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 是否显示秒钟滑轮
     */
    public void showSec(boolean bln) {
        if (sec != null) {
            if (bln) {
                sec.setVisibility(View.VISIBLE);
            } else {
                sec.setVisibility(View.GONE);
            }
        }
    }


    private int n_month;
    private int curSec;
    OnWheelScrollListener scrollListener = new OnWheelScrollListener() {

        @Override
        public void onScrollingStarted(WheelView wheel) {

        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            //年
            curYear = year.getCurrentItem() + norYear;//月
            curMonth = month.getCurrentItem() + 1;
            initDay();
            curDate = Integer.parseInt(numericWheelAdapter.getItemText(day.getCurrentItem()).toString());
            curHour = time.getCurrentItem();
            curMin = min.getCurrentItem();
            curSec = sec.getCurrentItem();
        }
    };

    /**
     * @param year
     * @param month
     * @return
     */
    private int getDay(int year, int month) {
        int day = 30;
        boolean flag = false;
        switch (year % 4) {
            case 0:
                flag = true;
                break;
            default:
                flag = false;
                break;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = 31;
                break;
            case 2:
                day = flag ? 29 : 28;
                break;
            default:
                day = 30;
                break;
        }
        return day;
    }

    /**
     */
    private void initDay() {
        numericWheelAdapter = new NumericWheelAdapter(ct, 1, getDay(curYear, curMonth), "%02d");
        numericWheelAdapter.setLabel("日");
        day.setViewAdapter(numericWheelAdapter);
    }

    private OnConfirmClickListener listener2;

    public void setListener2(OnConfirmClickListener listener2) {
        this.listener2 = listener2;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss();

        } else if (i == R.id.tv_confirm) {
            LogUtil.i(curYear + " " + curMonth + " " + curDate + " " + curHour + " " + curMin + " " + curSec);
            String hour = "";
            String min = "";
            String sec = "";
            if (listener2 != null) {
                if (curHour < 10) {
                    hour = "0" + curHour;
                } else {
                    hour = curHour + "";
                }
                if (curMin < 10) {
                    min = "0" + curMin;
                } else {
                    min = curMin + "";
                }
                if (curSec < 10) {
                    sec = "0" + curSec;
                } else {
                    sec = curSec + "";
                }
                listener2.onConfirmClickListener(curYear, curMonth, curDate, hour, min, sec);
            } else {
                LogUtil.i("listerner2为空");
            }
            dismiss();

        }
    }

    public interface OnConfirmClickListener {
        void onConfirmClickListener(int i1, int i2, int i3, String i4, String i5, String sec);
    }
}
