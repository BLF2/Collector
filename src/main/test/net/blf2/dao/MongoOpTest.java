package net.blf2.dao;

import net.blf2.util.Consts;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.print.Doc;
import java.util.*;

/**
 * Created by blf2 on 17-2-9.
 */
public class MongoOpTest {
    private String objectId;
    private Map<String,Object> data;

    @Before
    public void createResourceForTest(){
        objectId = UUID.randomUUID().toString();
        data = new HashMap<String, Object>();
        data.put(Consts.MONGO_PRIMARY_KEY_NAME,objectId);
        data.put("Test1","test1");
        data.put("Test2",2);
        data.put("Test3",3.0);
    }

    @Test
    public void testMongoOp(){
        try {
            MongoOperator.insertDocument(Consts.MONGO_DATABASE_NAME, Consts.MONGO_COLLECTION_FOR_CLASS, data);
            Map<String,Object>queryMap = new HashMap<String, Object>();
            queryMap.put(Consts.MONGO_PRIMARY_KEY_NAME,objectId);
            Document document = MongoOperator.findDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,
                    queryMap);
            Set<Map.Entry<String, Object>>keyValues = document.entrySet();
            for(Map.Entry<String,Object> keyvalue : keyValues){
                System.out.println(keyvalue.getKey() + " " + keyvalue.getValue());
            }
            data.put("Test1", "test updata");
            data.put("Test2", 4);
            data.put("Test100", 9.0);
            data.remove("Test3");
            System.out.println("data----->Test1    " + data.get("Test1").toString() + "\n------->" + data.get(Consts.MONGO_PRIMARY_KEY_NAME));
            MongoOperator.updateDocument(Consts.MONGO_DATABASE_NAME, Consts.MONGO_COLLECTION_FOR_CLASS, data);
            Map<String,Object>queryUpd = new HashMap<String, Object>();
            queryUpd.put(Consts.MONGO_PRIMARY_KEY_NAME, objectId);
            Document upddocument = MongoOperator.findDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,
                    queryUpd);
            Assert.assertTrue(upddocument != null);
            for(Map.Entry<String,Object> keyvalue : upddocument.entrySet()){
                System.out.println(keyvalue.getKey() + " " + keyvalue.getValue());
            }
            Assert.assertEquals(Integer.valueOf(4), upddocument.getInteger("Test2"));
            Assert.assertEquals(Double.valueOf(9.0), upddocument.getDouble("Test100"));
            Assert.assertTrue(upddocument.get("Test3") == null);
            List<Document> documents= MongoOperator.findAllDocuments(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS);
            System.out.println("show all documents:");
            for(Document iDocument : documents){
                for(Map.Entry<String,Object>keyvalue : iDocument.entrySet()){
                    System.out.println(keyvalue.getKey() + " " + keyvalue.getValue());
                }
            }
            Map<String,Object> delMap = new HashMap<String, Object>();
            delMap.put(Consts.MONGO_PRIMARY_KEY_NAME,objectId);
            MongoOperator.deleteDocument(Consts.MONGO_DATABASE_NAME, Consts.MONGO_COLLECTION_FOR_CLASS, delMap);
            Map<String,Object> delFindMap = new HashMap<String, Object>();
            delFindMap.put(Consts.MONGO_PRIMARY_KEY_NAME, objectId);
            document = MongoOperator.findDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,delFindMap);
            Assert.assertTrue(document == null || document.isEmpty());
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }
}
