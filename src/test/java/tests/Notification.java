package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;
import sophiatech.System;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class Notification {
    private DeliveryPerson deliveryPerson;
    private Customer customer;
    private Product product;
    private RestaurantEmployee restaurantEmployee;
    private sophiatech.System system = sophiatech.System.getInstance();


    @Given("a delivery person")
    public void a_delivery_person() {
        deliveryPerson = system.getAvailableDeliveryPerson().get(0);
    }
    @When("the order is marked as ready")
    public void the_order_is_marked_as_ready() {
        Restaurant restaurant = new Restaurant("test restaurant", "restaurant location", null);
        product = new Product(restaurant, "test burger", 7);

        customer = new Customer("test", "customer");
        customer.addProductToPendingOrder(product);
        restaurantEmployee = new RestaurantEmployee("Beurel", "Simon", false, null);
        customer.payForOrder();

    }
    @Then("they are no longer available")
    public void they_recieve_a_notification() {
        assertFalse(deliveryPerson.getIsAvailable());
    }
    @Then("they are able to read informations like : the venue, the customer's name, the order's id....")
    public void they_are_able_to_read_informations_like_the_venue_the_customer_s_name_the_order_s_id() {
        assertEquals(deliveryPerson.getActiveOrders().get(0).getLocation(), customer.getActiveOrders().get(0).getLocation());
    }

    @Given("a restaurantEmployee finishing an order")
    public void a_restaurant_employee_finishing_an_order() {
        Restaurant restaurant = new Restaurant("test restaurant", "restaurant location", null);
        product = new Product(restaurant, "test burger", 7);

        customer = new Customer("test", "customer");
        customer.addProductToPendingOrder(product);
        restaurantEmployee = new RestaurantEmployee("Beurel", "Simon", false, null);
        customer.payForOrder();
    }
    @When("they validate the end of the preparation of an order")
    public void they_validate_the_end_of_the_preparation_of_an_order() {
        restaurantEmployee.finishOrder(customer.getActiveOrders().get(0));
    }
    @Then("the deliveryPerson receive a notification")
    public void the_delivery_person_receive_a_notification() {
        assertTrue(customer.getActiveOrders().get(0).getStatus() == Status.PREPARED);
    }
}
