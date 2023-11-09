package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.DeliveryPerson;
import sophiatech.Order;
import sophiatech.Product;
import sophiatech.Status;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import java.util.Date;

public class deliveryConfirmation {
    private DeliveryPerson deliveryPerson;
    private Order order;
    @Given("a delivery person with an order")
    public void a_delivery_person() {
        deliveryPerson = new DeliveryPerson("Aziki", "Tarik");
        String location = "polytech Nice Sophia, ... Biot";
        Date date = new Date();
        ArrayList<Product> productList = new ArrayList<Product>();
        order = new Order(location, date, productList,"1");
        deliveryPerson.addOrder(order);
    }
    @When("they deliver the order")
    public void they_deliver_the_order() {

        deliveryPerson.validDelivery(order);
    }
    @Then("they can confirm the delivery on the app")
    public void they_can_confirm_the_delivery_on_the_app() {
        assertEquals(order.getStatus(), Status.DELIVERED);
    }
}
