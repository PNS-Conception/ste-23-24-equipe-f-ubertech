package sophiatech.Restaurant;

import java.text.Normalizer;
import java.util.List;

public class Formule extends Product{
    private List<Product> products;
    private int maxCapacity;


    public Formule(Restaurant restaurant, String name, double price, List<Product> products, int maxCapacity) {
        super(restaurant, name, price);
        this.products = products;
        this.maxCapacity = maxCapacity;
    }
    public Formule(List<Product> products) {
        super(products.get(0).getRestaurant(), products.get(0).getName(), products.get(0).getPrice());
        this.products = products;
        this.maxCapacity = products.size();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Product> getProducts() {
        return products;
    }
}
