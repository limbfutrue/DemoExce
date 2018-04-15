package com.libaoming.demoexce.demoexce.utils.net;

import android.app.Activity;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Libaoming on 20/12/2017.
 * 11 hour 50 minute
 * project_name : DemoExce
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {
    private Activity activity;
    private boolean isShowProgress;
    public JsonCallBack(Activity activity,boolean isShowProgress){
        super();
        this.activity = activity;
        this.isShowProgress = isShowProgress;
        if (isShowProgress){
            showProgressDialog();
        }
    }

    /**
     * 显示加载框
     */
    protected void showProgressDialog(){
        //TODO 处理弹窗
    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);
    }

    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertSuccess(Response response) throws Exception {
        //以下代码是通过泛型解析实际参数,泛型必须传

        //得到类的泛型，包括了泛型参数
        Type genType = getClass().getGenericSuperclass();
        //从上述的类中取出真实的泛型参数，有些类可能有多个泛型，所以是数值
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        //我们的示例代码中，只有一个泛型，所以取出第一个，得到如下结果
        //得到泛型类型
        Type type = params[0];

        //这里我们既然都已经拿到了泛型的真实类型，即对应的 class ，那么当然可以开始解析数据了，我们采用 Gson 解析
        //以下代码是根据泛型解析数据，返回对象，返回的对象自动以参数的形式传递到 onSuccess 中，可以直接使用
        JsonReader jsonReader = new JsonReader(response.body().charStream());
        //有数据类型，表示有data
        T data = GosnHelp.fromJson(jsonReader, type);
        response.close();
        //判断
        int code = ((BaseResponse) data).errCode;
        final String msg = ((BaseResponse) data).msg;
        // TODO 处理成功码 失败码逻辑
        if (code == 0) {

        }else {
            new ErrorCodeHint().showToast(activity,code,msg);
        }
        return data;
    }
}
