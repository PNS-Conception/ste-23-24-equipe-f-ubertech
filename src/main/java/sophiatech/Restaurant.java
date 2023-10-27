package sophiatech;


import java.util.ArrayList;
import java.util.Objects;

public class Restaurant {
    System system;
    ArrayList<Product> products;

    private String name;
    private String location;
    private ArrayList<Order> activeOrders;
    private ArrayList<Order> orderHistory;

    public Restaurant(String name, String location){
        this.name = name;
        this.location = location;

        this.system = System.getInstance();
        system.addRestaurant(this);

        this.products = new ArrayList<>();

        this.activeOrders = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public void addProduct(Product product) {
        if (product.getRestaurant() != this) {
            throw new RuntimeException("the product that you sent is not linked to this restaurant");
        }
        this.products.add(product);
    }

    public void addOrder(Order order) {
        this.activeOrders.add(order);
        this.orderHistory.add(order);
    }

    public ArrayList<Order> getActiveOrders() {
        return this.activeOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant restaurant = (Restaurant) o;
        return Objects.equals(name, restaurant.name) && Objects.equals(location, restaurant.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }
}