package sophiatech;
import java.util.ArrayList;
import java.util.Objects;

public class DeliveryPerson {

    private System system;
    private String firstName;
    private String lastName;
    private ArrayList<Order> activeOrders;
    private ArrayList<Order> orderHistory;
    private boolean isAvailable;

    @Override
    public boolean equals(Object obj){
        if(obj instanceof DeliveryPerson){
            DeliveryPerson dp = (DeliveryPerson) obj;
            return dp.firstName.equals(firstName) && dp.lastName.equals(lastName);
        }
        return false;
    }

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


    public void validDelivery(Order order) {
        order.validateDelivery(Status.DELIVERED);
        order.changeStatusValidation(Status.DELIVERY_CONFIRMED);
        this.isAvailable = true;
        activeOrders.remove(order);

    }
}