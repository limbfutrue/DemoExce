package com.libaoming.demoexce.demoexce.utils.net;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5375804597574885028L;
    public int errCode = -1;// 	返回码:0请求成功;500异常;1其他;2 token过期
    public String msg;

}
