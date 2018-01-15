package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import com.jack.util.RequestResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by jack on 2018/1/13.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping(value = "/getSessionId",method = RequestMethod.GET)
    public JSONObject getSessionId(HttpServletRequest request){
        Cookie [] cookies = request.getCookies();
        System.out.println("the getSessionId method cookies is : "+JSONObject.toJSON(cookies));
        //System.out.println("the getSessionId method session id is : "+request.getSession().getId());
        return RequestResultUtil.success(cookies);
    }
}
