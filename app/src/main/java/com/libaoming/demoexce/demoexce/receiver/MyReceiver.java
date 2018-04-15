package com.libaoming.demoexce.demoexce.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.libaoming.demoexce.demoexce.MainActivity;
import com.libaoming.demoexce.demoexce.R;
import com.libaoming.demoexce.demoexce.test.test_eventbus_demo.EventBusTestReceiverActivity;
import com.libaoming.demoexce.demoexce.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("error_code", "onReceive: 闹铃响了");
//        context.startActivity(new Intent(Settings.ACTION_SETTINGS)); //直接进入手机中设置界面
        context.startActivity(new Intent(context, EventBusTestReceiverActivity.class));
    }
}
