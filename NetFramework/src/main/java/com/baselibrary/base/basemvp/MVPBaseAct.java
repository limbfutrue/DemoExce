package com.baselibrary.base.basemvp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baselibrary.base.ActManager;
import com.baselibrary.base.MyBaseAct;
import com.baselibrary.utils.ToastUtils;
import com.visitor.lc.baselibrary.R;

/**
 * Created by Libaoming on 24/4/2018.
 * 15 hour 32 minute
 * project_name : DemoExce
 */

public abstract class MVPBaseAct<V extends MVPBaseView , P extends MVPBasePresenter<V>> extends AppCompatActivity implements MVPBaseView,View.OnClickListener{
    public P presenter;
    public TitleBar titleBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏字体颜色为深色
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View contentView = getLayoutInflater().inflate(getLayoutResId(), null);
        contentView.setLayoutParams(params);
        titleBar = obtainTitleBarLayout();
        //处理是否显示标题栏
        if (titleBar != null) {
            setContentView(titleBar.initTitleBar(this,contentView));
            initTitleBarData(titleBar);
        } else {
            setContentView(contentView);
        }
        ActManager.getManager().addActivity(this);
        presenter = createPresenter();
        if (presenter!=null){
            presenter.attachView((V) this);
        }
        initView(savedInstanceState);
        initView();
        initListener();
        obtainNetData();
    }

    public void initView(Bundle savedInstanceState) {

    }

    /**
     * 初始化控件监听
     */
    public void initListener(){}

    /**
     * 获取网络数据
     */
    public void obtainNetData(){}

    @Override
    public void showToast(String info) {
        ToastUtils.showShortToast(info);
    }

    /**
     * 初始化布局
     */
    public abstract int getLayoutResId() ;

    /**
     * 获取p层对象
     * @return
     */
    public abstract P createPresenter();

    /**
     * 初始化布局控件
     */
    public abstract void initView();

    /**
     * 初始化标题栏布局
     */
    public abstract TitleBar obtainTitleBarLayout();

    /**
     * 初始化标题栏数据
     * @param titleBar
     */
    public abstract void initTitleBarData(TitleBar titleBar);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null) {
            presenter.detachView();
        }
        ActManager.getManager().removeActivity(this);
    }

    @Override
    public void onClick(View v) {

    }
}
