package com.konias.cucumber;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecifications {
    ResponseSpecification responseSpecification;
        /**
     * Returns a ResponseSpecification object that contains the expected status code and content type.
     * @return the ResponseSpecification object
     */
    public ResponseSpecification getResponseSpecification() {
        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();

        return responseSpecification;
    }

}
