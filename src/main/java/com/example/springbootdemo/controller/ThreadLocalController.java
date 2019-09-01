package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.ThreadLocalUtil;
import com.example.springbootdemo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.controller
 * @ClassName: ThreadLocalController
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/9/1 17:52
 * @Version: 1.0
 */
@Component
@RequestMapping("/threadLocal/")
@RestController
public class ThreadLocalController {

    @RequestMapping("test")
    public User test(){
        return ThreadLocalUtil.getUser();
    }
}
