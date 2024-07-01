Feature: Validations for Place API's

@AddPlace
Scenario: Ability to add a Place using AddPlaceAPI
    Given Add Place Payload with "<name>", "<language>" and "<address>"
    When User calls "AddPlaceApi" with "POST" HTTP request
    Then the response should return status 200 successful
        And "status" in the response should be "OK"
        And "scope" in the response should be "APP"
        And using "GetPlaceApi", the added map should have the same "name": "<name>"
        And using "GetPlaceApi", the added map should have the same "language": "<language>"
        And using "GetPlaceApi", the added map should have the same "address": "<address>"
    
    Examples:
        | name         | language  | address                     |
        | AAHouse      | English   | World cross Centre, London  |
        # | BBHouse      | Japanese  | World cross Centre, London  |

@DeletePlace
Scenario: Ability to delete a Place using DeletePlaceAPI
    Given Delete Place Payload
    When User calls "DeletePlaceApi" with "DELETE" HTTP request
    Then the response should return status 200 successful
        And "status" in the response should be "OK"
