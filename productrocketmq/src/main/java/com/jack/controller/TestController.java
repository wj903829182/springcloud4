package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jack on 2018/1/15.
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping(value = "/zuulTest",method = RequestMethod.GET)
    public JSONObject zuulTest(){
        JSONObject result = new JSONObject();
        result.put("success","通过网关调用成功");
        return result;
    }
}
