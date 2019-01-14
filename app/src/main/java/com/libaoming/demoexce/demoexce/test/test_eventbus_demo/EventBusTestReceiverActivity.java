package com.libaoming.demoexce.demoexce.test.test_eventbus_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.test.test_mvpmodel_demo.view.MvpTestActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 接收发送过来的数据
 * 注册 解注  eventBus
 */
public class EventBusTestReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test_receiver);
        EventBus.getDefault().register(this);
        startActivity(new Intent(this, EventBusTestSendActivity.class));
    }

    /**
     * 再主线程中处理消息
     * @param receiverBean
     */
    @Subscribe
    public void onEventMainThread(EventBusReceiverBean receiverBean){
        startActivity(new Intent(this, MvpTestActivity.class));
        Log.e("error_code", "onEventMainThread: eventData1:"+Thread.currentThread().getName()+receiverBean.eventData1+"\neventData2:"+receiverBean.eventData2);
    }

    /**
     * 再后台线程中处理消息
     *
     * @param receiverBean
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(EventBusReceiverBean receiverBean) {
        Log.e("error_code", "onEventMainThread: eventData1:" + Thread.currentThread().getName() + receiverBean.eventData1 + "\neventData2:" + receiverBean.eventData2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
