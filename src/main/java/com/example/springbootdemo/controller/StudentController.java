package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.task.dto.ErrorInfo;
import com.example.springbootdemo.common.task.dto.R;
import com.example.springbootdemo.common.task.perfile.Cloumns;
import com.example.springbootdemo.config.TimeUtil;
import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/limx/")
public class StudentController {
    private Logger logger =  LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private Cloumns cloumns;

    /**
     * @Method getStudent
     * @Author limingxing
     * @Version  1.0
     * @Description
     * @param id
     * @Return com.example.springbootdemo.entity.Student
     * @Exception
     * @Date 2019/6/25 10:05
     */
    @RequestMapping("test")
    public R<?> getStudent(String id){
        logger.warn("method : getStudent " + TimeUtil.dateFormat("yyyy-MM-dd HH:mm:ss") + cloumns.getName());
        Student student = studentMapper.getStudent(id);
        logger.debug("method : getStudent " + TimeUtil.dateFormat("yyyy-MM-dd HH:mm:ss") + student);
        Map<Integer,Integer> map = new HashMap<>(1000000);
        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<1000000;i++){
                    map.put(i,i);
                }
            }
        }.start();
        return R.success(student);
    }

    /*测试异常通知*/
    @RequestMapping("doErro")
    public Object testErro(){
        int a = 4;
        int b = 0;
        a = a / b;
        return a;
    }

}
