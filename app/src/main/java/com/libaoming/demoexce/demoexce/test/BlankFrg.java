package com.libaoming.demoexce.demoexce.test;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.baselibrary.base.CommonAdapter;
import com.baselibrary.base.ViewHolder;
import com.baselibrary.base.basemvp.MVPBaseFrg;
import com.baselibrary.base.basemvp.MVPBasePresenter;
import com.baselibrary.base.basemvp.TitleBar;
import com.baselibrary.network.JsonCallback;
import com.baselibrary.network.ObtainJsonParamsUtil;
import com.baselibrary.network.OkgoUtils;
import com.baselibrary.utils.UIManager;
import com.bumptech.glide.Glide;
import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.view.TextureCameraPreview;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import zxing.activity.CaptureActivity;


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
        return null;
    }

    @Override
    public void initTitleBarData(TitleBar titleBar) {
        super.initTitleBarData(titleBar);
    }

    @Override
    public void initView(View view) {
        ImageView imageView = view.findViewById(R.id.iv_imag);
        String img_url = "http://192.168.7.121:8080/dict/attach/6/CT.jpg";
        Glide.with(this).load(img_url).into(imageView);
        final EditText username = view.findViewById(R.id.et_username);
        final EditText psd = view.findViewById(R.id.et_psd);


        view.findViewById(R.id.bt_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(username,psd);
            }
        });
    }

    private void login(EditText username, EditText psd) {
        String biz = new ObtainJsonParamsUtil()
                .obtainMap("userName",username)
                .obtainMap("psd",psd)
                .obtainJsonParmas();
        OkGo.post("http://192.168.1.112:8080")
                .tag(this)
                .params("params",biz)
                .execute(new JsonCallback<UserBean>(getActivity(), true) {
                    @Override
                    public void onSuccess(UserBean userBean, Call call, Response response) {

                    }
                });
    }

    @Override
    public void obtainNetData() {

    }

    @Override
    public MVPBasePresenter createPresenter() {
        return null;
    }

}
