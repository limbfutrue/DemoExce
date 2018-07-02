package com.libaoming.demoexce.demoexce.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Libaoming on 14/5/2018.
 * 14 hour 07 minute
 * project_name : DemoExce
 */

public class PathTest extends View {

    private Path path;
    private Paint mPaint;


    public PathTest(Context context) {
        super(context);
        init(context);
    }

    public PathTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PathTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mPaint = new Paint();
        path = new Path();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        canvas.scale(1,-1);
        path.lineTo(100,100);

        RectF rectF = new RectF(0,0,300,300);
        path.arcTo(rectF,0,270);
        canvas.drawPath(path,mPaint);

    }
}
