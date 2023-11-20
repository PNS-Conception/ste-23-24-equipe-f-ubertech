package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;
import sophiatech.System;

import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import java.util.Date;

public class deliveryConfirmation {
    private DeliveryPerson deliveryPerson;
    private Customer customer;
    private Order order;
    private GroupOrder groupOrder;
    private System system= System.getInstance();

    @Given("a delivery person with an order")
    public void a_delivery_person() {
        deliveryPerson = new DeliveryPerson("Aziki", "Tarik");
        String location = "polytech Nice Sophia, ... Biot";
        ArrayList<Product> productList = new ArrayList<Product>();

        order = new Order(customer,location, LocalTime.now(), productList);
        groupOrder = new GroupOrder();
        groupOrder.orders.add(order);
        deliveryPerson.addOrder(groupOrder);

    }
    @When("they deliver the order")
    public void they_deliver_the_order() {

        deliveryPerson.validDelivery(groupOrder);
    }
    @Then("they can confirm the delivery on the app")
    public void they_can_confirm_the_delivery_on_the_app() {
        assertEquals(order.getStatus(), Status.DELIVERED);
    }
}
