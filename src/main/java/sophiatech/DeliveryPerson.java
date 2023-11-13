package sophiatech;
import java.util.ArrayList;

public class DeliveryPerson {

    private System system;
    private String firstName;
    private String lastName;
    private GroupOrder activeOrder;
    private ArrayList<GroupOrder> orderHistory;
    private boolean isAvailable;

    public DeliveryPerson(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.activeOrder = new GroupOrder();
        this.orderHistory = new ArrayList<>();
        this.isAvailable = true;

        this.system = System.getInstance();
        this.system.addDeliveryPerson(this);
    }

    public void addOrder(GroupOrder o){
        this.orderHistory.add(o);
        this.activeOrder = o;
        this.isAvailable = false;
    }

    public boolean getIsAvailable(){
        return isAvailable;
    }

    public GroupOrder getActiveOrder() {
        return this.activeOrder;
    }
}