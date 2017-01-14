package net.blf2.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import net.blf2.util.Consts;
import net.blf2.util.Tools;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blf2 on 17-1-8.
 */
public class MongoOperator {
    private <T>BasicDBObject javaBeanToMongoObject(T document) throws IllegalAccessException {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.putAll(Tools.encodeClass(document));
        return basicDBObject;
    }
    public <T> boolean insertDocument(String databaseName,String collectionName,T document){
        MongoCollection mongoCollection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);
        try {
            mongoCollection.insertOne(javaBeanToMongoObject(document));
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public <T> boolean deleteDocument(String databaseName,String collectionName,T document){
        MongoCollection mongoCollection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);
        DeleteResult deleteResult;
        try {
            mongoCollection.findOneAndDelete(javaBeanToMongoObject(document));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
