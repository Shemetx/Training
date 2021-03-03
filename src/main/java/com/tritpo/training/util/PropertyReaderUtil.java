package com.tritpo.training.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public final class PropertyReaderUtil {

    private static final Logger logger = LogManager.getLogger(PropertyReaderUtil.class);

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    public static Properties getInstance() {
        return properties;
    }

    public static void loadProperties() throws IOException {
        final String propertiesFileName = "C:/epam/final-project/src/main/resources/application.properties";
        try (InputStream inputStream = new FileInputStream(propertiesFileName)) {
            properties.load(inputStream);
            logger.info("App properties successfully loaded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.fatal("Can't find properties file");
        }
    }

    public static String url() { return properties.getProperty("url");}
    public static String dbName() {return  properties.getProperty("dbname");}
    public static String user() { return properties.getProperty("user");}
    public static String password() {return properties.getProperty("password");}
    public static String availableConnections() {return properties.getProperty("availableConnections");}
    public static String mailPassword() {return properties.getProperty("mailPassword");}
    public static String mailSender() {return properties.getProperty("mailSender");}
    public static String flyWayPath() {return properties.getProperty("flyWayPath");}
}
