package com.libaoming.demoexce.demoexce.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Libaoming on 4/5/2018.
 * 11 hour 32 minute
 * project_name : DemoExce
 *
 * 自定义折线图(未完成)
 */

public class LineChartView extends View {
    private Context context;
    private Paint mLineChartPaint;//折线画笔
    private int mLineColor = Color.GRAY;//默认折线画笔颜色

    private Paint mVLinePaint;//竖线画笔
    private int mVLineColor = Color.GRAY;//默认竖线画笔颜色


    private Paint mHLinePaint;//横线画笔
    private int mHLineColor = Color.GRAY;//默认横线画笔颜色

    //x轴坐标
    private List<String> xData = new ArrayList<>();

    //y轴坐标
    private List<String> yData = new ArrayList<>();


    public LineChartView(Context context) {
        super(context);
        init(context);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化参数
     * @param context
     */
    private void init(Context context){
        this.context = context;
        mLineChartPaint = new Paint();
        mVLinePaint = new Paint();
        mHLinePaint = new Paint();

        mLineChartPaint.setAntiAlias(true);
        mVLinePaint.setAntiAlias(true);
        mHLinePaint.setAntiAlias(true);

        mLineChartPaint.setColor(mLineColor);
        mVLinePaint.setColor(mVLineColor);
        mHLinePaint.setColor(mHLineColor);


    }

    /**
     * 测量宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取宽
        int width = obtainDefaultSize(getMinimumWidth(),widthMeasureSpec);
        //获取高
        int height = obtainDefaultSize(getMinimumHeight(),heightMeasureSpec);
        //设置宽高
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //x周起始结束坐标
        int startHX = getWidth()/10;
        int startHY = getHeight()-getWidth()/10;
        int endHY = getHeight()-getWidth()/10;
        int endHX = getWidth()-getWidth()/10;
        //Y轴起始结束坐标
        int startVX = getWidth()/10;
        int startVY = getWidth()/10;
        int endVX = getWidth()/10;
        int endVY = getHeight()-getWidth()/10;

        //画竖线（Y轴）
        canvas.drawLine(startVX,startVY,endVX,endVY,mVLinePaint);
        //画横线（X轴）
        canvas.drawLine(startHX,startHY,endHX,endHY,mVLinePaint);


        int vLength = endVY-startVY;//y轴线的长度
        int hLength = endHX-startHX;//x轴线的长度
        xData.add("1");
        xData.add("1");
        xData.add("1");
        xData.add("1");
        xData.add("1");

        yData.add("2");
        yData.add("2");
        yData.add("2");

        for (int i = 1; i < xData.size()+1; i++) {
            //画竖线（Y轴）
            canvas.drawLine(startVX+i*(hLength/xData.size()),startVY,endVX+i*(hLength/xData.size()),endVY,mVLinePaint);
            canvas.drawText(i+"",startVX+i*(hLength/xData.size()),endVY+20,mLineChartPaint);
        }

        for (int i = 1; i < yData.size()+1; i++) {
            //画横线（X轴）
            canvas.drawLine(startHX,startHY-i*(vLength/yData.size()),endHX,startHY-i*(vLength/yData.size()),mVLinePaint);

            canvas.drawText(i+"",startHX-20,startHY-i*(vLength/yData.size()),mLineChartPaint);

        }



    }



    /**
     * 获取view的size
     * @param size
     * @param measureSpec
     * @return
     */
    public int obtainDefaultSize(int size, int measureSpec) {
        int result = size;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            //未指定模式（MeasureSpec.UNSPECIFIED）
            //这个就是说，当前组件，可以随便用空间，不受限制。
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            //最大模式（MeasureSpec.AT_MOST）
            //这个也就是父组件，能够给出的最大的空间，
            // 当前组件的长或宽最大只能为这么大，当然也可以比这个小。
            case MeasureSpec.AT_MOST://内容包裹
                result = 100;
                break;
                //1.精确模式（MeasureSpec.EXACTLY）
                // 在这种模式下，尺寸的值是多少，那么这个组件的长或宽就是多少。
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }
}
