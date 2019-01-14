package com.libaoming.demoexce.demoexce.test.test_view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author Z
 */
public class CustomViewExe1 extends View {
    public CustomViewExe1(Context context) {
        super(context);
    }

    public CustomViewExe1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewExe1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST){
            Log.e("callback", "onMeasure: widthMode111111");
        }
        if (widthMode == MeasureSpec.EXACTLY){
            Log.e("callback", "onMeasure:widthModeeeee");
        }
        if (widthMode == MeasureSpec.UNSPECIFIED){
            Log.e("callback", "onMeasure:widthModeuuuu");
        }
        if (heightMode == MeasureSpec.EXACTLY){
            Log.e("callback", "onMeasure:eeee");
        }
        if (heightMode == MeasureSpec.UNSPECIFIED){
            Log.e("callback", "onMeasure:uuuu");
        }
        Log.e("callback", "onMeasure: "+widthMode+"----"+heightMode);
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}

