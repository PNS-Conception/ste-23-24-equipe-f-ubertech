Feature: Customer checks their history
  Scenario: A customer checks their history
    Given A Customer who has ordered previously
    When they check their history
    Then they can acces all the previous orders