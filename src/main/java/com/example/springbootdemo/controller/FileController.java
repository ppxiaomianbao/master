package com.example.springbootdemo.controller;

import com.example.springbootdemo.common.task.dto.R;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.controller
 * @ClassName: FileController
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/8/21 13:08
 * @Version: 1.0
 */
@Component
@RequestMapping("/limxFile/")
public class FileController {

    @RequestMapping("fileRead")
    public String fileRead(){
        return "fileRead";
    }

    @RequestMapping("save")
    @ResponseBody
    public R fileSave(@RequestParam(required = false,defaultValue = "")MultipartFile photo) throws IOException {
        R r = new R();
        if(photo.isEmpty()){
            r.setErrmsg("error --- photo is empty!!!");
            r.setErrcode("1001");
            return r;
        }
        String fileName = photo.getOriginalFilename();
        System.out.println(fileName);
        photo.transferTo(new File("d://img",fileName));
        r.setErrmsg("success");
        r.setErrcode("0");
        return r;
    }
}
