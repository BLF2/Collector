package net.blf2.util;

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
}
