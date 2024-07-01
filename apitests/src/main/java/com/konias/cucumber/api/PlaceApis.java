package com.konias.cucumber.api;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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


    /**
     * Constructs an AddPlace object with the given parameters.
     * @return the constructed AddPlace object
     * @throws JsonProcessingException 
     */
    public Object createAddPlaceRequest(String address, String lat, String lng, String name, String phoneNumber, String website, String language, String accuracy, String[] types){
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

    public String getValueFromPlaceResponseByKey(Response responseBody, String keyPath) {
        return responseBody.jsonPath().getString(keyPath);
    }

    public Response getPlace(String requestEndpoint, String placeId) throws IOException{
        ApiResources apiResources = ApiResources.valueOf(requestEndpoint);

        String getPlaceEndpoint = apiResources.getResource();

        RequestSpecifications requestSpecification = new RequestSpecifications();
        ResponseSpecifications responseSpecification = new ResponseSpecifications();


        return given()
                .spec(requestSpecification.getRequestSpecification())
                .queryParams("place_id", placeId)
            .when()
                .get(getPlaceEndpoint)
                .then()
                .spec(responseSpecification.getResponseSpecification())
                .extract().response();
    }

    public Map<String,Object> createDeletePlaceRequest(String placeId){
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("place_id", placeId);

        return requestPayload;

    }

}
