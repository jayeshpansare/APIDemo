Feature: Validate login page

  @test
  Scenario: validate get API
    Given I have get "baseURL"
    When I have "baseURL/GET_LIST_USERS" using get method
          |header|Content-Type|application/json|
    Then Verify "200" error message
    Then Verify response
      |data[0].id        |7                                      |
      |data[0].email     |michael.lawson@reqres.in               |
      |data[0].first_name|Michael                                |
      |data[0].last_name |Lawson                                 |
      |data[0].avatar    |https://reqres.in/img/faces/7-image.jpg|
      |data[1].id        |8                                      |
      |data[1].email     |lindsay.ferguson@reqres.in             |
      |data[1].first_name|Lindsay                                |
      |data[1].last_name |Ferguson                               |
      |data[1].avatar    |https://reqres.in/img/faces/8-image.jpg|

  @test
  Scenario: validate post API
    Given I have get "baseURL"
    When I have "baseURL/POST_USERS" using post method
      |header|Content-Type|application/json|
      |header|x-api-key   |reqres-free-v1  |
      |body  |name        |test            |
      |body  |job         |leader          |
    Then Verify "201" error message
    Then Verify response
      |name      |test         |
      |job       |leader       |

  @test
  Scenario: validate update API
    Given I have get "baseURL"
    When I have "baseURL/UPDATE_USERS" using update method
      |header|Content-Type|application/json|
      |header|x-api-key   |reqres-free-v1  |
      |body  |name        |test            |
      |body  |job         |leader          |
    Then Verify "200" error message
    Then Verify response
      |name      |test         |
      |job       |leader       |

  @test
  Scenario: validate delete API
    Given I have get "baseURL"
    When I have "baseURL/DELETE_USERS" using delete method
      |header|Content-Type|application/json|
      |header|x-api-key   |reqres-free-v1  |
    Then Verify "204" error message