package com.baselibrary.base.basemvp;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.visitor.lc.baselibrary.R;

/**
 * Created by Libaoming on 25/4/2018.
 * 11 hour 28 minute
 * project_name : DemoExce
 */

public class TitleBar {

    private ImageView mIvRight,mIvLeft;
    private TextView mTvLeft,mTvTitle,mTvRight;
    private RelativeLayout mRlBar;

    public View initTitleBar(Activity act, View contentView){
        LinearLayout titleBarView = (LinearLayout) act.getLayoutInflater().inflate(R.layout.act_my_base, null);
        titleBarView.addView(contentView);
        initTitleBarView(titleBarView);
        return titleBarView;
    }

    /**
     * 初始化标题栏对象
     * @param view
     */
    private void initTitleBarView(View view) {
        mIvLeft = (ImageView) view.findViewById(R.id.iv_left_icon);
        mTvLeft = (TextView) view.findViewById(R.id.tv_left_text);
        mIvRight = (ImageView)view.findViewById(R.id.iv_right_icon);
        mTvRight = (TextView)view.findViewById(R.id.tv_right_text);
        mTvTitle = (TextView)view.findViewById(R.id.tv_title);
        mRlBar = (RelativeLayout)view.findViewById(R.id.rl_title_bar);
    }


    /**
     * 设置titleBar监听按键
     * @param listener
     */
    public void setTitleBarListener(final ITitleBarListener listener){
        mIvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclickLeft();
            }
        });
        mTvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclickLeft();
            }
        });

        mIvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclickRight();
            }
        });
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onclickRight();
            }
        });
    }

    /**
     * 设置 标题栏 内容
     * @param leftRes 左侧图标
     * @param leftText 左侧文字
     * @param title 标题文字
     * @param rightRes 右侧图标
     * @param rightText 右侧文字
     */
    public TitleBar setTitleBarContent(int leftRes, String leftText, String title, int rightRes, String rightText) {
        setTitle(title)
                .setLeftIcon(leftRes)
                .setLeftText(leftText)
                .setRightIcon(rightRes)
                .setRightText(rightText);
        return this;
    }
    /**
     * 设置 标题栏 内容
     * @param leftRes 左侧图标
     * @param leftText 左侧文字
     * @param title 标题文字
     */
    public TitleBar setTitleBarContent(int leftRes, String leftText, String title) {
        setTitle(title)
                .setLeftIcon(leftRes)
                .setLeftText(leftText)
                .setRightIcon(0)
                .setRightText("");
        return this;
    }
    /**
     * 设置标题栏标题
     *
     * @param title
     * @return
     */
    public TitleBar setTitle(String title) {
        if (mTvTitle == null) {
            return this;
        }
        mTvTitle.setText(title == null ? "" : title);
        return this;
    }

    /**
     * 设置标题栏左侧图标
     *
     * @param leftRes
     * @return
     */
    private TitleBar setLeftIcon(int leftRes) {
        if (mIvLeft == null) {
            return this;
        }
        if (leftRes == 0) {
            mIvLeft.setVisibility(View.GONE);
        } else {
            mIvLeft.setVisibility(View.VISIBLE);
        }
        try {
            mIvLeft.setImageResource(leftRes);
        }catch (Exception e){
            mIvLeft.setImageResource(R.mipmap.ic_back);
        }
        return this;
    }

    /**
     * 设置标题栏左侧文字
     *
     * @param leftText
     * @return
     */
    private TitleBar setLeftText(String leftText) {
        if (mTvLeft == null) {
            return this;
        }
        isShowView(mTvLeft, leftText);
        mTvLeft.setText(leftText == null ? "" : leftText);
        return this;
    }

    /**
     * 设置标题栏右侧图标
     *
     * @param rightRes
     * @return
     */
    private TitleBar setRightIcon(int rightRes) {
        if (mIvRight == null) {
            return this;
        }
        if (rightRes == 0) {
            mIvRight.setVisibility(View.GONE);
        } else {
            mIvRight.setVisibility(View.VISIBLE);
        }
        mIvRight.setImageResource(rightRes);
        return this;
    }

    /**
     * 设置标题栏右侧文字
     *
     * @param rightText
     * @return
     */
    private TitleBar setRightText(String rightText) {
        if (mTvRight == null) {
            return this;
        }
        isShowView(mTvRight, rightText);
        mTvRight.setText(rightText == null ? "" : rightText);
        return this;
    }

    /**
     * 是否显示标题栏子view
     *
     * @param textView
     * @param text
     */
    private void isShowView(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }
    public interface ITitleBarListener{
        void onclickLeft();
        void onclickRight();
    }

    public void setTitleBarBackgroundColor(int color){
        mRlBar.setBackgroundColor(color);
    }
}
