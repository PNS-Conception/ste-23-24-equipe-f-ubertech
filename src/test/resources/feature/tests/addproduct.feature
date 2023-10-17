Feature: Add a product to a pendingOrder
  Scenario:
    Given a customer
    When I add a product to my pendingOrder
    Then  the product is stored in my pendingOrder