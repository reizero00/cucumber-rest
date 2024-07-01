package stepDefinitions;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.konias.cucumber.api.ApiResources;
import com.konias.cucumber.api.HttpMethodRequests;
import com.konias.cucumber.api.PlaceApis;


public class PlaceValidationsSteps {
    
    JsonPath placeApis;
    Map<String, Object> deletePlaceRequestBody;
    Object addPlaceRequestBody;
    RequestSpecification getRequestSpecification;
    static Response placeResponse;
    static String placeId;
    
    @Given("^Add Place Payload$")
    public void addPlacePayload() throws JsonProcessingException {
        PlaceApis placeApis = new PlaceApis();
        
        addPlaceRequestBody =  placeApis.createAddPlaceRequest("29, side layout, cohen 09", "-38.383494", "33.427362", "Frontline house", "(+91) 983 893 3937", "http://google.com", "French-IN", "69", new String[]{"shoe park", "shop"});
    }

    @Given("^Delete Place Payload$")
    public void createDeletePlacePayload() throws JsonProcessingException {
        PlaceApis placeApis = new PlaceApis();
        placeId = placeApis.getValueFromPlaceResponseByKey(placeResponse, "place_id");

        deletePlaceRequestBody =  placeApis.createDeletePlaceRequest(placeId);
    }

    @Given("Add Place Payload with {string}, {string} and {string}")
    public void addPlacePayloadWithExample(String name, String language, String address) throws JsonProcessingException {
        PlaceApis placeApis = new PlaceApis();
        
        addPlaceRequestBody =  placeApis.createAddPlaceRequest(
            address, 
            "-38.383494", 
            "33.427362", 
            name, 
            "(+91) 983 893 3937", 
            "http://google.com", 
            language, 
            "69", 
            new String[]{"shoe park", "shop"}
            );
        
    }
    
    @When("User calls {string} with {string} HTTP request")
    public void userCallsAPIWithPOSTRequest(String apiName, String httpMethod) throws IOException {
        ApiResources apiResources = ApiResources.valueOf(apiName);
        String apiEndpoint = apiResources.getResource();
        
        HttpMethodRequests httpMethodRequests = new HttpMethodRequests();
        if(httpMethod.equalsIgnoreCase("POST")) {
            placeResponse = httpMethodRequests.post(addPlaceRequestBody, apiEndpoint);            
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            placeResponse = httpMethodRequests.get(addPlaceRequestBody, apiEndpoint);
        } else if (httpMethod.equalsIgnoreCase("DELETE")) {
            placeResponse = httpMethodRequests.delete(deletePlaceRequestBody, apiEndpoint);
        }
    }

    @Then("the response should return status {int} successful")
    public void theResponseShouldReturnStatusSuccessful(int expectedStatusCode) {
        int actualStatusCode = placeResponse.getStatusCode();

        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("{string} in the response should be {string}")
    public void scopeInResponseShouldBe(String expectedKey, String expectedValue) {
        String responseString = placeResponse.asString();
        JsonPath responseJson = new JsonPath(responseString);
        String actualValue = responseJson.getString(expectedKey);

        assertEquals(expectedValue, actualValue);
    }

    @Then("using {string}, the added map should have the same {string}: {string}")
    public void verifyGetPlaceHavingValue(String requestEndpoint, String keyName, String expectedName) throws IOException {
        // get the placeId from Add Place API response
        PlaceApis placeApis = new PlaceApis();

        placeId = placeApis.getValueFromPlaceResponseByKey(placeResponse, "place_id");

        // use the placeId to get the place details from Get Place API
        Response getPlaceResponse = placeApis.getPlace(requestEndpoint, placeId);

        // Assert that the name matches expected value
        String actualName = placeApis.getValueFromPlaceResponseByKey(getPlaceResponse, keyName);

        Assert.assertEquals(expectedName, actualName);

    }

}
