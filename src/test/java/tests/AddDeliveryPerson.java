package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.AppUsers.CampusAdministrator;
import sophiatech.AppUsers.DeliveryPerson;
import sophiatech.System;

import static org.junit.Assert.assertTrue;

public class AddDeliveryPerson {
    private CampusAdministrator CA ;
    private System System ;
    private sophiatech.AppUsers.DeliveryPerson DeliveryPerson;


    @Given("that I am a Campus Administrator")
    public void that_i_am_a_campus_administrator() {
        System = new System();
        CA = new CampusAdministrator(System);
    }
    @When("I add a Delivery Person to the System")
    public void i_add_a_delivery_person_to_the_system() {
        DeliveryPerson = new DeliveryPerson("Jean", "Dupont");
        CA.addDeliveryPerson(DeliveryPerson);
    }
    @Then("There is a new delivery person in the System's delivery person list")
    public void there_is_a_new_delivery_person_in_the_system_s_delivery_person_list() {
        assertTrue(System.getListDeliveryPerson().size()==1);
    }

}
