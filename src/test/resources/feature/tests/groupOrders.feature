Feature: Group orders
  Scenario: Create a group order
    Given a customer who payed for their order
    When they want to create a group order
    Then a new group order is created
  Scenario: Join a group order
    Given a customer who payed for they order
    When they want to join a group order
    Then their order is added to the group order
  Scenario: Retrieve orders from group order
    Given a delivery person
    When they recieve the notification to pick the orders up
    Then they need to know all the individual orders
