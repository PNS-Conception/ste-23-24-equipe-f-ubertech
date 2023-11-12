package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import sophiatech.*;
import sophiatech.System;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class PayForAnOrder {
    System system;
    Product product;
    Customer customer;
    Restaurant restaurant;
    DeliveryPerson deliveryPerson;
    PaymentService paymentService;
    Order order;
    CampusAdministrator campusAdministrator;

    @Given("a customer with a completed product list")
    public void a_customer_with_a_completed_product_list() {
        system = new System();

        Hours hours = new Hours(LocalTime.of(9,30), LocalTime.of(23,45));
        restaurant = new Restaurant("test restaurant", "restaurant location", hours);

        campusAdministrator = new CampusAdministrator(system);
        campusAdministrator.addRestaurant(restaurant);

        product = new Product(restaurant, "test burger", 7);    //adds product to the corresponding restaurant in the constructor

        customer = new Customer("test", "customer",system);    //adds customer to the system in the constructor
        customer.addProductToPendingOrder(product);

        deliveryPerson = new DeliveryPerson("test", "delivery person"); //adds delivery person to the system in the constructor
        system.addDeliveryPerson(deliveryPerson);

        paymentService = system.getPaymentService();
    }

    @When("they want to pay")
    public void they_want_to_pay() {
        assertTrue(customer.payForOrder() != null);
    }

    @Then("the corresponding order is successfully created")
    public void the_corresponding_order_is_successfully_created() {

    }

    @Then("the created order is assigned to the customer")
    public void the_created_order_is_assigned_to_the_customer() {
        ArrayList<Order> validationOrders = customer.getActiveOrders();
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        assertTrue(validationOrders.contains(new Order(customer.getFavouriteLocation(), new Date(), products)));    //needs Order.equals() to be modified
    }

    @Then("the created order is assigned to the restaurant")
    public void the_created_order_is_assigned_to_the_restaurant() {
        ArrayList<Order> validationOrders = restaurant.getActiveOrders();
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        assertTrue(validationOrders.contains(new Order(customer.getFavouriteLocation(), new Date(), products)));    //needs Order.equals() to be modified
    }

    @Then("the created order is assigned to the delivery person")
    public void the_created_order_is_assigned_to_the_delivery_person() {
        ArrayList<Order> validationOrders = deliveryPerson.getActiveOrders();
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        java.lang.System.out.println(system.getListDeliveryPerson().get(0).getIsAvailable());
        java.lang.System.out.println(validationOrders);
        assertTrue(validationOrders.contains(new Order(customer.getFavouriteLocation(), new Date(), products)));    //needs Order.equals() to be modified
        assertFalse(deliveryPerson.getIsAvailable());
    }
}
