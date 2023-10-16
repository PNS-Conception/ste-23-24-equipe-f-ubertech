package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class AddRestaurant {
    private CampusAdministrator campusAdministrator;
    private Restaurant restaurant;

    @Given("a CampusAdministrator")
    public void a_customer() {
        customer = new Customer("Beurel","Simon");
    }
    @When("I add a new restaurant to the System")
    public void add_product_to_my_order() {
        // XXXX
    }
    @Then("There is a new restaurant in the System's restaurant list")
    public void product_is_correctly_stored() {
        assertTrue(deliveryPerson.getIsAvailable());
    }
}
