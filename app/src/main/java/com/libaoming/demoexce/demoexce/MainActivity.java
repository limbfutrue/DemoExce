package com.libaoming.demoexce.demoexce;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.libaoming.demoexce.demoexce.receiver.MyReceiver;
import com.libaoming.demoexce.demoexce.view.draggridview.DragGridView;
import com.libaoming.demoexce.demoexce.view.draggridview.GridViewAdapter;
import com.libaoming.demoexce.demoexce.view.dragview.GridViewItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private int i=0;
    private String videoPath;
    private DragGridView gv;
    private List<GridViewItem> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        gv = findViewById(R.id.systemGridView);
        List<String> lists = new ArrayList<>();
        lists.add("11111");
        lists.add("2222");
        lists.add("33333");
        lists.add("4444");
        GridViewAdapter adapter = new GridViewAdapter(this,lists);
        gv.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Subscribe
    public void MainThread(Object o){

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
