package com.jack.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by jack on 2018/1/13.
 */
public class RequestResultUtil {
    public static JSONObject success(){
        JSONObject result = getSuccessResultBaseMsg();
        return result;
    }
    public static JSONObject success(Object data){
        JSONObject result = getSuccessResultBaseMsg();
        result.put("data",data);
        return result;
    }
    public static JSONObject fail(){
        return getFailResultBaseMsg();
    }
    public static JSONObject fail(Object data){
        JSONObject result = getFailResultBaseMsg();
        result.put("data",data);
        return result;
    }

    public static JSONObject getSuccessResultBaseMsg(){
        JSONObject result = new JSONObject();
        result.put("code",0);
        result.put("msg", "success");
        return result;
    }
    public static JSONObject getFailResultBaseMsg(){
        JSONObject result = new JSONObject();
        result.put("code",-1);
        result.put("msg", "fail");
        return result;
    }

}
