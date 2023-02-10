Feature: Application Login

  Scenario: Home page default login
    Given User is on banking landing page
    When User login into the application with username and password
    Then HomePage is populated
    And cards displayed "True"

  Scenario: Home page default login
    Given User is on banking landing page
    When User login into the application with "Naf" and password "Kha"
    Then HomePage is populated
    And cards displayed "True"


  Scenario: Home page default login
    Given User is on banking landing page
    When User login into the application with "wrongNaf" and password "WrongKha"
    Then HomePage is populated
    And cards displayed "False"


