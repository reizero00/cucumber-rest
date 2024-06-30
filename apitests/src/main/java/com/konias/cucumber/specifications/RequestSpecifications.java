package com.konias.cucumber.specifications;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {
    RequestSpecification requestSpecification;
        /**
     * Returns a RequestSpecification object that contains the base URI and query parameters.
     * @return the RequestSpecification object
         * @throws IOException 
     */
    public RequestSpecification getRequestSpecification() throws IOException {
        PrintStream log = new PrintStream(new FileOutputStream("logging.txt", true));

        String requestBaseUri = getProperties("baseUri");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(requestBaseUri)
                .addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType("application/json")
                .build();

        return requestSpecification;
    }

    public static String getProperties(String propertyKey) throws IOException{
        Properties testProperties = new Properties();
       
        String baseDirectory = System.getProperty("user.dir");

        FileInputStream propertiesFile = new FileInputStream(baseDirectory + "/src/test/java/resources/test.properties");
        testProperties.load(propertiesFile);

        return testProperties.getProperty(propertyKey);
        
    }

}