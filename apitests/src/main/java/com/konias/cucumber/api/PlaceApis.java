package com.konias.cucumber.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.konias.cucumber.models.pojo.AddPlace;
import com.konias.cucumber.models.pojo.Location;
import com.konias.cucumber.specifications.RequestSpecifications;
import com.konias.cucumber.specifications.ResponseSpecifications;

import io.restassured.response.Response;


public class PlaceApis {

    /**
     * Adds a new place to the Google Map API.
     * @param addPlaceRequestBody the body of the request
     * @return the response as a string
     * @throws IOException 
     */
    public Response addPlace(AddPlace addPlaceRequestBody) throws IOException {
        RequestSpecifications requestSpecification = new RequestSpecifications();

        ResponseSpecifications responseSpecification = new ResponseSpecifications();

        return given()
                .spec(requestSpecification.getRequestSpecification())
                .body(addPlaceRequestBody)
                .when()
                .post("/maps/api/place/add/json")
            .then()
                .spec(responseSpecification.getResponseSpecification())
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

}
