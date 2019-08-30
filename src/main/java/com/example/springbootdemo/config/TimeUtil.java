package com.example.springbootdemo.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.logging.SimpleFormatter;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config
 * @ClassName: TimeUtil
 * @Author: limingxing
 * @Description: TODO 时间处理类
 * @Date: 2019/7/25 11:56
 * @Version: 1.0
 */
public class TimeUtil {

    /**
     * @Method dateFormat
     * @Author limingxing
     * @Version  1.0
     * @Description  传入时间格式，返回指定格式的当前时间
     * @param pattern
     * @Return java.lang.String
     * @Exception
     * @Date 2019/7/25 11:59
     */
    public static String dateFormat(String pattern){
        SimpleDateFormat sim = new SimpleDateFormat(pattern);
        return sim.format(new Date());
    }
}
