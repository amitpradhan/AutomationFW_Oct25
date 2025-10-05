package com.automation.megamind.utils;

import java.io.*;
import java.util.Properties;

import static com.automation.megamind.constants.Constants.CONFIG_PROPERTIES;

public class PropertyReader {


    public static String getProperty(String filepath , String propertyKey){
        Properties properties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filepath));
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyKey);
    }


    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
//        String file = System.getProperty("user.dir")+"/src/test/resources/config.properties";
        System.out.println(getProperty(CONFIG_PROPERTIES , "browserType"));
        System.out.println(getProperty(CONFIG_PROPERTIES , "url"));
    }

}
