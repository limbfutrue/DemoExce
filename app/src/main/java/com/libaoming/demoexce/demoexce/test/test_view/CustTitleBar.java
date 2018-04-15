package com.libaoming.demoexce.demoexce.test.test_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.libaoming.demoexce.demoexce.R;

/**
 * Created by Libaoming on 11/1/2018.
 * 18 hour 56 minute
 * project_name : DemoExce
 */

public class CustTitleBar extends RelativeLayout {
    private int left_image;
    private String left_text;
    private TextView leftTextView;
    private int left_showImg;
    private float left_size;
    private int left_color;
    private String center_text;
    private float center_size;
    private int center_color;
    private TextView centerTextView;
    private int right_image;
    private String right_text;
    private int right_showImg;
    private float right_size;
    private int right_color;
    private TextView rightTextView;

    public CustTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Custom_Title_Bar);
        addLeft(context, ta);
        addCenter(context, ta);
        addRight(context,ta);
        addView(leftTextView);
        addView(centerTextView);
        addView(rightTextView);

    }

    /**
     * 添加左边布局
     * @param context
     * @param ta
     */
    private void addCenter(Context context, TypedArray ta) {
        LayoutParams centerParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        center_text = ta.getString(R.styleable.Custom_Title_Bar_center_text);
        center_size = ta.getDimension(R.styleable.Custom_Title_Bar_center_text_size, 16);
        center_color = ta.getColor(R.styleable.Custom_Title_Bar_center_text_color, Color.BLACK);
        centerTextView = new TextView(context);
        centerTextView.setText(center_text);
        centerTextView.setTextSize(center_size);
        centerTextView.setTextColor(center_color);
        centerTextView.setGravity(Gravity.CENTER_VERTICAL);
        centerParams.addRule(CENTER_IN_PARENT);
        centerTextView.setLayoutParams(centerParams);
    }

    /**
     * 添加右边布局
     * @param context
     * @param ta
     */
    private void addLeft(Context context, TypedArray ta) {
        LayoutParams leftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        left_image = ta.getResourceId(R.styleable.Custom_Title_Bar_left_image, 0);//左侧图片
        left_text = ta.getString(R.styleable.Custom_Title_Bar_left_text);//左侧文字
        left_showImg = ta.getInt(R.styleable.Custom_Title_Bar_left_showImg, 0);//0代表显示左边图片，其他代表不显示
        left_size = ta.getDimension(R.styleable.Custom_Title_Bar_left_text_size, 14);//左侧文字大小
        left_color = ta.getColor(R.styleable.Custom_Title_Bar_left_text_color, Color.BLACK);//左侧文字颜色

        //设置属性
        leftTextView = new TextView(context);
        leftParams.addRule(CENTER_VERTICAL);
        leftTextView.setText(left_text);
        leftTextView.setTextSize(left_size);
        leftTextView.setTextColor(left_color);
        leftTextView.setLayoutParams(leftParams);
        //是否显示左侧图片
        if (left_showImg == 0) {
            leftTextView.setCompoundDrawablesWithIntrinsicBounds(left_image, 0, 0, 0);
        } else {
            leftTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }
    /**
     * 添加右边布局
     * @param context
     * @param ta
     */
    private void addRight(Context context, TypedArray ta) {
        LayoutParams rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        right_image = ta.getResourceId(R.styleable.Custom_Title_Bar_right_image, 0);//左侧图片
        right_text = ta.getString(R.styleable.Custom_Title_Bar_right_text);//左侧文字
        right_showImg = ta.getInt(R.styleable.Custom_Title_Bar_right_showImg, 1);//0代表显示左边图片，其他代表不显示
        right_size = ta.getDimension(R.styleable.Custom_Title_Bar_right_text_size, 14);//左侧文字大小
        right_color = ta.getColor(R.styleable.Custom_Title_Bar_right_text_color, Color.BLACK);//左侧文字颜色

        //设置属性
        rightTextView = new TextView(context);
        rightParams.addRule(CENTER_VERTICAL);
        rightParams.addRule(ALIGN_PARENT_RIGHT);
        rightTextView.setText(right_text);
        rightTextView.setTextSize(right_size);
        rightTextView.setTextColor(right_color);
        rightTextView.setLayoutParams(rightParams);
        //是否显示左侧图片
        if (right_showImg == 0) {
            rightTextView.setCompoundDrawablesWithIntrinsicBounds(right_image, 0, 0, 0);
        } else {
            rightTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }
}
