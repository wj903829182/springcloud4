package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import com.jack.util.RequestResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jack on 2018/1/13.
 */
@RestController
@RequestMapping("zuul")
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject login(String username,String password) {
        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            return RequestResultUtil.fail("用户名或者密码错误");
        }
        if ("jack".equals(username) && "123456".equals(password)) {
            return RequestResultUtil.success("login success");
        }
        return RequestResultUtil.fail("用户名或者密码错误");
    }
}
