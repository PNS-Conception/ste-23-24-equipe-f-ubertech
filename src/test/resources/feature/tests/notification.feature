Feature: Notifie delivery person
  Scenario: Delivery person gets notified when the order is ready
    Given a delivery person
    When the order is marked as ready
    Then they recieve a notification
