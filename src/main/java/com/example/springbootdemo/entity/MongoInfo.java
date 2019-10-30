package com.example.springbootdemo.entity;

import org.springframework.data.annotation.Id;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.entity
 * @ClassName: MongoInfo
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/10/30 20:57
 * @Version: 1.0
 */
public class MongoInfo {
    @Id
    private Long id;

    private String username;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MongoInfo(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
