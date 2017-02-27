package net.blf2.util;

import com.google.gson.reflect.TypeToken;
import net.blf2.entity.UserRoleInfo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by blf2 on 17-1-8.
 */
public class Consts {
    private static Properties properties;
    private static List<RoleInfo> roleInfoList;
    public static Map<String,Map<String,Boolean>> roleRuleMap;
    static{
        properties = new Properties();
        try {
            properties.load(Consts.class.getClassLoader().getResourceAsStream("consts.properties"));
            roleInfoList = Tools.loadJson("rbac.json",new TypeToken<List<RoleInfo>>(){}.getType());
            if(roleInfoList != null){
                roleRuleMap = new HashMap<String, Map<String, Boolean>>();
                for(RoleInfo roleInfo : roleInfoList){
                    List<RoleRule> roleRuleList = roleInfo.getRoleRules();
                    Map<String,Boolean> roleRuleKeyValue = new HashMap<String, Boolean>();
                    for(RoleRule roleRule : roleRuleList){
                        roleRuleKeyValue.put(roleRule.getRoleRuleName(),roleRule.getRoleRuleValue());
                    }
                    roleRuleMap.put(roleInfo.getRoleName(),roleRuleKeyValue);
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static String MAJORNAME_GRADE_NUM_LIST = "majorNameGradeNumList";
    public static String USER_GRADE_NAME_VALUE = "UserGradeNameValue";
    public static String VALIDATE_CODE = "validateCode";
    public static String FRONT_GRADE_ALL = "majorNameGradeNum";
    public static String FRONT_SCORE_MAP = "userScoreMap";
    public static String LOGIN_INFO = "LoginInfo";
    public static String USER_SUM_SCORE = "userSumScore";
    public static String MONITOR_ROLE_NAME = "monitor";
    public static String PRIMARY_ROLE_NAME = "primary";
    public static String ADMIN_ROLE_NAME = "admin";
    public static int CONNECTIONS_PER_HOST = properties.getProperty("CONNECTIONS_PER_HOST") != null ? Integer.parseInt(properties.getProperty("CONNECTIONS_PER_HOST")) : 50;
    public static int THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER = properties.getProperty("THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER") != null ? Integer.parseInt(properties.getProperty("THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER")) : 50;
    public static int CONNECT_TIMEOUT = properties.getProperty("CONNECT_TIMEOUT") != null ? Integer.parseInt(properties.getProperty("CONNECT_TIMEOUT")) : 60000;
    public static int MAX_WAIT_TIME = properties.getProperty("MAX_WAIT_TIME") != null ? Integer.parseInt(properties.getProperty("MAX_WAIT_TIME")) : 120000;
    public static String MONGODB_HOST = properties.getProperty("MONGODB_HOST") != null ? properties.getProperty("MONGODB_HOST") : "127.0.0.1";
    public static int SOCKET_TIME_OUT = properties.getProperty("SOCKET_TIME_OUT") != null ? Integer.parseInt(properties.getProperty("SOCKET_TIME_OUT")) : 5000;
    public static int MONGODB_PORT = properties.getProperty("MONGODB_PORT") != null ? Integer.parseInt(properties.getProperty("MONGODB_PORT")) : 27017;
    public static boolean SOCKET_KEEP_ALIVE = properties.getProperty("SOCKET_KEEP_ALIVE") != null ? Boolean.parseBoolean(properties.getProperty("SOCKET_KEEP_ALIVE")) : false;
    public static String MONGO_DATABASE_NAME = properties.getProperty("MONGO_DATABASE_NAME") != null ? properties.getProperty("MONGO_DATABASE_NAME") : "app";
    public static String MONGO_COLLECTION_FOR_CLASS = properties.getProperty("MONGO_COLLECTION_FOR_CLASS") != null ? properties.getProperty("MONGO_COLLECTION_FOR_CLASS") : "app_data";
    public static String MONGO_PRIMARY_KEY_NAME = properties.getProperty("MONGO_PRIMARY_KEY_NAME") != null ? properties.getProperty("MONGO_PRIMARY_KEY_NAME") : "objectId";
    public static String USER_INFO_NAME = properties.getProperty("USER_INFO_NAME") != null ? properties.getProperty("USER_INFO_NAME") : "UserInfo";
    public static String DATABASE_RETURN_ERROR = properties.getProperty("DATABASE_RETURN_ERROR") != null ? properties.getProperty("DATABASE_RETURN_ERROR") : "database return value error";
    public static String WEB_ERROR_MWSSAGE = properties.getProperty("WEB_ERROR_MWSSAGE") != null ? properties.getProperty("WEB_ERROR_MWSSAGE") : "WebErrorMessage";
    public static String PRIMARY_ROLR_ID = properties.getProperty("PRIMARY_ROLR_ID") != null ? properties.getProperty("PRIMARY_ROLR_ID") : "651361f3-aef0-47a4-b341-820432b08c8e" ;
    public static String MONITOR_ROLE_ID = properties.getProperty("MONITOR_ROLE_ID") != null ? properties.getProperty("MONITOR_ROLE_ID") : "a2ec492c-dc3c-4130-b40b-f4854bf4ce46";
    public static String ADMIN_ROLE_ID = properties.getProperty("ADMIN_ROLE_ID") != null ? properties.getProperty("ADMIN_ROLE_ID") : "9e47f1eb-aef3-42a8-b400-2c7d59d85db0";
    public static String ADMIN_ACCOUNT_DEFAULT = properties.getProperty("ADMIN_ACCOUNT_DEFAULT") != null ? properties.getProperty("ADMIN_ACCOUNT_DEFAULT") : "13110572081";
    public static String ADMIN_PASSWORD_DEFAULT = properties.getProperty("ADMIN_PASSWORD_DEFAULT") != null ? properties.getProperty("ADMIN_PASSWORD_DEFAULT") : "mxh19940822";
    public static String EXCEL_FILE_PATH = properties.getProperty("EXCEL_FILE_PATH") != null ? properties.getProperty("EXCEL_FILE_PATH") : "staticfile/downloadfiles";
}
