package com.example.springbootdemo.common.task.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config
 * @ClassName: R
 * @Author: limingxing
 * @Description: todo 最终返回包装类
 * @Date: 2019/7/25 17:27
 * @Version: 1.0
 */
public class R<T> {
    private String errcode;
    private String errmsg;
    private RespData<T> respData;

    public R(){}

    public R(String errcode, String errmsg, List<T> list){
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.respData = new RespData<>(list);
    }

    /*todo 此为创建此对象的方法*/

    public static <T> R<T> create(ErrorInfo errorInfo, List<T> list){
        R<T> r = new R<>();
        r.setErrcode(errorInfo.getCode());
        r.setErrmsg(errorInfo.getMsg());
        r.setRespData(new RespData<>(list!=null ? list : new ArrayList<>()));
        return r;
    }

    /*todo 以下三个方法是在外界调用的，用于创建此类，易于外界调用*/

    public static <T> R<T> success(List<T> list){
        R<T> r = create(ErrorInfo.SUCCESS, list);
        return r;
    }

    public static <T> R<T> success(T t){
        List<T> list = new ArrayList<>();
        list.add(t);
        return create(ErrorInfo.SUCCESS,list);
    }

    public static <T> R<T> failure(ErrorInfo errorInfo){
        return create(errorInfo,null);
    }

    /**
     * 以下是此包装类的get和set方法
     * */

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public RespData<T> getRespData() {
        return respData;
    }

    public void setRespData(RespData<T> respData) {
        this.respData = respData;
    }

}
