package stepDefinitions;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AddPlace;

import com.konias.cucumber.PlaceApis;

public class PlaceValidationsSteps {

    PlaceApis placeApis;
    AddPlace addPlaceRequestBody;
    RequestSpecification getRequestSpecification;
    Response addPlaceResponse;
    

    @Given("^Add Place Payload$")
    public void addPlacePayload() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        PlaceApis placeApis = new PlaceApis();

        addPlaceRequestBody =  placeApis.createAddPlaceRequest("29, side layout, cohen 09", "-38.383494", "33.427362", "Frontline house", "(+91) 983 893 3937", "http://google.com", "French-IN", "69", new String[]{"shoe park", "shop"});
        
    }
    
    @When("^User calls \"([^\"]+)\" with POST HTTP request$")
    public void userCallsAPIWithPOSTRequest(String apiName) {
        // Add your code to call the API here
        System.out.println(addPlaceRequestBody);
        PlaceApis placeApis = new PlaceApis();

        addPlaceResponse = placeApis.addPlace(addPlaceRequestBody);
    }

    @Then("the response should return status {int} successful")
    public void theResponseShouldReturnStatusSuccessful(int expectedStatusCode) {
        int actualStatusCode = addPlaceResponse.getStatusCode();

        assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Then("{string} in the response should be {string}")
    public void scopeInResponseShouldBe(String expectedKey, String expectedValue) {
        String responseString = addPlaceResponse.asString();
        JsonPath responseJson = new JsonPath(responseString);
        String actualValue = responseJson.getString(expectedKey);

        assertEquals(expectedValue, actualValue);
    }
    
}