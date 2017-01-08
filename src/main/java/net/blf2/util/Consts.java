package net.blf2.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by blf2 on 17-1-8.
 */
public class Consts {
    private static Properties properties;
    static{
        properties = new Properties();
        try {
            properties.load(Object.class.getResourceAsStream("consts.properties"));
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static int CONNECTIONS_PER_HOST = properties.getProperty("CONNECTIONS_PER_HOST") != null ? Integer.parseInt(properties.getProperty("CONNECTIONS_PER_HOST")) : 50;
    public static int THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER = properties.getProperty("THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER") != null ? Integer.parseInt(properties.getProperty("THREADS_ALLOWED_TO_BLOCK_FOR_CONNECTION_MULTIPLIER")) : 50;
    public static int CONNECT_TIMEOUT = properties.getProperty("CONNECT_TIMEOUT") != null ? Integer.parseInt(properties.getProperty("CONNECT_TIMEOUT")) : 60000;
    public static int MAX_WAIT_TIME = properties.getProperty("MAX_WAIT_TIME") != null ? Integer.parseInt(properties.getProperty("MAX_WAIT_TIME")) : 120000;
    public static String MONGODB_HOST = properties.getProperty("MONGODB_HOST") != null ? properties.getProperty("MONGODB_HOST") : "127.0.0.1";
    public static int SOCKET_TIME_OUT = properties.getProperty("SOCKET_TIME_OUT") != null ? Integer.parseInt(properties.getProperty("SOCKET_TIME_OUT")) : 5000;
    public static int MONGODB_PORT = properties.getProperty("MONGODB_PORT") != null ? Integer.parseInt(properties.getProperty("MONGODB_PORT")) : 27017;
    public static boolean SOCKET_KEEP_ALIVE = properties.getProperty("SOCKET_KEEP_ALIVE") != null ? Boolean.parseBoolean(properties.getProperty("SOCKET_KEEP_ALIVE")) : false;
    public static String MONGO_DATABASE_NAME = properties.getProperty("MONGO_DATABASE_NAME") != null ? properties.getProperty("MONGO_DATABASE_NAME") : "app";
}
