package sophiatech.Order;

import sophiatech.AppUsers.Customer;
import sophiatech.Restaurant.Product;
import sophiatech.Restaurant.Restaurant;

import java.time.LocalTime;
import java.util.ArrayList;

public class OrderFactory {
    public static OrderComponent createOrder (Customer customer, String location, LocalTime hour, ArrayList<Product> productList) {
        Restaurant r = productList.get(0).getRestaurant();
        for (Product p : productList) {
            if (p.getRestaurant() != r) {
                return new MultipleOrder(customer, location, hour, productList);
            }
        }
        return new Order(customer, location, hour, productList);
    }

    public static GroupOrder createGroupOrder (ArrayList<OrderComponent> orders) {
        return new GroupOrder(orders);
    }
}
