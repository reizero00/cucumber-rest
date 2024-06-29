package com.konias.cucumber;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecifications {
    RequestSpecification requestSpecification;
        /**
     * Returns a RequestSpecification object that contains the base URI and query parameters.
     * @return the RequestSpecification object
     */
    public RequestSpecification getRequestSpecification() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType("application/json")
                .build();

        return requestSpecification;
    }

}
