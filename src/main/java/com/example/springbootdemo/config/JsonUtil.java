package com.example.springbootdemo.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config
 * @ClassName: JsonUtil
 * @Author: limingxing
 * @Description: TODO json对象操纵类
 * @Date: 2019/6/27 16:54
 * @Version: 1.0
 */
@Component
public class JsonUtil {

    /**
     * @Method javaToJson
     * @Author limingxing
     * @Version  1.0
     * @Description  TODO 将java对象转为json字符串
     * @param clazz
     * @Return java.lang.String
     * @Exception
     * @Date 2019/6/27 17:05
     */
    public String javaToJson(Object clazz){
        Object object = JSONObject.toJSON(clazz);
        return object.toString();
    }

    /**
     * @Method jsonToJava
     * @Author limingxing
     * @Version  1.0
     * @Description TODO 将json字符串转为java对象，
     * @param str  （json字符串）
     * @param clazz  （java对象的class文件）
     * @Return T
     * @Exception
     * @Date 2019/6/27 17:34
     */
    public <T> T jsonToJava(String str, Class<T> clazz){
        JSONObject jsonObject = JSONObject.parseObject(str);
        T t = JSONObject.toJavaObject(jsonObject, clazz);
        return t;
    }
}
