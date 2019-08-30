package com.example.springbootdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config
 * @ClassName: RedisUtil
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/6/27 16:15
 * @Version: 1.0
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * @Method get
     * @Author limingxing
     * @Version  1.0
     * @Description TODO redis操纵工具类
     * @param key
     * @Return java.lang.String
     * @Exception
     * @Date 2019/6/27 16:18
     */
    public String get(final String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * @Method set
     * @Author limingxing
     * @Version  1.0
     * @Description TODO 写入缓存
     * @param key
     * @param value
     * @Return boolean
     * @Exception
     * @Date 2019/6/27 16:20
     */
    public boolean set(final String key, String value){
        boolean flag = false;
        try{
            redisTemplate.opsForValue().set(key,value);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * @Method getAndSet
     * @Author limingxing
     * @Version  1.0
     * @Description TODO 更新缓存
     * @param key
     * @param value
     * @Return boolean
     * @Exception
     * @Date 2019/6/27 16:22
     */
    public boolean getAndSet(final String key, String value){
        boolean flag = false;
        try{
            redisTemplate.opsForValue().getAndSet(key,value);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @Method delete
     * @Author limingxing
     * @Version  1.0
     * @Description  TODO 删除缓存
     * @param key
     * @Return boolean
     * @Exception `
     * @Date 2019/6/27 16:24
     */
    public boolean delete(final String key){
        boolean flag = false;
        try{
            redisTemplate.delete(key);
            flag = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @Method set
     * @Author limingxing
     * @Version  1.0
     * @Description TODO 写入缓存并设置过期时间(当前方法设置的是分钟)
     * @param key
     * @param value
     * @Return boolean
     * @Exception
     * @Date 2019/6/27 16:20
     */
    public boolean setExplain(final String key, String value,long timeout){
        boolean flag = false;
        try{
            redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.MINUTES);
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return flag;
    }
}
