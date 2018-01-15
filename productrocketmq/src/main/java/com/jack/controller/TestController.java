package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jack on 2018/1/15.
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping(value = "/zuulTest",method = RequestMethod.GET)
    public JSONObject zuulTest(HttpServletRequest request){
        JSONObject result = new JSONObject();
        result.put("success","通过网关调用成功");
        System.out.println("zuulTest seesion id is : "+request.getSession().getId());
        return result;
    }
}
