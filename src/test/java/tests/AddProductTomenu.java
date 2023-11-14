package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Hours;
import sophiatech.Product;
import sophiatech.Restaurant;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddProductTomenu {

    private Restaurant restaurant;
    private Product newProduct;

    @Given("the restaurant manager is logged into the system")
    public void the_restaurant_manager_is_logged_into_the_system() {
        restaurant = new Restaurant("Calade Rooftop", "Nice", new Hours(new Date(),new Date()));
    }
    @When("I add a new product with required details")
    public void i_add_a_new_product_with_required_details() {
        newProduct = new Product(restaurant,"Couscous",10.99);
        restaurant.addProductToMenu(newProduct);
    }
    @Then("the new product is successfully added to the menu and is visible to customers.")
    public void the_new_product_is_successfully_added_to_the_menu_and_is_visible_to_customers() {
        assertTrue(restaurant.getMenu().contains(newProduct));
    }


}
