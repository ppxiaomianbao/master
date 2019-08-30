package com.example.springbootdemo.redis;

import com.example.springbootdemo.config.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.generics.tree.VoidDescriptor;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.redis
 * @ClassName: RedisUtilTest
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/6/27 16:25
 * @Version: 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test_wirte(){
        boolean set = redisUtil.set("test", "李明星");
        System.out.println(set);
    }

    @Test
    public void test_read(){
        String test = redisUtil.get("test");
        System.out.println(test);
    }

    @Test
    public void test_wirteAndSet(){
        boolean andSet = redisUtil.getAndSet("test", "姓名李");
        System.out.println(andSet);
    }

    @Test
    public void test_delete(){
        boolean test = redisUtil.delete("test");
        System.out.println(test);
    }

    @Test
    public void test_setAndExplain(){
        boolean b = redisUtil.setExplain("test", "李明星",10);
        System.out.println(b);
    }

}
