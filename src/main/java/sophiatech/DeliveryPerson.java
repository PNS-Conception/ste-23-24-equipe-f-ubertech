package sophiatech;
import java.util.ArrayList;

public class DeliveryPerson {

    private String firstName;
    private String lastName;
    private ArrayList<Order> order;
    private boolean isAvailable;

    public DeliveryPerson(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.order = new ArrayList<>();
    }

    public void addOrder(Order o){
        this.order.add(o);
        this.isAvailable = false;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }
}