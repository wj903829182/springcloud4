package com.jack.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jack on 2018/1/15.
 */
@Component
public class MyZuulFilter extends ZuulFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyZuulFilter.class);

    @Override
    public String filterType() {
        // 前置过滤器
        return "pre";
    }

    @Override
    public int filterOrder() {
        //// 优先级为0，数字越大，优先级越低
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，此处为true，说明需要过滤
        return true;
    }

    @Override
    public Object run() {
        LOGGER.error("............执行zuul前置过滤器.........");
        /*RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();

        logger.info("send " + request.getMethod() + " request to " + request.getRequestURL().toString());

        Object token = request.getParameter("accToken");

        if (token == null) {

            logger.warn("token为空，不允许访问");

            ctx.setSendZuulResponse(false);

            ctx.setResponseStatusCode(401);

            return null;

        }

        logger.info("允许访问");

        return null;*/

        /*RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println(String.format("%s AccessUserNameFilter request to %s", request.getMethod(), request.getRequestURL().toString()));

        String username = request.getParameter("username");// 获取请求的参数
        if(null != username && username.equals("chhliu")) {// 如果请求的参数不为空，且值为chhliu时，则通过
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            return null;
        }else{
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"username is not correct!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }*/

        //获取zuul网关请求目前的上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //通过zuul请求的上下文对象获取http请求的request对象
        HttpServletRequest request = requestContext.getRequest();
        //获取cookie信息
        Cookie[] cookies = request.getCookies();
        System.out.println("zuul过滤器中的cookies信息为："+ JSONObject.toJSON(cookies));

        /*HttpServletResponse response = requestContext.getResponse();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");*/

        return null;
    }
}
