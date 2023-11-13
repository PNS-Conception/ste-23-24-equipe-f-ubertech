package sophiatech;

import java.util.Objects;

public class Product {
    private Restaurant restaurant;
    private String name;
    private int price;

    public Product(Restaurant restaurant, String name, int price) {
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;

        if (restaurant != null) {
            restaurant.addProduct(this);
        }
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

    @Override
    public String toString() {
        return "Product \"" + this.name + "\" served by restaurant : \"" + this.restaurant + "\" for " + this.price + "€";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Product product = (Product) obj;
        return price == product.price &&
                Objects.equals(restaurant, product.restaurant) &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurant, name, price);
    }
}