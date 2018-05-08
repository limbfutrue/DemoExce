package com.libaoming.demoexce.demoexce.ui;

import com.baselibrary.base.MyBaseAct;
import com.baselibrary.base.basemvp.TitleBar;
import com.libaoming.demoexce.demoexce.R;

public class TestAct extends MyBaseAct {
    @Override
    public TitleBar obtainTitleBarLayout() {
        return new TitleBar();
    }

    @Override
    public void initTitleBarData(TitleBar titleBar) {
        titleBar.setTitleBarContent(0,"","TestAct");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_test;
    }

    @Override
    public void initView() {
        super.initView();
    }
}
