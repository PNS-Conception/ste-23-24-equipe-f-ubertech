package sophiatech;
import java.util.ArrayList;

public class Customer {

    private String firstName;
    private String lastName;

    private ArrayList<Product> pendingOrder;
    private System system;


    public Customer(String fn, String ln, System sys){
        this.firstName = fn;
        this.lastName = ln;

        this.pendingOrder = new ArrayList<>();
        this.system = sys;
    }

    public void addProductToPendingOrder(Product p){
        this.pendingOrder.add(p);
    }

    public int getSizePendingOrder(){
        return this.pendingOrder.size();
    }

    public Hours getHours(Restaurant restaurant) {
        ArrayList<Restaurant> listRestaurant = this.system.getListRestaurant();
        for (Restaurant r : listRestaurant) {
            if (r.getName().equals(restaurant.getName())) {
                return r.getHours();
            }
        }
        return null;
    }
}