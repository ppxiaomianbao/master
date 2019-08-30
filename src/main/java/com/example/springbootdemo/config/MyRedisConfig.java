package com.example.springbootdemo.config;

import com.example.springbootdemo.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config
 * @ClassName: MyRedisConfig
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/6/27 12:02
 * @Version: 1.0
 */
@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Student> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Student> template = new RedisTemplate<Object, Student>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Student> ser = new Jackson2JsonRedisSerializer<Student>(Student.class);
        template.setDefaultSerializer(ser);
        return template;
    }
}
