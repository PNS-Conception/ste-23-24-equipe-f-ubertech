package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Customer;
import sophiatech.Product;
import sophiatech.System;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class AddProduct {
    private Product product;
    private Customer customer;
    private System system = new System();

    @Given("a customer")
    public void a_customer() {
        customer = new Customer("Beurel","Simon", system);
    }
    @When("I add a product to my pendingOrder")
    public void add_product_to_my_order() {
        product = new Product("Fraises");
        customer.addProductToPendingOrder(product);
    }
    @Then("the product is stored in my pendingOrder")
    public void product_is_correctly_stored() {
        assertTrue(customer.getSizePendingOrder()==1);
    }
}
