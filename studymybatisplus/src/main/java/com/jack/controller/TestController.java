package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import com.jack.config.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jack on 2018/1/4.
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestConfig testConfig;
    @RequestMapping("/config")
    public JSONObject testConfig(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("config",testConfig);
        return jsonObject;
    }
}
