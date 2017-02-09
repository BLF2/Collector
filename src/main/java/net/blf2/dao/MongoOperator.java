package net.blf2.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import net.blf2.util.Consts;
import net.blf2.util.Tools;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by blf2 on 17-1-8.
 */
public class MongoOperator {
    public static boolean insertDocument(String databaseName,String collectionName,Map<String,Object>dataMap){
        MongoCollection mongoCollection = MongoDriver.getMongoCollectionByName(databaseName, collectionName);
        Document document = Tools.mapToDocument(dataMap);
        try {
            mongoCollection.insertOne(document);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean deleteDocument(String databaseName,String collectionName,Map<String,Object>deleteDataMap){
        MongoCollection mongoCollection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);
        Document  query = new Document();;
        query = MongoOperator.fillQueryConditions(deleteDataMap, query);
        try {
            mongoCollection.findOneAndDelete(query);
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean updateDocument(String databaseName,String collectionName,Map<String,Object>updateDataMap){
        MongoCollection mongoCollection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);
        Document query = new Document();
        query = MongoOperator.fillQueryConditions(updateDataMap, query);
        try {
            mongoCollection.findOneAndUpdate(query,Tools.mapToDocument(updateDataMap));
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    public static Document findDocument(String databaseName,String collectionName,Map<String,Object>queryDataMap){
        MongoCollection<Document> mongoCollection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);
        Document query = new Document();
        query = MongoOperator.fillQueryConditions(queryDataMap, query);
        FindIterable<Document>findIterable;
        try {
            findIterable = mongoCollection.find(query);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        MongoCursor<Document> cursor = findIterable.iterator();
        List<Document>documentList = new LinkedList<Document>();
        while (cursor.hasNext()){
            Document document = cursor.next();
            documentList.add(document);
        }
        return documentList.get(0);
    }

    public static Document fillQueryConditions(Map<String,Object>dataMap,Document query){
        if(dataMap.containsKey(Consts.MONGO_PRIMARY_KEY_NAME)){
            ObjectId objectId = new ObjectId((String)dataMap.get(Consts.MONGO_PRIMARY_KEY_NAME));
            query.put("_id",objectId);
            dataMap.remove(Consts.MONGO_PRIMARY_KEY_NAME);
        }
        return query;
    }
}
