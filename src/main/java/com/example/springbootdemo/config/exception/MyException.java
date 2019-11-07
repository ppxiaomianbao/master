package com.example.springbootdemo.config.exception;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config.exception
 * @ClassName: MyException
 * @Author: limingxing
 * @Description: todo 自定义异常类
 * @Date: 2019/11/6 21:16
 * @Version: 1.0
 */
public class MyException extends Exception {
    private String message;
    private String errorCode;

    public String getMessage() {
        return message;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public MyException(String message,String errorCode){
        this.errorCode = errorCode;
        this.message = message;
    }

}
