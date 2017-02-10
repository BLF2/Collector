package net.blf2.dao;

import net.blf2.util.Consts;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
            data.put("Test1","test updata");
            data.put("Test2",4);
            data.put("Test3",9.0);
            MongoOperator.updateDocument(Consts.MONGO_DATABASE_NAME, Consts.MONGO_COLLECTION_FOR_CLASS, data);
            document = MongoOperator.findDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,
                    queryMap);
            Assert.assertEquals("test updata",document.getString("Test1"));
            Assert.assertEquals(Integer.valueOf(4),document.getInteger("Test2"));
            Assert.assertEquals(Double.valueOf(9.0),document.getDouble("Test3"));
            MongoOperator.deleteDocument(Consts.MONGO_DATABASE_NAME, Consts.MONGO_COLLECTION_FOR_CLASS, queryMap);
            document = MongoOperator.findDocument(Consts.MONGO_DATABASE_NAME,Consts.MONGO_COLLECTION_FOR_CLASS,queryMap);
            Assert.assertTrue(document == null || document.isEmpty());
        }catch (Exception ex){
            ex.printStackTrace();
            Assert.assertTrue(false);
        }
        Assert.assertTrue(true);
    }
}
