package net.blf2.util;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public static String generateInvitationCode(String userNum) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String str = userNum + " " + sdf.format(new Date());
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new Exception("生成出现错误");
        }
    }
    public static boolean checkInvitationCode(String code,String userNum) throws Exception{
        try {
            return Tools.generateInvitationCode(userNum).equals(code);
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("程序出错验证失败");
        }
    }
    public static <T> T loadJson(String path,Type type)throws Exception{
        String jsonString = "";
        String buffer = "";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Tools.class.getClassLoader().getResourceAsStream(path)));
        while((buffer = bufferedReader.readLine()) != null){
            jsonString +=buffer;
        }
        T instance = new Gson().fromJson(jsonString,type);
        return instance;
    }
}
