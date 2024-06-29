Feature: Validations for Place API's


Scenario: Ability to add a Place using AddPlaceAPI
    Given Add Place Payload
    When User calls "AddPlaceAPI" with POST HTTP request
    Then the response should return status 200 successful
        And "status" in the response should be "OK"
        And "scope" in the response should be "APP"
    