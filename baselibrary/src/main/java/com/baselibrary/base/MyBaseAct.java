package com.baselibrary.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.visitor.lc.baselibrary.R;

/**
 * 带标题栏的公共Activity
 */
public abstract class MyBaseAct extends AppCompatActivity {

    private ImageView mIvLeft, mIvRight;
    private TextView mTvLeft, mTvRight, mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View contentView = getLayoutInflater().inflate(getLayoutResId(), null);
        contentView.setLayoutParams(params);
        //处理是否显示标题栏
        if (isShowTitleBar()) {
            LinearLayout titleBarView = (LinearLayout) getLayoutInflater().inflate(R.layout.act_my_base, null);
            titleBarView.addView(contentView);
            setContentView(titleBarView);
            initTitleBarView();
            initTitleBar();
        } else {
            setContentView(contentView);
        }
        ActManager.getManager().addActivity(this);
        initView();
        initListener();
        initData();
    }

    /**
     * 初始化标题栏对象
     */
    private void initTitleBarView() {
        mIvLeft = (ImageView) findViewById(R.id.iv_left_icon);
        mTvLeft = (TextView) findViewById(R.id.tv_left_text);
        mIvRight = (ImageView) findViewById(R.id.iv_right_icon);
        mTvRight = (TextView) findViewById(R.id.tv_right_text);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
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
     * 是否显示标题栏
     *
     * @return
     */
    public abstract boolean isShowTitleBar();

    /**
     * 获取子布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 初始化标题栏数据
     */
    public abstract void initTitleBar();

    /**
     * 初始化布局
     */
    public void initView() {
    }

    /**
     * 初始化监听
     */
    public void initListener() {
    }

    /**
     * 初始化网络数据
     */
    public void initData() {
    }

    /**
     * 设置 标题栏 内容
     * @param leftRes 左侧图标
     * @param leftText 左侧文字
     * @param title 标题文字
     * @param rightRes 右侧图标
     * @param rightText 右侧文字
     */
    public void setTitleBarContent(int leftRes, String leftText, String title, int rightRes, String rightText) {
        setTTitle(title)
                .setLeftIcon(leftRes)
                .setLeftText(leftText)
                .setRightIcon(rightRes)
                .setRightText(rightText);
    }

    /**
     * 设置标题栏标题
     *
     * @param title
     * @return
     */
    private MyBaseAct setTTitle(String title) {
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
    private MyBaseAct setLeftIcon(int leftRes) {
        if (mIvLeft == null) {
            return this;
        }
        if (leftRes == 0) {
            mIvLeft.setVisibility(View.GONE);
        } else {
            mIvLeft.setVisibility(View.VISIBLE);
        }
        mIvLeft.setImageResource(leftRes);
        return this;
    }

    /**
     * 设置标题栏左侧文字
     *
     * @param leftText
     * @return
     */
    private MyBaseAct setLeftText(String leftText) {
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
    private MyBaseAct setRightIcon(int rightRes) {
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
    private MyBaseAct setRightText(String rightText) {
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
}
