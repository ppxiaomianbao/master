package com.example.springbootdemo.Repository;

import com.example.springbootdemo.entity.MongoInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.Repository
 * @ClassName: MongoRepository
 * @Author: limingxing
 * @Description: 这种只能根据每个实体定制，不够通用
 * @Date: 2019/10/30 21:00
 * @Version: 1.0
 */
@Repository
public interface MongoRepositorys extends MongoRepository<MongoInfo,Long> {
    // 自定义方法规则，根据实体类中的属性查询，get + By + 属性名（首字母大写）
    public MongoInfo getByUsername(String name);

    // 根据属性名模糊查询，命名规则get + By + 属性名(首字母大写) + Like
    public List<MongoInfo> getByUsernameLike(String name);

    //此注解用于指定范围字段，主键字段不用指定，会自动返回
    @Query(value="{'username':?0}",fields = "{'username':1,'password':1}")
    public Page<MongoInfo> getByUsernameLike(String name, Pageable pageRequest);

    //这个可以实现查询所有，并分页，找一个不可能为null的字段，一般用id
    @Query(value = "{'_id':{'$ne':null}}",fields = "{'username':1}")
    public Page<MongoInfo> getByIdNotNull(Pageable pageable);

    /**
     * GreaterThan(大于)
     * 方法名举例：findByAgeGreaterThan(int age)
     * query中的value举例：{“age” : {"$gt" : age}}
     *
     * LessThan（小于）
     * 方法名举例：findByAgeLessThan(int age)
     * query中的value举例：{“age” : {"$lt" : age}}
     *
     * Between（在…之间）
     * 方法名举例：findByAgeBetween(int from, int to)
     * query中的value举例：{“age” : {“gt&quot;:from,&quot; gt&quot; : from, &quot;gt":from,"lt” : to}}
     *
     * Not（不包含）
     * 方法名举例：findByNameNot(String name)
     * query中的value举例：{“age” : {"$ne" : name}}
     *
     * Near（查询地理位置相近的）
     * 方法名举例：findByLocationNear(Point point)
     * query中的value举例：{“location” : {"$near" : [x,y]}}
     * ————————————————
     * 版权声明：本文为CSDN博主「michael.csdn」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/qq_38288606/article/details/78673528*/

}
