package com.baselibrary.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 创建： dongshuaijun .
 * 日期：2016/6/17.
 * 注释：软键盘辅助类
 */
public class KeyBoardUtils {


    /**
     * 打卡软键盘
     *
     * @param view     输入框
     * @param mContext 上下文
     */
    public static void openKeybord(final View view, final Context mContext) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

                           public void run() {
                               InputMethodManager imm = (InputMethodManager) mContext
                                       .getSystemService(Context.INPUT_METHOD_SERVICE);
                               imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
                               imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                                       InputMethodManager.HIDE_IMPLICIT_ONLY);
                           }

                       },
                300);


    }


    /**
     * 关闭软键盘
     *
     * @param view
     * @param mContext 上下文
     */
    public static void closeKeybord(View view, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}
