Feature: Validations for Place API's


Scenario: Ability to add a Place using AddPlaceAPI
    Given Add Place Payload with "<name>", "<language>" and "<address>"
    When User calls "AddPlaceApi" with "POST" HTTP request
    Then the response should return status 200 successful
        And "status" in the response should be "OK"
        And "scope" in the response should be "APP"
    
    Examples:
        | name         | language  | address                     |
        | AAHouse      | English   | World cross Centre, London  |
        | BBHouse      | Japanese  | World cross Centre, London  |

