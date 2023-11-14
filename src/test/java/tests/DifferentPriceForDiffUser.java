package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.Customer;
import sophiatech.Product;
import sophiatech.Restaurant;
import sophiatech.UserType;
import static org.junit.Assert.*;



public class DifferentPriceForDiffUser {
    private Customer customer;
    private Restaurant restaurant;
    private Product product;
    @Given("a customer student")
    public void a_customer_student() {
        customer = new Customer("Simon", "Beurel", UserType.STUDENT);
        restaurant = new Restaurant("test restaurant", "test address", null);
        product = new Product(restaurant, "test burger", 7);
        customer.addProductToPendingOrder(product);

    }
    @Given("a customer faculty person")
    public void a_customer_faculty_person() {
        customer = new Customer("Simon", "Beurel", UserType.FACULTY);
        restaurant = new Restaurant("test restaurant", "test address", null);
        product = new Product(restaurant, "test burger", 7);
        customer.addProductToPendingOrder(product);

    }

    @Given("a customer staff person")
    public void a_customer_staff_person() {
        customer = new Customer("Simon", "Beurel", UserType.STAFF);
        restaurant = new Restaurant("test restaurant", "test address", null);
        product = new Product(restaurant, "test burger", 7);
        customer.addProductToPendingOrder(product);

    }
    @When("they pay their order")
    public void they_pay_their_order() {
        customer.payForOrder();
    }
    @Then("they get the student discount")
    public void they_get_the_student_discount() {
        assertEquals(customer.getHistory().get(0).orders.get(0).getTotalPrice(),product.getPrice()-product.getPrice()*0.05,0);

    }
    @Then("they get faculty discount")
    public void they_get_faculty_discount() {
        assertEquals(customer.getHistory().get(0).orders.get(0).getTotalPrice(),product.getPrice()-product.getPrice()*0.03,0);

    }
    @Then("they get staff discount")
    public void they_get_staff_discount() {
        assertEquals(customer.getHistory().get(0).orders.get(0).getTotalPrice(),product.getPrice()-product.getPrice()*0.04  ,0);

    }


}