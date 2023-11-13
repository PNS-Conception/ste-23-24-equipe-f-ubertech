package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Notification {
    private DeliveryPerson deliveryPerson;
    private Order order;
    private GroupOrder groupOrder;

    @Given("a delivery person")
    public void a_delivery_person() {
        deliveryPerson = new DeliveryPerson("Aziki", "Tarik");
    }
    @When("the order is marked as ready")
    public void the_order_is_marked_as_ready() {
        String location = "polytech Nice Sophia, ... Biot";
        Date date = new Date();
        ArrayList<Product> productList = new ArrayList<Product>();
        order = new Order(location, date, productList);
        groupOrder = new GroupOrder();
        groupOrder.orders.add(order);

        deliveryPerson.addOrder(groupOrder);

        //simulates that restaurant staff completes the order
        order.changeStatus(Status.PREPARED);
    }
    @Then("they recieve a notification")
    public void they_recieve_a_notification() {
        assertFalse(deliveryPerson.getIsAvailable());
    }
}
