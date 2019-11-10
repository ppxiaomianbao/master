package com.example.springbootdemo.config.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config.exception
 * @ClassName: MyExceptionHandle
 * @Author: limingxing
 * @Description: todo springmvc统一异常处理
 * @Date: 2019/11/6 21:46
 * @Version: 1.0
 */
@Configuration
public class MyExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        MyException myException = null;
        if(e instanceof MyException){
            myException = (MyException)e;
        }else {
            myException = new MyException("未知异常，请联系管理员！","500");
        }
        //向前端页面传值
        modelAndView.addObject("message",myException.getMessage());
        modelAndView.addObject("errorCode",myException.getErrorCode());
        //设置返回页面
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
