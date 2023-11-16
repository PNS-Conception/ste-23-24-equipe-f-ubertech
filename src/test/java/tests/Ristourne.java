package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Customer;
import sophiatech.Product;
import sophiatech.Restaurant;
import sophiatech.UserType;

import static org.junit.Assert.*;

public class Ristourne {
    private Customer customer;
    private Restaurant restaurant;
    private Product product;

    @Given("a customer who has a discount in a restaurant")
    public void a_customer_who_has_a_discount_in_a_restaurant() {
        customer = new Customer("Beurel","Simon", UserType.EXTERNAL);
        Restaurant restaurant1 = new Restaurant("test restaurant", "test address", null,3,7);
        Restaurant restaurant2 = new Restaurant("test restaurant", "test address", null,3,7);
        restaurant = new Restaurant("test restaurant", "test address", null,3,5);
        customer.addDiscount(restaurant1);
        customer.addDiscount(restaurant);
        customer.addDiscount(restaurant2);
    }
    @When("they order in this restaurant")
    public void they_order_in_this_restaurant() {
        product = new Product(restaurant, "test burger", 7);
        customer.addProductToPendingOrder(product);
        customer.payForOrder();
    }
    @Then("the order is cheaper")
    public void the_order_is_cheaper() {
        assertNotEquals(customer.getHistory().get(0).orders.get(0).getTotalPrice(), product.getPrice() , 0);
        assertEquals(customer.getHistory().get(0).orders.get(0).getTotalPrice(), product.getPrice() - product.getPrice() * 0.05, 0);
    }


}
