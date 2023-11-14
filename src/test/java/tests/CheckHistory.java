package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;
import sophiatech.System;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckHistory {

    private System system = new System();
    private Customer customer;
    private Order order;
    private GroupOrder groupOrder;
    private Order order2;
    private GroupOrder groupOrder2;

    @Given("A customer")
    public void a_customer() {
        customer = new Customer("Simon", "Beurel", system);
    }
    @When("I view my order history for food orders")
    public void view_history_food_orders() {

        order = new Order(customer,"Lausanne", new Date(), new ArrayList<Product>());
        groupOrder = new GroupOrder();
        groupOrder.orders.add(order);
        order2= new Order(customer,"Le petit manger", new Date(), new ArrayList<Product>());
        groupOrder2 = new GroupOrder();
        groupOrder2.orders.add(order2);
        ArrayList<GroupOrder> orderHistory = new ArrayList<>();
        orderHistory.add(groupOrder);
        orderHistory.add(groupOrder2);
        customer.setHistory(orderHistory);
    }
    @Then("I can keep track of my past orders and preferences")
    public void product_is_correctly_stored() {
        assertTrue(customer.getOrderAtIndexHistory(0).orders.get(0)==order);
        assertFalse(customer.getOrderAtIndexHistory(0).orders.get(0).getLocation().equals("Le petit manger"));
    }
}
