package com.libaoming.demoexce.demoexce.test.test_mvpmodel_demo.presenter;

import android.app.Activity;

import com.libaoming.demoexce.demoexce.test.test_mvpmodel_demo.model.IMvpImpl;
import com.libaoming.demoexce.demoexce.test.test_mvpmodel_demo.model.IMvpModel;
import com.libaoming.demoexce.demoexce.test.test_mvpmodel_demo.view.IMvpTestView;
import com.libaoming.demoexce.demoexce.utils.net.JsonCallBack;
import com.lzy.okgo.OkGo;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Libaoming on 21/12/2017.
 * 11 hour 33 minute
 * project_name : DemoExce
 *
 * 处理逻辑代码
 *Presenter翻译的意思是主持人，也就是主持场合，控制节奏的意思。在这时Presenter就负责具体的业务逻辑，
 * 请求数据，把数据送到Model，或者监听Model的数据变化，接受View层的动作，负责通过通知View层的视图变化。
 */

public class MvpPresenter {
    //层定义Modle.interface,这个是用来定义数据层发生变化时的通知接口，
    // 因为Model不能直接与View交互，所以它与Presenter交互，
    // 然后再通过Presenter间接达到与View的交互。
    IMvpTestView iMvpTestView;
    //View层定义View.interface，用来定义View的行为。
    // 一般由Activity或者是Fragment来实现这个接口，
    // 它定义了View视图的各种变化，如设置Textview,加载对话框，更新进度条等
    IMvpModel iMvpModel;

    public MvpPresenter(IMvpTestView iMvpTestView) {
        this.iMvpTestView = iMvpTestView;
        iMvpModel = new IMvpImpl();
    }

    /**
     * 获取网络数据
     */
    public void requestData(Activity context){
        //处理 通知view层
        iMvpTestView.dealwithView1();
        OkGo.post("")
                .execute(new JsonCallBack<Object>(context,true) {
                    @Override
                    public void onSuccess(Object o, Call call, Response response) {
                        //model处理数据
                        iMvpModel.setInfo("");
                    }
                });

    }
}
