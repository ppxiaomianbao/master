package com.example.springbootdemo.common.task.dto;

import java.util.List;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.common.task.dto
 * @ClassName: RespData
 * @Author: limingxing
 * @Description: todo 最终返回结果里的返回值得包装类
 * @Date: 2019/7/25 17:33
 * @Version: 1.0
 */
public class RespData<T> {
    private List<T> result;
    private Integer total;

    public RespData(List<T> list){
        this.result = list;
        this.total = list.size();
    }

    public List<T> getResult(){
        return result;
    }

    public void setResult(List<T> list){
        this.result = list;
        this.total = list.size();
    }

    public Integer getTotal(){
        return total;
    }
}
