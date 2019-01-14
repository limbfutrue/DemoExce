package com.baselibrary.network;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Libaoming on 2017/11/20.
 * 09 hour 37 minute
 * project_name : taidouhui
 */

public class ObtainParamsUtil {
    private HashMap<String, Object> params;

    public ObtainParamsUtil(){
        params = new HashMap<>();
    }
    /**
     * 初始化参数
     *
     * @param key
     * @param value
     * @return
     */
    public ObtainParamsUtil obtainMap(String key, Object value) {
        if (value !=null ) {
            params.put(key, value);
        }
        return this;
    }

    /**
     * 获取json类型参数
     *
     * @return
     */
    public String obtainJsonParmas() {
        if (params == null) {
            return null;
        }
        return new JSONObject(params).toString();
    }

    /**
     * 获取map类型参数
     *
     * @return
     */
    public Map<String, Object> obtainMapParmas() {
        return params;
    }
}
