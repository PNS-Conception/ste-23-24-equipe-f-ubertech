package sophiatech;
import java.util.ArrayList;

public class DeliveryPerson {

    private System system;
    private String firstName;
    private String lastName;
    private ArrayList<Order> activeOrders;
    private ArrayList<Order> orderHistory;
    private boolean isAvailable;

    public DeliveryPerson(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.activeOrders = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        this.isAvailable = true;

        this.system = System.getInstance();
        this.system.addDeliveryPerson(this);
    }

    public void addOrder(Order o){
        this.orderHistory.add(o);
        this.activeOrders.add(o);
        this.isAvailable = false;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public ArrayList<Order> getActiveOrders() {
        return this.activeOrders;
    }
}