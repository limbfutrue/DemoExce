package com.libaoming.demoexce.demoexce.test;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;

import com.baselibrary.base.basemvp.MVPBasePresenter;
import com.baselibrary.network.BaseResponse;
import com.baselibrary.network.JsonCallback;
import com.baselibrary.network.ObtainJsonParamsUtil;
import com.baselibrary.network.OkgoUtils;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Libaoming on 24/4/2018.
 * 15 hour 46 minute
 * project_name : DemoExce
 */

public class MyPresenter extends MVPBasePresenter<MyView> {
    public void obtainNetData(Context context){
        String urls ="http://192.168.1.120:8080/app/homepage/approval/selectAuditTitle";
        String biz = new ObtainJsonParamsUtil().obtainJsonParmas();
        OkgoUtils.postNet(context,urls,biz)
                .execute(new JsonCallback<BaseResponse>((Activity) context,true) {
                    @Override
                    public void onSuccess(BaseResponse baseResponse, Call call, Response response) {
                        getView().success();

                    }

                    @Override
                    public void onAfter(@Nullable BaseResponse baseResponse, @Nullable Exception e) {
                        super.onAfter(baseResponse, e);
                        getView().success();
                    }
                });
    }
}
