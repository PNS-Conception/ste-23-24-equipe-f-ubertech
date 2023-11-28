package tests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;

import java.lang.System;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SlotsRestaurant {
    private Restaurant restaurant;
    @Given("a Restaurant called {string}")
    public void a_restaurant_called(String arg0) {
        restaurant = new Restaurant(arg0, "Biot", null, 0, 0 , 0, 0);
    }

    @When("I specify my production's capacity")
    public void i_specify_my_production_s_capacity() {
        restaurant.editCapacity(1);
        //Now the restaurant got a capacity of 1 menus every 10 minutes
    }

    @Then("I cant accept more order than my production's capacity")
    public void i_cant_accept_more_order_than_my_production_s_capacity() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product(restaurant, "p", 4.5));
        Order o1 = new Order("Langast", LocalTime.now(), productList);
        ArrayList<Order> list = new ArrayList<>();
        list.add(o1);

        GroupOrder gp = new GroupOrder(list);

        //We add a first order to our slot
        restaurant.addOrder(gp);
        assertEquals(1, restaurant.getActiveOrders().size()); //Success because the restaurant cas make one order every 10 minutes

        restaurant.addOrder(gp);
        assertNotEquals(2, restaurant.getActiveOrders().size()); //False because the restaurant's capacity is empty

    }
}
