package com.libaoming.demoexce.demoexce.utils.net;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Libaoming on 8/1/2018.
 * 16 hour 14 minute
 * project_name : DemoExce
 */

public class ErrorCodeHint {

    public void showToast(Context context, int code, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
