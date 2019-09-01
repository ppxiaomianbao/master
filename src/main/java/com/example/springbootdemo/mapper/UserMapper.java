package com.example.springbootdemo.mapper;

import com.example.springbootdemo.entity.User;

public interface UserMapper {

    /**
     * @Method 
     * @Author limingxing
     * @Version  1.0
     * @Description
     * @param null
     * @Return 
     * @Exception 保存注册的用户数据
     * @Date 2019/8/31 22:12
     */
    public int save(User user);
    /**
     * @Method 
     * @Author limingxing
     * @Version  1.0
     * @Description
     * @param null
     * @Return 
     * @Exception 根据用户名查询密码
     * @Date 2019/8/31 22:10
     */
    public User findUserByName(String userName);
}
