package net.blf2.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import net.blf2.util.Consts;
import net.blf2.util.Tools;
import org.bson.Document;
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
        Document updateDocument = Tools.mapToDocument(updateDataMap);
        try {
            mongoCollection.replaceOne(query, updateDocument);
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
        if(cursor == null)
            return null;
        List<Document>documentList = new LinkedList<Document>();
        while (cursor.hasNext()){
            documentList.add(cursor.next());
        }
        return documentList.size() > 0 ? documentList.get(0) : null;
    }

    public static List<Document> findAllDocuments(String databaseName,String collectionName){
        MongoCollection<Document> mongoCollection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);
        FindIterable<Document>findIterable;
        try {
            findIterable = mongoCollection.find();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        MongoCursor<Document> cursor = findIterable.iterator();
        if(cursor == null)
            return null;
        List<Document>documentList = new LinkedList<Document>();
        while (cursor.hasNext()){
            documentList.add(cursor.next());
        }
        return documentList;
    }
    public static List<Document> findAllDocumentsByFilter(String databaseName,String collectionName,Map<String,Object>queryDataMap){
        MongoCollection<Document> mongoCollection = MongoDriver.getMongoCollectionByName(databaseName,collectionName);
        Document query = new Document();
        query = Tools.mapToDocument(queryDataMap);
        FindIterable<Document>findIterable;
        try {
            findIterable = mongoCollection.find(query);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        MongoCursor<Document> cursor = findIterable.iterator();
        if(cursor == null)
            return null;
        List<Document>documentList = new LinkedList<Document>();
        while (cursor.hasNext()){
            documentList.add(cursor.next());
        }
        return documentList;
    }
    public static Document fillQueryConditions(Map<String,Object>dataMap,Document query){
        if(dataMap.containsKey(Consts.MONGO_PRIMARY_KEY_NAME)){
            query.put("_id",dataMap.get(Consts.MONGO_PRIMARY_KEY_NAME));
            dataMap.remove(Consts.MONGO_PRIMARY_KEY_NAME);
        }
        return query;
    }
}
