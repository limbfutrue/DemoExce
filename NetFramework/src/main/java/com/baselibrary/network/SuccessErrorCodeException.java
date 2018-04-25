package com.baselibrary.network;



/**
 * Created by Libaoming on 2017/5/5.
 */

public class SuccessErrorCodeException extends Exception {
    public String code;
    public String msg;

    public SuccessErrorCodeException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
