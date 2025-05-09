Feature: Validate login page

  @test
  Scenario: validate post API
    Given I have get "baseURL"
    When I have "baseURL/REGISTER" using post method
      |header|Content-Type|application/json|
      |header|x-api-key   |reqres-free-v1  |
      |body  |email       |eve.holt@reqres.in|
      |body  |password    |pistol          |
    Then Verify "200" error message
    Then Verify response
      |token      |QpwL5tke4Pnpja7X4         |

  @test
  Scenario: validate post API
    Given I have get "baseURL"
    When I have "baseURL/REGISTER" using post method
      |header|Content-Type|application/json|
      |header|x-api-key   |reqres-free-v1  |
      |body  |email       |eve.holt@reqres.in |
    Then Verify "400" error message
    Then Verify response
      |error      |Missing password         |
