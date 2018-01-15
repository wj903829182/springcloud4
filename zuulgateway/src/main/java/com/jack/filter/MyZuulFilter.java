package com.jack.filter;

import com.netflix.zuul.ZuulFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
        return 0;
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

        return null;
    }
}
