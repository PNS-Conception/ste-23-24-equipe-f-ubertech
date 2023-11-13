package sophiatech;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<Product> products;



    public Menu() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void editProduct(Product product) { return; }

    public void showMenu() {
        for (Product product : products) {
            java.lang.System.out.println("Name: " + product.getName() + " Price: " + product.getPrice());
        }
    }

    public List<Product> getProducts(){
        return products;
    }



}
