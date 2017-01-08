package net.blf2.dao;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.blf2.util.Consts;
import org.bson.Document;

import java.util.Map;

/**
 * Created by blf2 on 17-1-8.
 */
public class MongoOperator {
    public void insertDocument(String databaseName,String collectionName,Document document){
        MongoDatabase mongoDatabase = MongoDriver.getMongoDatabaseByName(Consts.MONGO_DATABASE_NAME);
        MongoCollection<Document>collection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);

    }
}
