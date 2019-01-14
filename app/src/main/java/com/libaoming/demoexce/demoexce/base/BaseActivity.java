package com.libaoming.demoexce.demoexce.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

public abstract class BaseActivity extends AppCompatActivity {

    public BaseActivity mBaseContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mBaseContext = this;
        getWindow().setBackgroundDrawable(null);
        setContentView(getLayoutId());
        afterInitView();
        loadNetWork();
    }

    /**
     * 获取布局文件Id
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化布局
     */
    public View initView() {
        return View.inflate(this, getLayoutId(), null);
    }

    /**
     * 初始化布局之后的逻辑
     */
    public abstract void afterInitView();

    /**
     * 加载数据
     */
    public abstract void loadNetWork();

    @Override
    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }

    //环信清除通知栏
    @Override
    protected void onResume() {
        super.onResume();
        // cancel the notification
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
