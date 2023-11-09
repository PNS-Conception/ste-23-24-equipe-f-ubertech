package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;
import sophiatech.System;

import java.util.Date;

public class AddEmployeeRestaurant {

    private CampusAdministrator CA ;
    private System System ;
    private Restaurant Restau;
    private EmployeeRestaurant ER;




    @Given("that I am a Campus Administrator")
    public void that_i_am_a_campus_administrator() {
        System = new System();
        CA = new CampusAdministrator(System);
        Restau = new Restaurant("Le Cosi", "Rue du Suquet, Cannes", new Hours(new Date(2021, 1, 1, 8, 0), new Date(2021, 1, 1, 20, 0)));

    }


    @When("I want to add a restaurant employee to the system")
    public void i_want_to_add_a_restaurant_employee_to_the_system() {
        ER = new EmployeeRestaurant("Tarik", "AZIKI");
        CA.addrestauEmployee(ER);
    }


    @Then("I have to access to the admin's different features")
    public void i_have_to_access_to_the_admin_s_different_features() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Then("I can have access to an admin's different features")
    public void i_can_have_access_to_an_admin_s_different_features() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Then("I'll have to fill in the form with the correct informations about the person; like the name, adresse...")
    public void i_ll_have_to_fill_in_the_form_with_the_correct_informations_about_the_person_like_the_name_adresse() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
