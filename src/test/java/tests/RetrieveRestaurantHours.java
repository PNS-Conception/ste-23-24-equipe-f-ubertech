package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Customer;
import sophiatech.Hours;
import sophiatech.Product;
import sophiatech.Restaurant;
import sophiatech.System;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RetrieveRestaurantHours {
    private Restaurant restaurant;
    private Customer customer;
    private System system = new System();

    @Given("a Customer") //TO CHANGE WITH CONTAINER
    public void a_customer() {
        customer = new Customer("Beurel","Simon", system);
    }
    @When("I want to check the operating hours of a restaurant")
    public void check_hours_restaurant() {
        Hours h = new Hours(new Date(2021, 1, 1, 8, 0), new Date(2021, 1, 1, 20, 0));
        restaurant = new Restaurant("McDonalds", "Paris", h);
        system.addRestaurant(restaurant);
    }
    @Then("I retrieve the restaurant's hours")
    public void we_get_correct_hours() {
        assertEquals(this.customer.getHours(restaurant), restaurant.getHours());
    }
}
