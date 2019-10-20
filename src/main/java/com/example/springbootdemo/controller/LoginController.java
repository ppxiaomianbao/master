package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.MD5Util;
import com.example.springbootdemo.config.poi.ExportExcelUtil;
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

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

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
        if(user==null){
            return "login";
        }
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

    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist(String userName){
        User user = userMapper.findUserByName(userName);
        if(user==null){
            return true;
        }
        return false;
    }

    /**
     * @Method export
     * @Author limingxing
     * @Version  1.0
     * @Description todo 测试导出excel文件，可以有多个页，但是实体类的属性必须和表头顺序一致
     * @param response
     * @Return void
     * @Exception
     * @Date 2019/10/19 21:11
     */
    @RequestMapping("exports")
    public void export(HttpServletResponse response){
        User user = new User(1,"limx","123");
        User user1 = new User(2,"limx1","123");
        User user2 = new User(3,"limx2","123");
        List<User> listUser = new ArrayList<>();
        listUser.add(user);
        listUser.add(user1);
        listUser.add(user2);
        List<List<User>> dataLists = new ArrayList<>();
        dataLists.add(listUser);
        dataLists.add(listUser);
        dataLists.add(listUser);
        if (dataLists != null && dataLists.size() > 0) {
            try {
                List<String> sheetNames = new ArrayList<String>(
                        Arrays.asList("sheet页1", "sheet页2", "sheet页3"));
                                List<String> headers = new ArrayList<String>(
                                        Arrays.asList("编号","姓名", "密码"));
                String excelName = "你的Excel名称";
                ExportExcelUtil.exportExcel(excelName, sheetNames, null, headers, dataLists, response, null, true);
            } catch (Exception e) {
                LOGGER.info("导出Excel出错", e);
            }
        }
    }

    @RequestMapping("exports1")
    public void exportToMap(HttpServletResponse response){
        //todo 这里集合如果使用linkedhashmap的话，顺序就是添加的顺序，key没什么用，不重复就行
        Map<String,Object> map = new HashMap<>();
        map.put("表头列名1","水电费");
        map.put("表头列名3","地方");
        map.put("表头列名2","胜地");
        Map<String,Object> map1 = new HashMap<>();
        map1.put("表头列名1","胜地1");
        map1.put("表头列名2","地方1");
        map1.put("表头列名3","水电费1");
        List<Map<String,Object>> listMap = new ArrayList<>();
        listMap.add(map);
        listMap.add(map1);
        List<List<Map<String,Object>>> dataLists = new ArrayList<>();
        dataLists.add(listMap);
        dataLists.add(listMap);
        if (!dataLists.isEmpty()) {
            try {
                List<String[]> headers = new ArrayList<>();
                headers.add(new String[]{"表头列名1","表头列名2","表头列名3"});
                headers.add(new String[]{"表头1","表头2","表头3","表头4"});
                String fileName ="你的文件名，若包含Excel不支持的特殊字符，会自动处理成下划线";
                ExportExcelUtil.exportDynamicExcel(fileName, Arrays.asList("用户历史数据","测试"), null, headers, dataLists, response,
                        null, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

