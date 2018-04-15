package com.libaoming.demoexce.demoexce.test.test_eventbus_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.libaoming.demoexce.demoexce.R;

import org.greenrobot.eventbus.EventBus;

/**
 * eventBus发送消息
 *
 */
public class EventBusTestSendActivity extends AppCompatActivity {

    private TextView sendMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
        sendMsg = (TextView)findViewById(R.id.tv_event_bus_send_msg);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBusReceiverBean bean = new EventBusReceiverBean();
                bean.eventData2 = "eventData2";
                bean.eventData1 = "eventData1";
                EventBus.getDefault().post(bean);

            }
        });
    }
}
