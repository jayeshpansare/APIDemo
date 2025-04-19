Feature: Validate login page

  @test @dashboard @run
  Scenario:  validate login with valid inputs
    Given I have get "baseURL"
    When I have get "baseURL/GET_LIST_USERS" using get method
          |header|Content-Type|application/json|
          |header|Content-Type1|application/json2|
          |header 123|Content-Type1|application/json2|
    Then Verify "200" error message