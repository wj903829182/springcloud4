package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
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
        //result.put("sessionId",request.getSession().getId());
        Cookie [] cookies = request.getCookies();
        result.put("cookies",cookies);
        //System.out.println("模块的 seesion id is : "+request.getSession().getId());
        System.out.println("模块的 cookies is : "+JSONObject.toJSON(cookies));
        result.put("sessionInfo",request.getSession().getId());
        System.out.println("zuulTest session Id is : "+request.getSession().getId());
        return result;
    }
}
