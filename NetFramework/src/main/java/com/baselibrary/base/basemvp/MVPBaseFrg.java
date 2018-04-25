package com.baselibrary.base.basemvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Libaoming on 24/4/2018.
 * 17 hour 10 minute
 * project_name : DemoExce
 */

public abstract class MVPBaseFrg<V extends MVPBaseView , P extends MVPBasePresenter<V>> extends Fragment implements MVPBaseView{
    public View footView;
    public P presenter;
    private TitleBar titleBar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        footView = inflater.inflate(getLayoutId(),container,false);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        footView = inflater.inflate(getLayoutId(),container, false);
        footView.setLayoutParams(params);

        titleBar = obtainTitleBarLayout();
        //处理是否显示标题栏
        if (titleBar != null) {
            footView = titleBar.initTitleBar(getActivity(),footView);
            initTitleBarData(titleBar);
        }
        initView(footView);
        initListener();
        presenter = createPresenter();
        if (presenter!=null){
            presenter.attachView((V) this);
        }
        obtainNetData();
        return footView;
    }

    @Override
    public void showToast(String info) {

    }
    public abstract int getLayoutId();
    /**
     * 初始化标题栏布局
     */
    public abstract TitleBar obtainTitleBarLayout();

    /**
     * 初始化标题栏数据
     * @param titleBar
     */
    public void initTitleBarData(TitleBar titleBar){}
    public abstract void initView(View view);

    public void initListener(){}

    public abstract void obtainNetData();

    /**
     * 获取p层对象
     * @return
     */
    public abstract P createPresenter();

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter!=null) {
            presenter.detachView();
        }
    }
}
