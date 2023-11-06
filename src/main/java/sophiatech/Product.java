package sophiatech;

public class Product {
    private Restaurant restaurant;
    private String name;
    private int price;

    public Product(Restaurant restaurant, String name, int price) {
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;

        restaurant.addProduct(this);
    }

    public Product(String name, int price) {
        this(null, name, price);
    }

    public int getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}