package com.baselibrary.network;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;
import com.baselibrary.utils.LogUtil;
import com.baselibrary.utils.StringUtil;
import com.visitor.lc.baselibrary.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;


/**
 * Created by Libaoming on 2017/5/3.
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    private Activity context;
    private boolean isShowDialog;
    private CustomerDialog dialog;


    private void initDialog(Activity activity) {
        dialog = new CustomerDialog(activity, R.style.MyDialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("加载中...");
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
    }

    public JsonCallback(Activity activity, boolean isShowDialog) {
        super();
        if (isShowDialog)
            initDialog(activity);
        this.context = activity;
        this.isShowDialog = isShowDialog;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (isShowDialog && context != null) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (context.isDestroyed()) {
                        return;
                    }
                }
                if (dialog != null && !dialog.isShowing()) {
                    dialog.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        //网络请求结束后关闭对话框
        if (isShowDialog)
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
        if (e instanceof SuccessErrorCodeException) {
            LogUtil.log("服务器请求code:" + ((SuccessErrorCodeException) e).code + "---" + ((SuccessErrorCodeException) e).msg);
        } else if (e instanceof JsonIOException || e instanceof JsonSyntaxException) {
            LogUtil.log("解析失败");
        } else {

            if (context != null) {
                Toast.makeText(context, "请求服务器失败,请检查您的网络", Toast.LENGTH_SHORT).show();
                Log.e("callback", "onError: " + e.getMessage());
            }
        }
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertSuccess(Response response) throws Exception {

        //以下代码是通过泛型解析实际参数,泛型必须传

        //com.lzy.demo.callback.DialogCallback<com.lzy.demo.model.Login> 得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        //com.lzy.demo.model.Login
        Type type = params[0];

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        ResponseBody body = response.body();
        JsonReader jsonReader = new JsonReader(body.charStream());
        //有数据类型，表示有data
        Log.e("callback", "ClassType: "+type.toString());
        T data = Convert.fromJson(jsonReader, type);
        Log.e("callback", "返回数据: " + JSON.toJSONString(data));
        response.close();
        //判断
        String code = ((BaseResponse) data).result;
        String rspCode = ((BaseResponse) data).rspCode;

        final String msg = ((BaseResponse) data).msg;
        if (code.equals(2)) {
            return data;
        } else {
            if (rspCode.equals("9000")) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "token过期，请重新登录", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                if (context != null)
                    if (StringUtil.isNotNull(msg)) {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "服务器响应失败,错误信息为空", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
            }
            throw new SuccessErrorCodeException(code, ((BaseResponse) data).msg);
        }
    }
}

