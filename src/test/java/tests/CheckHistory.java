package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Customer;
import sophiatech.Order;
import sophiatech.Product;
import sophiatech.System;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckHistory {

    private System system = new System();
    private Customer customer;
    private Order order;
    private Order order2;

    @Given("A customer")
    public void a_customer() {
        customer = new Customer("Simon", "Beurel", system);
    }
    @When("I view my order history for food orders")
    public void view_history_food_orders() {
        order = new Order("Lausanne", new Date(), new ArrayList<Product>(),"1");
        order2= new Order("Le petit manger", new Date(), new ArrayList<Product>(), "2");
        ArrayList<Order> orderHistory = new ArrayList<>();
        orderHistory.add(order);
        orderHistory.add(order2);
        customer.setHistory(orderHistory);
    }
    @Then("I can keep track of my past orders and preferences")
    public void product_is_correctly_stored() {
        assertTrue(customer.getOrderAtIndexHistory(0)==order);
        assertFalse(customer.getOrderAtIndexHistory(0).getLocation().equals("Le petit manger"));
    }
}
