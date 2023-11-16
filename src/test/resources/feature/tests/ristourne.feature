Feature: Restaurant Discount
  Scenario:
    Given a customer who has a discount in a restaurant
    When they order in this restaurant
    Then the order is cheaper
