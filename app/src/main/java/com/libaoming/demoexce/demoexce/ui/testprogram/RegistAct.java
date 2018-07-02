package com.libaoming.demoexce.demoexce.ui.testprogram;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baselibrary.base.MyBaseAct;
import com.baselibrary.base.basemvp.TitleBar;
import com.baselibrary.network.JsonCallback;
import com.baselibrary.network.ObtainJsonParamsUtil;
import com.baselibrary.utils.ToastUtils;
import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.test.UserBean;
import com.lzy.okgo.OkGo;

import java.net.URLEncoder;

import okhttp3.Call;
import okhttp3.Response;

public class RegistAct extends MyBaseAct {

    private EditText mEtName,mEtPsd;
    private Button mBtRegist;

    @Override
    public TitleBar obtainTitleBarLayout() {
        return new TitleBar();
    }

    @Override
    public void initTitleBarData(TitleBar titleBar) {
        titleBar.setTitleBarContent(1,"","注册");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.act_regist;
    }

    @Override
    public void initView() {

        mEtName = findViewById(R.id.et_username);
        mEtPsd = findViewById(R.id.et_psd);
        mBtRegist = findViewById(R.id.bt_regist);
    }

    @Override
    public void initListener() {

        mBtRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_regist:
                regist();
                break;
        }

    }

    /**
     * 登录
     */
    private void regist() {
        String username = mEtName.getText().toString();
        String psd = mEtPsd.getText().toString();

        if (TextUtils.isEmpty(username)){
            ToastUtils.showShortToast("请输入用户名！");
            return;
        }

        if (TextUtils.isEmpty(username)){
            ToastUtils.showShortToast("请输入注册密码！");
            return;
        }

        String biz = new ObtainJsonParamsUtil()
                .obtainMap("userName",username)
                .obtainMap("password", psd)
                .obtainJsonParmas();
        OkGo.post("http://192.168.1.112:8080/regist2?")
                .tag(this)
                .params("biz", URLEncoder.encode(biz))
                .execute(new JsonCallback<UserBean>(this, true) {
                    @Override
                    public void onSuccess(UserBean userBean, Call call, Response response) {
                        ToastUtils.showShortToast(userBean.msg);
                        finish();
                    }
                });
    }
}
