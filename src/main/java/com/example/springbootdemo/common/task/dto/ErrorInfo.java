package com.example.springbootdemo.common.task.dto;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.common.task.dto
 * @ClassName: ErrorInfo
 * @Author: limingxing
 * @Description: todo 用枚举，方便
 * @Date: 2019/7/25 17:45
 * @Version: 1.0
 */
public enum ErrorInfo {
    SUCCESS(
            "0","成功"
    ),KEY_PARAM_NULL(
            "1001","为传入必穿参数或必传参数为空"
    ),KEY_PARAM_ERROR(
            "10011","参数错误"
    ),RESULT_NULL(
            "1002","数据结果为空"
    ),ERROR(
            "2000","接口异常"
    );
    private String code;
    private String msg;

    private ErrorInfo(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }}
