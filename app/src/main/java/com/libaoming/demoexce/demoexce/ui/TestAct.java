package com.libaoming.demoexce.demoexce.ui;

import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.base.BaseActivity;

public class TestAct extends BaseActivity {

//    @Override
//    public boolean isShowTitleBar() {
//        return true;
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.act_test;
//    }
//
//    @Override
//    public void initTitleBar() {
//        setTitleBarContent(0,"返回","中间标题",0,"");
//        setTitleBarListener(new ITitleBarListener() {
//            @Override
//            public void onclickLeft() {
//                finish();
//            }
//
//            @Override
//            public void onclickRight() {
//                ToastUtil.showLongToast("测试点击");
//            }
//        });
//    }

    @Override
    public int getLayoutId() {
        return R.layout.act_test;
    }

    @Override
    public void afterInitView() {

    }

    @Override
    public void loadNetWork() {

    }
}
