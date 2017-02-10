package net.blf2.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by blf2 on 17-1-14.
 */
public class Tools {
    public static <T> Map<String,Object> encodeClass(T classInstance) throws IllegalAccessException {
        Field[] fields = classInstance.getClass().getDeclaredFields();
        Map<String,Object>fieldToValueMap = new HashMap<String, Object>();
        for(Field field : fields){
            field.setAccessible(true);
            fieldToValueMap.put(field.getName(),field.get(classInstance));
        }
        return fieldToValueMap;
    }
    public static <T> T decodeClass(Class<T> tClass,Map<String,Object>classFieldsMap){
        Field[] fields = tClass.getDeclaredFields();
        T t;
        try {
            t = tClass.newInstance();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
        for(Field field : fields){
            field.setAccessible(true);
            try {
                field.set(t,classFieldsMap.get(field.getName()));
            }catch (Exception ex){
                ex.printStackTrace();
                return null;
            }
        }
        return t;
    }
    public static <T>Document javaBeanToMongoObject(T document) throws IllegalAccessException {
        Document newDocument = new Document();
        newDocument.putAll(Tools.encodeClass(document));
        return newDocument;
    }
    public static Document mapToDocument(Map<String,Object>keyValueMap){
        Document document = new Document();
        boolean flag = false;
        for(Map.Entry<String,Object> entry : keyValueMap.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!flag && key.equals(Consts.MONGO_PRIMARY_KEY_NAME)){
                document.put("_id",value);
                flag = true;
                continue;
            }
            document.put(key,value);
        }
        return document;
    }
}
