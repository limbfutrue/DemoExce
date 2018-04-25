package com.libaoming.demoexce.demoexce;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.baselibrary.base.basemvp.MVPBaseActivity;
import com.baselibrary.base.basemvp.TitleBar;
import com.libaoming.demoexce.demoexce.view.draggridview.DragGridView;
import com.libaoming.demoexce.demoexce.view.draggridview.GridViewAdapter;
import com.libaoming.demoexce.demoexce.view.dragview.GridViewItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MVPBaseActivity<MyView, MyPresenter> implements View.OnClickListener, MyView {


    private int i = 0;
    private String videoPath;
    private DragGridView gv;
    private List<GridViewItem> list = new ArrayList<>();
    private FragmentTransaction ft;
    private FragmentManager fm;

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        gv = findViewById(R.id.systemGridView);
        List<String> lists = new ArrayList<>();
        lists.add("11111");
        lists.add("2222");
        lists.add("33333");
        lists.add("4444");
        GridViewAdapter adapter = new GridViewAdapter(this, lists);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.obtainNetData(MainActivity.this);

            }
        });

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fl,new BlankFragment());
        ft.commit();
    }

    @Override
    public TitleBar obtainTitleBarLayout() {
        return null;
    }

    @Override
    public void initTitleBarData(TitleBar titleBar) {
        titleBar.setTitleBarContent(0,"返回","首页");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public MyPresenter createPresenter() {
        return new MyPresenter();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Subscribe
    public void MainThread(Object o) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void success() {
        Log.e("error_code", "success: ");
    }
}
