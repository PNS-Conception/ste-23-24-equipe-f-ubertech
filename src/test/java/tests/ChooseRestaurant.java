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
public class ChooseRestaurant {

    private Customer customer;
    private System system = new System();
    private Restaurant target;
    private Restaurant macdonalds1;
    private Restaurant macdonalds2;

    @Given("I am a simple customer")
    public void a_simple_customer(){
        customer = new Customer("Simon","Beurel", system,UserType.STUDENT);
    }

    @When("I search for a specific restaurant named McDonalds")
    public void search_macdonalds(){
        macdonalds1 = new Restaurant("Macdonalds","Loudéac",null);
        macdonalds2 = new Restaurant("Macdonalds", "Biot", null);
        system.addRestaurant(macdonalds1);
        system.addRestaurant(macdonalds2);
        target = customer.searchRestaurant("Macdonalds","Biot");
    }

    @Then("The system should return the restaurant McDonalds")
    public void check_if_good_result(){
        assertTrue(target.equals(macdonalds2));
        assertFalse(target.equals(macdonalds1));
    }
}
