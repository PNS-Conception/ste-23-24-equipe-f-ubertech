package browse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.User;

import static org.junit.Assert.assertTrue;

public class testsN1 {
    private User u;

    @Given("a user using the application")
    public void a_user_using_the_application() {
        u = new User(1);
    }
    @When("they select a campus restaurant")
    public void they_select_a_campus_restaurant() {
        u.setIndex(5);
    }
    @Then("The application prints their menu")
    public void the_application_prints_their_menu() {
        assertTrue(5 == u.getIndex());
    }
}
