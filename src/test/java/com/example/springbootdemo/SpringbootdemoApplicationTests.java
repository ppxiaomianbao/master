package com.example.springbootdemo;

import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.mapper.StudentMapper;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {
    @Autowired
    private StudentMapper studentMapper;
    //用来处理字符串的
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //用来处理对象的
    @Autowired
    private RedisTemplate<Object,Student> MyRedisConfig;

    @Test
    public void testStringRedis() {
        stringRedisTemplate.opsForValue().append("message","hello");
        String message = stringRedisTemplate.opsForValue().get("message");
        System.out.println("message:" + message);

        stringRedisTemplate.opsForList().leftPush("myList","张三");
        stringRedisTemplate.opsForList().leftPush("myList","李四");
        stringRedisTemplate.opsForList().leftPush("myList","王五");
        List<String> myList = stringRedisTemplate.opsForList().range("myList", 0, 3);
        System.out.println(myList.toString());
    }

    @Test
    public void testObject(){
        /*Student student = studentMapper.getStudent(1 + "");
        JSONObject jsonObject = JSONObject.fromObject(student);
        redisTemplate.opsForValue().set("object1",jsonObject.toString());
        redisTemplate.opsForValue().set("object2",JSONObject.fromObject(student).toString());
        Object object1 = redisTemplate.opsForValue().get("object1");
        System.out.println(object1);*/
        Student student = studentMapper.getStudent(1 + "");
        MyRedisConfig.opsForValue().set("objectTest",student);
    }

    @Test
    public void ObjectToJson(){
        Student student = studentMapper.getStudent(1 + "");
        System.out.println(student);
        JSONObject jsonObject = JSONObject.fromObject(student);
        System.out.println(jsonObject);
    }



}
