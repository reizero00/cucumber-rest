package com.konias.cucumber.specifications;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import com.konias.cucumber.resources.Helpers;

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

        Helpers helpers = new Helpers();
        
        String requestBaseUri = helpers.getProperties("baseUri");
        String placeKey = helpers.getProperties("placeKey");

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(requestBaseUri)
                .addQueryParam("key", placeKey)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType("application/json")
                .build();

        return requestSpecification;
    }

}