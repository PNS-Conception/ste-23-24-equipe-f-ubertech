package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Customer;
import sophiatech.Product;
import sophiatech.Restaurant;
import static org.junit.Assert.*;

public class Ristourne {
    private Customer customer;
    private Restaurant restaurant;
    private Product product;

    @Given("a customer who has a discount in a restaurant")
    public void a_customer_who_has_a_discount_in_a_restaurant() {
        customer = new Customer("Beurel","Simon");
        customer.addDiscount(restaurant);
    }
    @When("they order in this restaurant")
    public void they_order_in_this_restaurant() {
        restaurant = new Restaurant("test restaurant", "test address", null,3);
        product = new Product(restaurant, "test burger", 7);
        customer.addProductToPendingOrder(product);
        restaurant.getDiscountDuration();
        customer.payForOrder();
    }
    @Then("the order is cheaper")
    public void the_order_is_cheaper() {
        assertTrue(product.getPrice()==7);
    }
}
