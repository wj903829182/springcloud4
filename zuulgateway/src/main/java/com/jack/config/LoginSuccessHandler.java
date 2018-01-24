package com.jack.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jack on 2018/1/23.
 * http://blog.csdn.net/z69183787/article/details/21190733
 * http://blog.csdn.net/code__code/article/details/53885510
 *
 * 登入验证成功，权限分配成功后的回调
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获得授权后可得到用户信息
        //authentication.getPrincipal();
        System.out.println("onAuthenticationSuccess ...."+ JSONObject.toJSON(authentication.getPrincipal()));
       /* Set<SysRole> roles = userDetails.getSysRoles();*/
        //输出登录提示信息
        //System.out.println("管理员 " + userDetails.getName() + " 登录");

        //System.out.println("IP :"+getIpAddress(request));

    }
}
