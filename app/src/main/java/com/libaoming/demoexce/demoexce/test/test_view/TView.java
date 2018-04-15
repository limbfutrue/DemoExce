package com.libaoming.demoexce.demoexce.test.test_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by Libaoming on 9/1/2018.
 * 17 hour 09 minute
 * project_name : DemoExce
 */

public class TView extends View {
    private Paint fontPaint;
    private boolean isDown = true;
    private int count;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isDown) {
                if (msg.what == 1) {
                    count += 2;
                    if (12 + count > 150) {
                        Toast.makeText(getContext(), "shibai", Toast.LENGTH_SHORT).show();
                    } else {
                        invalidate();
                        handler.sendEmptyMessageDelayed(1, 100);
                    }
                }
            }
        }
    };
    private Paint rectPaint;
    private Path topPath;
    private Paint rightPaint;
    private Path rightPath;
    private int len;


    public TView(Context context) {
        super(context);
        init(context);
    }

    public TView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        fontPaint = new Paint();
        fontPaint.setColor(Color.BLACK);
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLUE);
        rightPaint = new Paint();
        rightPaint.setColor(Color.GREEN);
        topPath = new Path();
        rightPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        topPath.reset();
        topPath.moveTo(300, 300 + count);
        topPath.lineTo(600, 300 + count);
        topPath.lineTo(400, 450 + count);
        topPath.lineTo(100, 450 + count);
        topPath.close();
        canvas.drawPath(topPath, fontPaint);

        Rect rect = new Rect(100, 450 + count, 400, 750);
        canvas.drawRect(rect, rectPaint);

        rightPath.reset();
        rightPath.moveTo(600, 300 + count);
        rightPath.lineTo(400, 450 + count);
        rightPath.lineTo(400, 750);
        rightPath.lineTo(600, 600);
        rightPath.close();
        canvas.drawPath(rightPath, rightPaint);
        if (isDown) {
            canvas.drawCircle(350, 375 + count, 50, rightPaint);
        }else {
            canvas.drawCircle(350+len, 375 - len, 50, rightPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                handler.sendEmptyMessageDelayed(1, 100);
                break;

            case MotionEvent.ACTION_UP:
                isDown = false;
                fontPaint.setTextSize(12);
                len = count+100;
                count = 0;
                invalidate();

                break;
        }
        return true;
    }
}
