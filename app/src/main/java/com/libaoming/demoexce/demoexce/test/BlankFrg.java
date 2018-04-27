package com.libaoming.demoexce.demoexce.test;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.baselibrary.base.CommonAdapter;
import com.baselibrary.base.ViewHolder;
import com.baselibrary.base.basemvp.MVPBaseFrg;
import com.baselibrary.base.basemvp.MVPBasePresenter;
import com.baselibrary.base.basemvp.TitleBar;
import com.bumptech.glide.Glide;
import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.view.TextureCameraPreview;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFrg extends MVPBaseFrg {


    private TextureCameraPreview cView;

    public BlankFrg() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    public TitleBar obtainTitleBarLayout() {
        return new TitleBar();
    }

    @Override
    public void initTitleBarData(TitleBar titleBar) {
        super.initTitleBarData(titleBar);
        titleBar.setTitleBarContent(0,"","题目");
    }

    @Override
    public void initView(View view) {
        ImageView imageView = view.findViewById(R.id.iv_imag);
        String img_url = "";
        Glide.with(this).load(img_url).into(imageView);

    }

    @Override
    public void obtainNetData() {

    }

    @Override
    public MVPBasePresenter createPresenter() {
        return null;
    }

}
