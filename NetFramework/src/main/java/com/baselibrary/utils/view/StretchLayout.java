package com.baselibrary.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.RelativeLayout;

import com.baselibrary.utils.DensityUtils;

/**
 * 可层叠伸展的View
 * Created by Haoxunwang on 2016/3/25.
 */
public class StretchLayout extends RelativeLayout {

    public static final int STATUS_EXPANDED = 1;
    public static final int STATUS_COLLAPSED = 2;

    private int mStatus = STATUS_EXPANDED;
    private View mLayoutOver;
    private View mLayoutBottom;
    private int mOriginalBottomHeight;
    private int mOverDistance;
    private int mTouchSlop;
    private boolean mInitDataSucceed = false;
    private boolean mIsSticky = true;
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;
    private int mLastX = 0;
    private int mLastY = 0;
    private int topDistance = DensityUtils.dip2px(70);//默认距离顶部的距离
    private int bottomDistance = DensityUtils.dip2px(200);//上层覆盖下层底部的高度

    private OnGiveUpTouchEventListener mGiveUpTouchEventListner;

    public StretchLayout(Context context) {
        this(context, null);
    }

    public StretchLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && (mLayoutOver == null || mLayoutBottom == null)) {
            initData();
        }
    }

    private void initData() {
        if (getChildCount() < 2) {
            Log.e("error_code", "initData: StretchLayout必须包含两个子布局");
            throw new NullPointerException();
        }
        mLayoutBottom = getChildAt(0);
        mLayoutOver = getChildAt(1);
        mOriginalBottomHeight = mLayoutBottom.getMeasuredHeight();
        mOverDistance = mOriginalBottomHeight-bottomDistance;
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        if (mOverDistance > 0) {
            mInitDataSucceed = true;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int intercepted = 0;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastXIntercept = x;
                mLastYIntercept = y;
                mLastX = x;
                mLastY = y;
                intercepted = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaY) <= Math.abs(deltaX)) {
                    intercepted = 0;
                } else if (mStatus == STATUS_EXPANDED && deltaY <= -mTouchSlop) {
                    intercepted = 1;
                } else if (mGiveUpTouchEventListner != null) {
                    if (mGiveUpTouchEventListner.giveUpTouchEvent(event) && deltaY > mTouchSlop) {
                        intercepted = 1;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = 0;
                mLastXIntercept = mLastYIntercept = 0;
                break;
        }
        return intercepted != 0 && mIsSticky;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!mIsSticky)
            return true;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                mOverDistance += deltaY;
                updateViewDistance(mOverDistance);
                break;
            case MotionEvent.ACTION_UP:
                int destDistance = topDistance;
                if (mOverDistance <= mOriginalBottomHeight * 0.5) {
                    destDistance = topDistance;
                    mStatus = STATUS_COLLAPSED;
                } else {
                    destDistance = mOriginalBottomHeight-bottomDistance;
                    mStatus = STATUS_EXPANDED;
                }
                //慢慢滑向终点
                this.smoothSetBottomDistance(mOverDistance, destDistance, 500);
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void smoothSetBottomDistance(final int from, final int to, int duration) {
        final int frameCount = (int) (duration / 1000f * 30) + 1;
        final float partation = (to - from) / (float) frameCount;
        new Thread("smoothSetBottomDistance") {
            @Override
            public void run() {
                for (int i = 0; i < frameCount; i++) {
                    final int distance;
                    if (i == frameCount - 1) {
                        distance = to;
                    } else {
                        distance = (int) (from + partation * i);
                    }
                    post(new Runnable() {
                        @Override
                        public void run() {
                            updateViewDistance(distance);
                        }
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void updateViewDistance(int bottomDistance) {
        if (!mInitDataSucceed) {
            initData();
        }
        if (bottomDistance < topDistance) {
            bottomDistance = topDistance;
        } else if (bottomDistance > mOriginalBottomHeight) {
            bottomDistance = mOriginalBottomHeight-bottomDistance;
        }
        if (bottomDistance == topDistance) {
            mStatus = STATUS_COLLAPSED;
        } else {
            mStatus = STATUS_EXPANDED;
        }
        if (mLayoutOver != null && mLayoutOver.getLayoutParams() != null) {
            LayoutParams params = (LayoutParams) mLayoutOver.getLayoutParams();
            params.topMargin = bottomDistance;
            mLayoutOver.requestLayout();
            mOverDistance = bottomDistance;
        }
    }

    public int getOverDistance() {
        return mOverDistance;
    }

    public void setmIsSticky(boolean isSticky) {
        this.mIsSticky = isSticky;
    }

    /**
     * 外部ListView是否放弃touch事件监听
     */
    public interface OnGiveUpTouchEventListener {
        public boolean giveUpTouchEvent(MotionEvent event);
    }

    public void setOnGiveUpTouchEventListner(OnGiveUpTouchEventListener listner) {
        this.mGiveUpTouchEventListner = listner;
    }
}

