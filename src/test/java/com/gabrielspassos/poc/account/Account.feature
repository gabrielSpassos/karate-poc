Feature: account

  Background: 
    * url baseUrl
    * def accountsPath = '/v1/accounts'
    * def accountId = 1

  Scenario: Get all accounts with empty content
    Given path accountsPath
    When method GET
    Then status 200
    And match $ contains {empty: true}

  Scenario: Create account
    Given url 'http://localhost:8080/v1/accounts'
    And request { bank: 121 , agency: 1, number: 123456}
    When method POST
    Then status 200
    And match response == {id:"#notnull",bank: 121, agency: 1, number: 123456}
    And def newAccount = response

  Scenario: Get account by id
    Given path accountsPath + '/' + accountId
    When method GET
    Then status 200
    And match $ == {id:"#notnull",bank: 121, agency: 1, number: 123456}

  Scenario: Try to create duplicate account and return error
    Given url accountUrl
    And request { bank: 121 , agency: 1, number: 123456}
    When method POST
    Then status 400
    And match $ == {"code":"2","message":"Account already existent"}

  Scenario: Get all accounts
    Given url 'http://localhost:8080/v1/accounts'
    When method GET
    Then status 200
    And match $ contains {content: [{id:"#notnull",bank: 121, agency: 1, number: 123456}]}
