package com.example.springbootdemo.mongo;

import com.alibaba.fastjson.JSON;
import com.example.springbootdemo.config.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.mongo
 * @ClassName: MongoDBUtilTest
 * @Author: limingxing
 * @Description: ${description}
 * @Date: 2019/10/31 12:54
 * @Version: 1.0
 */
public class MongoDBUtilTest {
    private static final MongoDBUtil mongoDBUtil = MongoDBUtil.getInstance("127.0.0.1",27017);
    MongoCollection<Document> dbCollection = mongoDBUtil.getDBCollection("admin", "test");
    MongoDatabase admin = mongoDBUtil.getMongoDatabase("admin");

    @Test  //添加一条数据
    public void test_insertOne(){
        Document document = new Document("name","Mongo测试");
        MongoCollection<Document> test = admin.getCollection("mongoInfo");
        /**todo 删除一个collection，如果添加的时候指定了一个不存在的collection，会自动的创建一个
         * MongoCollection<Document> mongoinfo = admin.getCollection("mongoinfo");
        mongoinfo.drop();*/
        test.insertOne(document);
    }

    @Test  //添加多条数据,可以添加各种数据结构
    public void test_insertList(){
        List<Document> list = new ArrayList<>();
        list.add(new Document("name","测试").append("age",25).append("sex","男"));
        list.add(new Document("name","测试1").append("age",26).append("sex","女"));
        list.add(new Document("name","测试2").append("age",27).append("sex","男"));
        Map<String,Object> map = new HashMap<>();
        map.put("TEST1",1);
        map.put("TEST2",2);
        map.put("TEST3",3);
        list.add(new Document("name","666666").append("test",map));
        dbCollection.insertMany(list);
    }

    @Test  //添加json字符串
    public void insertByJson(){
        String json = "{ \"name\" : \"王五\" , \"age\" : 66 , \"job\" : \"快递员\" , \"remark\" : { \"address\" : \"广东省深圳市\" , \"street\" : \"深南大道888号\"}}";
        Map map = JSON.parseObject(json, Map.class);
        dbCollection.insertOne(new Document(map));
    }

    @Test  //查询所有数据,并排序，1为升序，-1为降序
    public void test_find(){
        FindIterable<Document> documents = dbCollection.find().sort(new BasicDBObject("name",-1));
        for(Document document:documents){
            Set<String> strings = document.keySet();
            for(String str:strings){
                Object o = document.get(str);
                System.out.println("key: " + str + " value: " + o);
            }
            System.out.println();
        }
    }

    @Test  //查询name为王五的数据
    public void test_findByName(){
        FindIterable<Document> documents = dbCollection.find(new Document("name", "王五"));
        for(Document document : documents){
            Set<String> strings = document.keySet();
            for(String str : strings){
                Object o = document.get(str);
                System.out.println("key: " + str + " value: " + o);
            }
        }
    }

    @Test  //查询name为王五的数据,并分页
    public void test_findByNameByPage(){
        FindIterable<Document> documents = dbCollection.find(new Document("name", "王五")).skip(1).limit(1);
        for(Document document : documents){
            Set<String> strings = document.keySet();
            for(String str : strings){
                Object o = document.get(str);
                System.out.println("key: " + str + " value: " + o);
            }
        }
    }

    @Test  //模糊查询所有数据 todo append("$options", "i")是忽略大小写匹配
    public void test_findByLike(){
        FindIterable<Document> documents = dbCollection.find(new Document().append("name", new Document("$regex", "M").append("$options", "i")));
        for(Document document:documents){
            Set<String> strings = document.keySet();
            for(String str:strings){
                Object o = document.get(str);
                System.out.println("key: " + str + " value: " + o);
            }
            System.out.println();
        }
    }
}
