package com.konias.cucumber;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import pojo.AddPlace;
import pojo.Location;

public class PlaceApis {

    /**
     * Adds a new place to the Google Map API.
     * @param addPlaceRequestBody the body of the request
     * @return the response as a string
     */
    public Response addPlace(AddPlace addPlaceRequestBody) {
        return given()
                .spec(getRequestSpecification())
                .body(addPlaceRequestBody)
                .log().all()
                .when()
                .post("/maps/api/place/add/json")
            .then()
                .spec(getResponseSpecification())
                .extract().response();
    }

    /**
     * Constructs an AddPlace object with the given parameters.
     * @return the constructed AddPlace object
     */
    public AddPlace createAddPlaceRequest(String address, String lat, String lng, String name, String phoneNumber, String website, String language, String accuracy, String[] types) {
        AddPlace addPlaceRequestBody = new AddPlace();
        addPlaceRequestBody.setAccuracy(Integer.parseInt(accuracy));
        addPlaceRequestBody.setAddress(address);
        addPlaceRequestBody.setLanguage(language);
        addPlaceRequestBody.setLocation(new Location(Double.parseDouble(lat), Double.parseDouble(lng)));
        addPlaceRequestBody.setName(name);
        addPlaceRequestBody.setPhone_number(phoneNumber);
        addPlaceRequestBody.setWebsite(website);
        addPlaceRequestBody.setTypes(new ArrayList<String>(Arrays.asList(types)));
        return addPlaceRequestBody;
    }

    /**
     * Returns a RequestSpecification object that contains the base URI and query parameters.
     * @return the RequestSpecification object
     */
    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType("application/json")
                .build();
    }

    /**
     * Returns a ResponseSpecification object that contains the expected status code and content type.
     * @return the ResponseSpecification object
     */
    public static ResponseSpecification getResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }

}
