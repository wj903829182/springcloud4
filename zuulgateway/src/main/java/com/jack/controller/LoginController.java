package com.jack.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jack.DTO.SessionInfo;
import com.jack.DTO.User;
import com.jack.util.RequestResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jack on 2018/1/13.
 */
@RestController
@RequestMapping("/logincontroller")
public class LoginController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (username == null || "".equals(username) || password == null || "".equals(password)) {
            return RequestResultUtil.fail("用户名或者密码错误");
        }

        System.out.println("username = "+username+",password = "+password);
        System.out.println("sessionId is : "+request.getSession().getId());
        HttpSession httpSession = request.getSession();
        System.out.println("cokie is : "+ JSONObject.toJSON(request.getCookies()));
        SessionInfo sessionInfo = new SessionInfo();
        Cookie [] cookies = request.getCookies();
        if (cookies != null) {
            System.out.println("cookies 已经存在...........................");
            RequestResultUtil.success("cookies已经存在");
        }
        if ("jack".equals(username) && "123456".equals(password)) {
            sessionInfo.setSessionId(httpSession.getId());
            sessionInfo.setUsername(username);
            //HttpSession session = request.getSession(true);
            //session.setAttribute("key", "value");
            //String sessionId = session.getId();
            Cookie cookie = new Cookie("sessionId",httpSession.getId());//sessionId默认是存放在一个name为mySessionId里面的
            cookie.setPath("/");
            cookie.setMaxAge(5 * 60);// 以秒为单位，所以为30分钟
            response.addCookie(cookie);
            httpSession.setAttribute("sessionInfo",sessionInfo);

            return RequestResultUtil.success(sessionInfo);
        }

        return RequestResultUtil.fail("用户名或者密码错误");
    }
}
