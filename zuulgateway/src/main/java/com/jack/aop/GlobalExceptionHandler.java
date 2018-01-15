package com.jack.aop;

import com.alibaba.fastjson.JSONObject;
import com.jack.util.RequestResultUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jack on 2017/8/24.
 * 统一异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        JSONObject jsonObject = RequestResultUtil.fail(e.getMessage());
        return jsonObject;
    }


}
