package com.baselibrary.utils.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.lang.reflect.Field;

/**
 * Created by dada on 2017/6/4.
 */

public class MyNumberPicker extends NumberPicker {
    private Context context;
    public MyNumberPicker(Context context) {
        super(context);
        this.context = context;
    }

    public MyNumberPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyNumberPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    private void updateView(View child) {
        if(child instanceof EditText){
            ((EditText) child).setTextSize(19f);
            ((EditText) child).setTextColor(Color.BLACK);
        }
        initNumberPicker();
    }

    /**
     * 初始化滚动框布局
     */
    private void initNumberPicker() {
        //设置焦点
        setFocusable(false);
        setFocusableInTouchMode(false);
        setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS); // 关闭编辑模式
    }
    /**
     * 自定义滚动框分隔线颜色
     */
    public void setNumberPickerDividerColor(Context context, NumberPicker number, int colorResId) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(number, new ColorDrawable(ContextCompat.getColor(context, colorResId)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void setOnValueChangedListener(OnValueChangeListener onValueChangedListener) {
        super.setOnValueChangedListener(onValueChangedListener);
    }
}
