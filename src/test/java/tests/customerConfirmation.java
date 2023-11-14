package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;
import sophiatech.System;

import java.util.ArrayList;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class customerConfirmation {
    private Order order;
    private GroupOrder groupOrder;
    private Customer customer;
    private DeliveryPerson deliveryPerson;
    private System system= System.getInstance();

    @Given("a customer waiting his order")
    public void a_customer_waiting_his_order() {
        customer = new Customer("Froment", "Lorenzo");
        deliveryPerson = new DeliveryPerson("Aziki", "Tarik");
        String location = "polytech Nice Sophia, ... Biot";
        Date date = new Date();
        ArrayList<Product> productList = new ArrayList<Product>();

        order = new Order(location, date, productList, customer);
        groupOrder = new GroupOrder();
        groupOrder.orders.add(order);
        customer.addOrder(groupOrder);
        deliveryPerson.addOrder(groupOrder);

    }
    @When("they receive the order")
    public void they_receive_the_order() {
        customer.validDelivery(groupOrder);

    }
    @When("the driver has confirmed delivery")
    public void the_driver_has_confirmed_delivery() {
        deliveryPerson.validDelivery(groupOrder);
    }
    @Then("the customer can confirm the delivery via the application")
    public void the_customer_can_confirm_the_delivery_via_the_application() {
        assertTrue(order.isValidationByCustomer());
    }
    @Then("the order status is updated to DELIVERY_CONFIRMED")
    public void the_order_status_is_updated_to() {
        assertEquals(order.getStatus(), Status.DELIVERY_CONFIRMED);

    }


}