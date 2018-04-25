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

import com.baselibrary.base.basemvp.TitleBar;
import com.visitor.lc.baselibrary.R;

/**
 * 带标题栏的公共Activity
 */
public abstract class MyBaseAct extends AppCompatActivity {

    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View contentView = getLayoutInflater().inflate(getLayoutResId(), null);
        contentView.setLayoutParams(params);
        //处理是否显示标题栏
        titleBar = obtainTitleBarLayout();
        if (titleBar != null) {
            setContentView(titleBar.initTitleBar(this,contentView));
            initTitleBarData(titleBar);
        } else {
            setContentView(contentView);
        }

        ActManager.getManager().addActivity(this);
        initView();
        initListener();
        initData();
    }


    /**
     * 初始化标题栏布局
     */
    public abstract TitleBar obtainTitleBarLayout();

    /**
     * 初始化标题栏数据
     * @param titleBar
     */
    public void initTitleBarData(TitleBar titleBar){}

    /**
     * 获取子布局
     *
     * @return
     */
    public abstract int getLayoutResId();

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



    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActManager.getManager().removeActivity(this);
    }
}
