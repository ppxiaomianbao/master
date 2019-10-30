package com.example.springbootdemo.Repository;

import com.example.springbootdemo.entity.MongoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.Repository
 * @ClassName: MongoRepository
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/10/30 21:00
 * @Version: 1.0
 */
@Repository
public interface MongoRepositorys extends MongoRepository<MongoInfo,Long> {

}
