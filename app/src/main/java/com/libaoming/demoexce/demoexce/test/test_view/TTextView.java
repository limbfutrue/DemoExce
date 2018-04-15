package com.libaoming.demoexce.demoexce.test.test_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Libaoming on 10/1/2018.
 * 10 hour 59 minute
 * project_name : DemoExce
 */

@SuppressLint("AppCompatCustomView")
public class TTextView extends TextView {
    private Paint paint;

    public TTextView(Context context) {
        super(context);
        init(context);
    }

    public TTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(getX()-getLeft(),getY()-getTop(),getWidth(),getHeight(),paint);
    }
}
