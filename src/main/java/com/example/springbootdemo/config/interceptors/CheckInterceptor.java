package com.example.springbootdemo.config.interceptors;

import com.example.springbootdemo.config.ThreadLocalUtil;
import com.example.springbootdemo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

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
        HttpSession session = request.getSession();
        StringBuffer path = request.getRequestURL();
        String servletPath = request.getServletPath();
        User user = (User) session.getAttribute("user");
        ThreadLocalUtil.setUser(user);
        if(user == null){
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("您已经退出登录，3秒后跳转至登录页面！");
            response.setHeader("refresh","3;/demo/login/login");
            //response.sendRedirect("/demo/login/login");
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //LOGGER.info("后置拦截器执行！！！ 移除的用户id为： {}",ThreadLocalUtil.getUser().getId());
        ThreadLocalUtil.remove();
    }
}
