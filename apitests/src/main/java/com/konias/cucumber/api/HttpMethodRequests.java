package com.konias.cucumber.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.konias.cucumber.specifications.RequestSpecifications;
import com.konias.cucumber.specifications.ResponseSpecifications;

import io.restassured.response.Response;

public class HttpMethodRequests {
    public Response post(Object requestBody, String apiEndpoint) throws IOException {
    RequestSpecifications requestSpecification = new RequestSpecifications();

    ResponseSpecifications responseSpecification = new ResponseSpecifications();

    return given()
            .spec(requestSpecification.getRequestSpecification())
            .body(requestBody)
            .when()
            .post(apiEndpoint)
        .then()
            .spec(responseSpecification.getResponseSpecification())
            .extract().response();
    }

    public Response get(Object requestBody, String apiEndpoint) throws IOException {
        RequestSpecifications requestSpecification = new RequestSpecifications();
    
        ResponseSpecifications responseSpecification = new ResponseSpecifications();
    
        return given()
                .spec(requestSpecification.getRequestSpecification())
                .body(requestBody)
                .when()
                .get(apiEndpoint)
            .then()
                .spec(responseSpecification.getResponseSpecification())
                .extract().response();
        }
}
