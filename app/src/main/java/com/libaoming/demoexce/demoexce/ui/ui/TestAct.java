package com.libaoming.demoexce.demoexce.ui.ui;

import android.graphics.Color;

import com.baselibrary.utils.tabswitch.TabView;
import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.base.BaseActivity;

public class TestAct extends BaseActivity {
    private TabView mTbv;

    @Override
    public int getLayoutId() {
        return R.layout.act_test;
    }

    @Override
    public void afterInitView() {

        mTbv = (TabView) findViewById(R.id.tbv);
        int[] cL = {Color.BLUE,Color.CYAN};
        String[] tL = {"首页","我的","TA"};
        mTbv.addTab(this,3,tL,cL);
        mTbv.addFragmentAndBindTab(this,new MyFragment(),new BlankFragment(),new MyFragment());
    }

    @Override
    public void loadNetWork() {

    }
}
