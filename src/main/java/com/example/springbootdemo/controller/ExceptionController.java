package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.exception.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.controller
 * @ClassName: ExceptionController
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/11/6 21:55
 * @Version: 1.0
 */
@RequestMapping("/exception/")
@RestController
public class ExceptionController {

    @RequestMapping("test")
    public String testExceptionHandler() throws MyException {
        try {
            int s = 5/0;
        }catch (Exception e){
            throw new MyException("零除异常！","500");
        }
        return "hello world!!!";
    }

}
