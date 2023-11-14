package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import sophiatech.*;
import sophiatech.System;
import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.Date;

public class RestaurantConfirmation {
    private System system;
    private Restaurant restaurant;
    private Product product;
    private Customer customer;
    private DeliveryPerson deliveryPerson;
    private PaymentService paymentService;
    private CampusAdministrator campusAdministrator;


    @Given("a restaurant which can accept an order")
    public void a_restaurant_which_can_accept_an_order() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        Hours h = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        restaurant = new Restaurant("restaurant acceptation", "3 rue du campus", h);

        campusAdministrator = new CampusAdministrator();
        campusAdministrator.addRestaurant(restaurant);

        product = new Product(restaurant, "sushi", 14);

        customer = new Customer("Sushi Enjoyer", "customer");
        customer.addProductToPendingOrder(product);

        deliveryPerson = new DeliveryPerson("sushi enjoyer", "delivery person"); //adds delivery person to the system in the constructor

        paymentService = system.getPaymentService();
    }
    @When("it recieves an order")
    public void it_recieves_an_order() {
        customer.payForOrder();
        assertFalse(restaurant.getActiveOrders().isEmpty());    //activeOrders not empty
    }
    @Then("it will accept it")
    public void it_will_accept_it() {
        restaurant.acceptOrder(restaurant.getActiveOrders().get(0));
    }
    @Then("the order will have status IN_PREPARATION")
    public void the_order_will_have_status_in_preparation() {
        assertEquals(Status.IN_PREPARATION, restaurant.getActiveOrders().get(0).orders.get(0).getStatus());
    }

    @When("a restaurant which cannot accept an order")
    public void a_restaurant_which_cannot_accept_an_order() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();

        Hours h = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        restaurant = new Restaurant("restaurant not acceptation", "7 rue du campus", h);

        campusAdministrator = new CampusAdministrator();
        campusAdministrator.addRestaurant(restaurant);

        product = new Product(restaurant, "ramen", 14);

        customer = new Customer("Ramen Enjoyer", "customer");
        customer.addProductToPendingOrder(product);

        deliveryPerson = new DeliveryPerson("ramen enjoyer", "delivery person"); //adds delivery person to the system in the constructor

        paymentService = system.getPaymentService();
    }
    @When("it recieves a new order")
    public void it_recieves_a_new_order() {
        customer.payForOrder();
        assertFalse(restaurant.getActiveOrders().isEmpty());    //activeOrders not empty
    }
    @Then("it will deny it")
    public void it_will_deny_it() {
        restaurant.denyOrder(restaurant.getActiveOrders().get(0));
    }
    @Then("the order will have status DENIED")
    public void the_order_will_have_status_denied() {
        assertEquals(Status.CANCELED, restaurant.getActiveOrders().get(0).orders.get(0).getStatus());

    }
}
