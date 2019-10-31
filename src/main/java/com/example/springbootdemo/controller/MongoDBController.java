package com.example.springbootdemo.controller;

import com.example.springbootdemo.Repository.MongoRepositorys;
import com.example.springbootdemo.entity.MongoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.controller
 * @ClassName: MongoDBController
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/10/30 21:04
 * @Version: 1.0
 */
@RestController
@RequestMapping("/MongoDB/")
public class MongoDBController {
    @Autowired
    private MongoRepositorys mongoRepositorys;

    @RequestMapping("save")
    public String save(){
        MongoInfo mongoInfo = new MongoInfo(System.currentTimeMillis(),"limx","123456");
        mongoRepositorys.save(mongoInfo);
        return "success";
    }

    @RequestMapping("getAllMongoInfo")
    public List<MongoInfo> getMongoInfoList(){
        List<MongoInfo> all = mongoRepositorys.findAll();
        return all;
    }

    @RequestMapping("delete")
    public String deelte(Long id){
        mongoRepositorys.deleteById(id);
        return "success";
    }

    @RequestMapping("update")
    public String update(Long id, String userName, String passWord){
        MongoInfo mongoInfo = new MongoInfo(id,userName,passWord);
        mongoRepositorys.save(mongoInfo);
        return "success";
    }
    /*======================================以下是自定义方法====================================================*/
    @RequestMapping("getByName")
    public MongoInfo deelte(String name){
        MongoInfo byUserName = mongoRepositorys.getByUsername(name);
        return byUserName;
    }

    @RequestMapping("getByUsernameLike")
    public List<MongoInfo> getMongoInfoByNameLike(String name){
        List<MongoInfo> byUsernameLike = mongoRepositorys.getByUsernameLike(name);
        return byUsernameLike;
    }

    @RequestMapping("getByUsernameAndPage")
    public List<MongoInfo> getByUsernameAndPage(String name,int page,int size){
        //mongodb中的分页页码是从0开始的
        PageRequest pageRequest = PageRequest.of(page,size);
        List<MongoInfo> content = mongoRepositorys.getByUsernameLike(name, pageRequest).getContent();
        return content;
    }

    @RequestMapping("getByIdNotNull")
    public List<MongoInfo> getByIdNotNull(int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<MongoInfo> byIdNotNull = mongoRepositorys.getByIdNotNull(pageable);
        List<MongoInfo> content = byIdNotNull.getContent();
        return content;
    }


}
