package com.libaoming.demoexce.demoexce.ui.ui.rxjavaTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.libaoming.demoexce.demoexce.R;


public class SimpleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }

    private String sayMyName() {
        return "Hello,I am your friend, Spike";
    }


}
