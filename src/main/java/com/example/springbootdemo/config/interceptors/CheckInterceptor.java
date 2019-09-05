package com.example.springbootdemo.config.interceptors;

import com.example.springbootdemo.config.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config.interceptors
 * @ClassName: CheckInterceptor
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/9/1 17:48
 * @Version: 1.0
 */
public class CheckInterceptor implements HandlerInterceptor {
    private final static Logger LOGGER = LoggerFactory.getLogger(CheckInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //LOGGER.info("后置拦截器执行！！！ 移除的用户id为： {}",ThreadLocalUtil.getUser().getId());
        ThreadLocalUtil.remove();
    }
}
