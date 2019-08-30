package com.example.springbootdemo.common.task.perfile;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.common.task.perfile
 * @ClassName: Cloumns
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/7/25 12:38
 * @Version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "cloumn")
public class Cloumns {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
