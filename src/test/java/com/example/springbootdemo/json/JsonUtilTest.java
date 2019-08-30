package com.example.springbootdemo.json;

import com.example.springbootdemo.config.JsonUtil;
import com.example.springbootdemo.config.RedisUtil;
import com.example.springbootdemo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.json
 * @ClassName: JsonUtilTest
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/6/27 16:57
 * @Version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonUtilTest {
    @Autowired
    private JsonUtil jsonUtil;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test_javaToJson(){
        //创建java对象
        Student student = new Student();
        student.setId(6);
        student.setName("test");
        student.setSex("男");
        //将java对象转为json字符串
        String s = jsonUtil.javaToJson(student);
        System.err.println(s);
        //将转为json字符串的java对象存放到redis里
        boolean student1 = redisUtil.set("student", s);
        System.err.println(student1);
        //将json字符串转为java对象
        Student student2 = jsonUtil.jsonToJava(s, Student.class);
        System.err.println(student2);

    }
}
