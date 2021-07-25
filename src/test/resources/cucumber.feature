Feature: the version can be retrieved
  Scenario: client makes call to GET /User
    When the client calls GET /User
    Then the client receives status code of 200
  Scenario: client makes call to POST /User
    When the client calls POST /User
    Then the client receives post status code of 201
    And create user response should be valid
  Scenario: client makes call to PUT /User/Id
    When the client calls PUT /User/Id
    Then the client receives put status code of 200
    And update user response should be valid
  Scenario: client makes call to DELETE /User/Id
    When the client calls DELETE /User/Id
    Then the client receives delete status code of 204