package com.example.springbootdemo.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @ProjectName: springbootdemo
 * @Package: com.example.springbootdemo.config
 * @ClassName: MongoDBUtil
 * @Author: limingxing
 * @Description: todo mongodb工具类
 * @Date: 2019/10/31 12:09
 * @Version: 1.0
 */
public class MongoDBUtil {
    /**
     * MongoClient是线程安全的
     * Mongo是非线程安全的
     * 目前mongodb API中已经建议用MongoClient替代Mongo
     */
    private MongoClient mongoClient = null;

    private static volatile MongoDBUtil mongoDBUtil = null;

    private MongoDBUtil(){}

    public static MongoDBUtil getInstance(String host,int port){
        mongoDBUtil = new MongoDBUtil(host,port);
        return mongoDBUtil;
    }

    private MongoDBUtil(String host, int port){
        if(mongoClient == null){
            synchronized (MongoDBUtil.class) {
                if (mongoClient == null) {
                    initConn(host, port);
                }
            }
        }
    }

    /**
     * 初始化MongoClient
     * @param host
     * @param port
     */
    private void initConn(String host, int port) {
        mongoClient = new MongoClient(host,port);
    }

    //获取mongo连接对象,传入数据库和collection名称
    public MongoCollection<Document> getDBCollection(String dbName, String collectionName){
        return mongoClient.getDatabase(dbName).getCollection(collectionName);
    }

    //获取mongo数据库，再根据数据库连接数据库下的collection
    public MongoDatabase getMongoDatabase(String dbName){
        MongoDatabase database = mongoClient.getDatabase(dbName);
        return database;
    }

}
