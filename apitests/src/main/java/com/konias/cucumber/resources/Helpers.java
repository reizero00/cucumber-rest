package com.konias.cucumber.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Helpers {
    public String getProperties(String propertyKey) throws IOException{
        Properties testProperties = new Properties();
       
        String baseDirectory = System.getProperty("user.dir");

        FileInputStream propertiesFile = new FileInputStream(baseDirectory + "/src/test/java/resources/test.properties");
        testProperties.load(propertiesFile);

        return testProperties.getProperty(propertyKey);
        
    }
}
