package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportaLateUser {
    private DeliveryPerson deliveryPerson;
    private Order order;
    private Customer customer;
    private ArrayList<Product> products;

    @Given("a delivery person observes a delay by a user for an order")
    public void a_delivery_person_observes_a_delay_by_a_user_for_an_order() {
        deliveryPerson = new DeliveryPerson("Ivan","Ridier");
        customer = new Customer("Sara", "Dahman");
        products = new ArrayList<>();
        products.add(new Product("Tajine", 30));
        order = new Order(customer,"55 Avenue de Cannes", new Date(),products);
        // Assign the order to the delivery person
        deliveryPerson.assignOrder(order);
        // Simulate a delay observed by the delivery person
        deliveryPerson.observeUserDelay();
    }
    @When("the delivery person reports the delay in the system")
    public void the_delivery_person_reports_the_delay_in_the_system() {
        // Write code here that turns the phrase above into concrete actions
        deliveryPerson.reportUserDelay(order);
    }
    @Then("the delay is recorded and associated with the relevant order.")
    public void the_delay_is_recorded_and_associated_with_the_relevant_order() {
        // Write code here that turns the phrase above into concrete actions
        assertTrue(order.isDelayRecorded());
    }
}
