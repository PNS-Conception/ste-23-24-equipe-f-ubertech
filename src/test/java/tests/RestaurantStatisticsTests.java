package tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sophiatech.*;

import java.lang.System;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class RestaurantStatisticsTests {
    Restaurant restaurant;
    RestaurantStatistics restaurantStatistics;


    @Given("a restaurant who has not sold any order")
    public void a_restaurant_who_has_not_sold_any_order() {
        this.restaurant = new Restaurant("La bonne raclette", "43 rue du campus", null, 0, 0, 0, 5);
    }

    @Given("a restaurant a restaurant who receives an order with two products of price {double} and {double} and no discount")
    public void a_restaurant_a_restaurant_who_receives_an_order_with_two_products_of_price_and_and_no_discount(Double priceP1, Double priceP2) {
        this.restaurant = new Restaurant("La bonne raclette", "43 rue du campus", null, 0, 0, 0, 5);
        Product p1 = new Product(restaurant, "p1", priceP1);
        Product p2 = new Product(restaurant, "p2", priceP2);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(new Order("batiment F", LocalTime.now(), productList));
        GroupOrder go = new GroupOrder(orderList);

        this.restaurant.addOrder(go);
    }

    @Given("a restaurant a restaurant who receives an order with two products of price {double} and {double} and has discounts of {int}")
    public void a_restaurant_a_restaurant_who_receives_an_order_with_two_products_of_price_and_and_has_discounts_of(Double priceP1, Double priceP2, Integer discountValue) {
        this.restaurant = new Restaurant("La bonne raclette", "43 rue du campus", null, 5, discountValue, 0, 5);
        Product p1 = new Product(restaurant, "", priceP1);
        Product p2 = new Product(restaurant, "", priceP2);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);

        Customer customerWithDiscount = new Customer("user", "with discount", UserType.EXTERNAL);
        customerWithDiscount.addDiscount(restaurant);

        customerWithDiscount.addProductToPendingOrder(p1);
        customerWithDiscount.addProductToPendingOrder(p2);
        customerWithDiscount.payForOrder();
    }

    @Given("a restaurant a restaurant who receives an order with two products of price {double} and {double} and no discount but from a student")
    public void a_restaurant_a_restaurant_who_receives_an_order_with_two_products_of_price_and_and_no_discount_but_from_a_student(Double priceP1, Double priceP2) {
        this.restaurant = new Restaurant("La bonne raclette", "43 rue du campus", null, 0, 0, 0, 5);
        Product p1 = new Product(restaurant, "", priceP1);
        Product p2 = new Product(restaurant, "", priceP2);

        Customer customerStudent = new Customer("customer", "student", UserType.STUDENT);
        customerStudent.addProductToPendingOrder(p1);
        customerStudent.addProductToPendingOrder(p2);
        customerStudent.payForOrder();
    }

    @Given("a restaurant with products at price of {double} {double} {double} and {double}")
    public void aRestaurantWithProductsAtPriceOfAnd(Double priceP1, Double priceP2, double priceP3, double priceP4) {
        this.restaurant = new Restaurant("La bonne raclette", "43 rue du campus", null, 0, 0, 0, 5);
        Product p1 = new Product(this.restaurant, "p1", priceP1);
        Product p2 = new Product(this.restaurant, "p2", priceP2);
        Product p3 = new Product(this.restaurant, "p3", priceP3);
        Product p4 = new Product(this.restaurant, "p4", priceP4);
    }

    @When("they access their statistics")
    public void they_access_their_statistics() {
        this.restaurantStatistics = this.restaurant.getStatistics();
    }
    @Then("the total of orders is at {int}")
    public void the_total_of_orders_is_at(Integer int1) {
        assertEquals((int) int1, restaurantStatistics.getNumberOrders());
    }
    @Then("the total price for orders is at {double}")
    public void the_total_price_for_orders_is_at(Double double1) {
        assertEquals(double1, restaurantStatistics.getTotalPriceForOrders(), 0.001);
    }
    @Then("the average price for orders is at {double}")
    public void the_average_price_for_orders_is_at(Double double1) {
        assertEquals(double1, restaurantStatistics.getAveragePriceForOrder(), 0.001);
    }
    @Then("the total discounted amount is at {double}")
    public void the_total_discounted_amount_is_at(Double double1) {
        assertEquals(double1, restaurantStatistics.getTotalPriceDiscounted(), 0.001);
    }
    @Then("the total of products is {int}")
    public void theTotalOfProductsIs(int nbProducts) {
        assertEquals(nbProducts, restaurantStatistics.getNumberProducts());
    }
    @And("the average price for products is at {double}")
    public void theAveragePriceForProductsIsAt(Double double1) {
        assertEquals(double1, restaurantStatistics.getAveragePriceForProduct(), 0);
    }
}
