package sophiatech;

public class Product {
    private Restaurant restaurant;
    private String name;
    private double price;

    public Product(Restaurant restaurant, String name, double price) {
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;

        restaurant.addProduct(this);
    }

    public Product(String name, int price) {
        this(null, name, price);
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}