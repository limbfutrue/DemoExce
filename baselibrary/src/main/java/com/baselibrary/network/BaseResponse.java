package com.baselibrary.network;

import java.io.Serializable;

/**
 * @author Libaoming
 */
public class BaseResponse implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 5375804597574885028L;
    public String result = "-1";// 	返回码:0请求成功;1 error
    public String msg;//结果描述
    public String rspCode; //结果编码 自定义
    public String token;

}
