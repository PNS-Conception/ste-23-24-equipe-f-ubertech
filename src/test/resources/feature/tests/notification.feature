Feature: Different notifications when an order is ready to cook or cooked
  Scenario: Delivery person gets notified when the order is ready
    Given a delivery person
    When the order is marked as ready
    Then they receive a notification


  Scenario: Restaurant is notified when there is a new order arrives
    Given a restaurant named "MacDonalds" and a customer
    When the customer makes a new order for "MacDonalds"
    Then the restaurant receive a notification

