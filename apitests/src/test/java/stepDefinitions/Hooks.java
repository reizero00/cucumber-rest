package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        PlaceValidationsSteps place = new PlaceValidationsSteps();

        if(PlaceValidationsSteps.placeId == null) {
            place.addPlacePayloadWithExample("Frontline house", "French-IN", "29, side layout, cohen 09");
            place.userCallsAPIWithPOSTRequest("AddPlaceApi", "POST");
        }
    }
}
