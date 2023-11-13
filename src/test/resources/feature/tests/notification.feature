Feature: Notifie delivery person
  Scenario: Delivery person gets notified when the order is ready
    Given a delivery person
    When the order is marked as ready
    Then they are no longer available
    And they are able to read informations like : the venue, the customer's name, the order's id....

  Scenario:
    Given a restaurantEmployee finishing an order
    When they validate the end of the preparation of an order
    Then the deliveryPerson receive a notification