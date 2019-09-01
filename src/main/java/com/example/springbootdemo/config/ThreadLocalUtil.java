package com.example.springbootdemo.config;

import com.example.springbootdemo.entity.User;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config
 * @ClassName: ThreadLocalUtil
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/9/1 17:44
 * @Version: 1.0
 */
public class ThreadLocalUtil {
    private final static ThreadLocal<User> THREAD_LOCAL = new ThreadLocal<>();

    public static User getUser(){
        return THREAD_LOCAL.get();
    }

    public static void setUser(User user){
        THREAD_LOCAL.set(user);
    }

    public static void remove(){
        THREAD_LOCAL.remove();
    }
}
