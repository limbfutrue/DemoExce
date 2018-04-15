package com.libaoming.demoexce.demoexce.test.test_mvpmodel_demo.view;

import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.base.BaseActivity;
import com.libaoming.demoexce.demoexce.test.test_mvpmodel_demo.presenter.MvpPresenter;

public class MvpTestActivity extends BaseActivity implements IMvpTestView {
    private MvpPresenter mvpPresenter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_mvp_test;
    }

    @Override
    public void afterInitView() {
        mvpPresenter = new MvpPresenter(this);
    }

    @Override
    public void loadNetWork() {
        mvpPresenter.requestData(this);
    }

    @Override
    public void dealwithView1() {

    }
}
