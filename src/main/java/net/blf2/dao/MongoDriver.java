package net.blf2.dao;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.blf2.util.Consts;
import org.bson.Document;

/**
 * Created by blf2 on 17-1-8.
 */
public class MongoDriver {
    private static MongoClientOptions.Builder builder;
    private static MongoClientOptions options;
    private static MongoClient mongoClient;

    private MongoDriver(){
        super();
    }
    static {
        builder = new MongoClientOptions.Builder();
        //每个地址最大请求数
        options =  builder.connectionsPerHost(Consts.CONNECTIONS_PER_HOST)
        // 一个socket最大的等待请求数
        .threadsAllowedToBlockForConnectionMultiplier(Consts.THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER)
        // 在建立（打开）套接字连接时的超时时间（ms），默以为0（无穷）
        .connectTimeout(Consts.CONNECT_TIMEOUT)
        //被阻塞线程从连接池获取连接的最长等待时间（ms）
        .maxWaitTime(Consts.MAX_WAIT_TIME)
        //长链接
        .socketKeepAlive(true)
        //读取套接字超时时间;该值会被传递给Socket.setSoTimeout(int)。默以为0（无穷）
        .socketTimeout(Consts.SOCKET_TIME_OUT)
        .writeConcern(WriteConcern.NORMAL)
        //最近优先策略
        .readPreference(ReadPreference.primary())
        .build();
        mongoClient = new MongoClient(new ServerAddress(Consts.MONGODB_HOST,Consts.MONGODB_PORT),options);
    }

    public static MongoClient getMongoClient(){
        return mongoClient;
    }
    public static MongoDatabase getMongoDatabaseByName(String databaseName){
        return mongoClient.getDatabase(databaseName);
    }
    public static MongoCollection<Document> getMongoCollectionByName(String databaseName,String collectionName){
        return mongoClient.getDatabase(databaseName).getCollection(collectionName);
    }
}
