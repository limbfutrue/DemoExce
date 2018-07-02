package com.libaoming.demoexce.demoexce.test;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.baselibrary.base.basemvp.MVPBaseFrg;
import com.baselibrary.base.basemvp.MVPBasePresenter;
import com.baselibrary.base.basemvp.TitleBar;
import com.baselibrary.network.JsonCallback;
import com.baselibrary.network.ObtainJsonParamsUtil;
import com.baselibrary.utils.ToastUtils;
import com.bumptech.glide.Glide;
import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.view.TextureCameraPreview;
import com.lzy.okgo.OkGo;

import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFrg extends MVPBaseFrg {


    private TextureCameraPreview cView;
    private WebView mTvAnalysis;
    private Button bt;
    private EditText et;
    private ScrollView sv;

    public BlankFrg() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_blank;
    }

    @Override
    public TitleBar obtainTitleBarLayout() {
        return null;
    }

    @Override
    public void initTitleBarData(TitleBar titleBar) {
        super.initTitleBarData(titleBar);
    }

    @Override
    public void initView(View view) {

    }


    @Override
    public void obtainNetData() {

    }

    @Override
    public MVPBasePresenter createPresenter() {
        return null;
    }
}
