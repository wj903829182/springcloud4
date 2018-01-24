package com.jack.controller;

import com.alibaba.fastjson.JSONObject;
import com.jack.util.RequestResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jack on 2018/1/19.
 */
@Controller
@RequestMapping("page")
public class LoginController {
    @GetMapping(value = "/test")
    //@ResponseBody
    public String loginTest(){
        System.out.println("url -----------test");
        //return "forward:http://localhost:8005/vuestudy2/login.html";redirect:
        return "redirect:http://localhost:8005/vuestudy2/login.html";
    }

   /* @GetMapping(value = "/login")
    //@ResponseBody
    public String loginPage(){
        System.out.println("url -----------login");
        return "forward:xx.html";
    }*/

    @GetMapping(value = "/fail")
    //@ResponseBody
    public String loginFail(){
        System.out.println("url -----------fail");
        return "forward:xx.html";
    }

    @GetMapping(value = "/wx")
    @ResponseBody
    public JSONObject loginIndex(){
        System.out.println("url -----------wx");
        JSONObject result = new JSONObject();
        return RequestResultUtil.success("登入成功");
        //return "redirect:http://localhost:8005/vuestudy2/html/wx.html";
    }


}
