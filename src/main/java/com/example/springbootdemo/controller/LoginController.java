package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.MD5Util;
import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.entity.User;
import com.example.springbootdemo.mapper.UserMapper;
import org.apache.catalina.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.controller
 * @ClassName: LoginController
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/8/31 18:06
 * @Version: 1.0
 */
@Controller
@RequestMapping("/login/")
public class LoginController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private UserMapper userMapper;
    @Autowired
    public LoginController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("index")
    public String index(String userName, String passWord, HttpServletRequest request){
        HttpSession session = request.getSession();
        String passMD5 = MD5Util.getMD5(passWord.getBytes());
        User user = userMapper.findUserByName(userName);
        session.setAttribute("user",user);
        if(user.getPassWord().equals(passMD5)){
            return "index";
        }else{
            return "login";
        }
    }

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("saveRegister")
    @ResponseBody
    public String saveRegister(User user,String passTo){
        User userFlag = userMapper.findUserByName(user.getUserName());
        if(!user.getPassWord().equals(passTo)||userFlag!=null){
            //return "register";
            return "erreo";
        }
        String passMD5 = MD5Util.getMD5(passTo.getBytes());
        user.setPassWord(passMD5);
        userMapper.save(user);
        LOGGER.info("当前插入数据的id：{}",user.getId());
        //return "login";
        return "success";
    }
}
