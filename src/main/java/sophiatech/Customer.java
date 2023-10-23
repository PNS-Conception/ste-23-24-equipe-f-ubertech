package sophiatech;
import java.util.ArrayList;

public class Customer {

    private String firstName;
    private String lastName;

    private ArrayList<Product> pendingOrder;
    private ArrayList<Order> orderHistory;
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

    public void setHistory(ArrayList<Order> orderHistory){
        this.orderHistory = orderHistory;
    }

    public ArrayList<Order> getHistory(){
        return this.orderHistory;
    }

    public Order getOrderAtIndexHistory(int i){
        return this.orderHistory.get(i);
    }

    public Restaurant searchRestaurant(String name, String location){
        ArrayList<Restaurant> listRestaurant = this.system.getListRestaurant();
        ArrayList<Restaurant> potentialTarget = new ArrayList<>();
        for(Restaurant r: listRestaurant){
            if(r.getName().equals(name)) potentialTarget.add(r);
        }
        if(potentialTarget.size()==1){
            return potentialTarget.get(0);
        }else{
            for(Restaurant r: potentialTarget){
                if(r.getLocation().equals(location)) return r;
            }
            return null;
        }
    }
}