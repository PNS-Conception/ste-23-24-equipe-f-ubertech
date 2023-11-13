package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;
import sophiatech.System;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;

public class CustomerChecksHistory {
    ArrayList<GroupOrder> orderHistory;
    GroupOrder recentOrder;
    System system;
    Product product;
    Customer customer;
    Restaurant restaurant;
    DeliveryPerson deliveryPerson;
    PaymentService paymentService;
    Order order;
    CampusAdministrator campusAdministrator;
    @Given("A Customer who has ordered previously")
    public void a_customer_who_has_ordered_previously() {
        system = System.getInstance();
        system.getListDeliveryPerson().clear();
        system.getListGroupOrders().clear();
        system.getListCustomer().clear();
        system.getListRestaurant().clear();
        system.getOrdersPendingDeliveryPersons().clear();


        Hours h = new Hours(new Date(2021, 1, 1, 8, 0), new Date(2021, 1, 1, 20, 0));
        restaurant = new Restaurant("test restaurant", "restaurant location", h);

        campusAdministrator = new CampusAdministrator();
        campusAdministrator.addRestaurant(restaurant);

        product = new Product(restaurant, "test burger", 7);    //adds product to the corresponding restaurant in the constructor

        customer = new Customer("test", "customer");    //adds customer to the system in the constructor
        customer.addProductToPendingOrder(product);

        deliveryPerson = new DeliveryPerson("test", "delivery person"); //adds delivery person to the system in the constructor

        paymentService = system.getPaymentService();

        customer.payForOrder();
        recentOrder = customer.getActiveOrder();
    }
    @When("they check their history")
    public void they_check_their_history() {
        orderHistory = customer.getOrderHistory();
    }
    @Then("they can acces all the previous orders")
    public void they_can_acces_all_the_previous_orders() {
        assertFalse(orderHistory.isEmpty());
        assertTrue(orderHistory.contains(recentOrder));
    }
}
