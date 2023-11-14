package sophiatech;
import java.util.ArrayList;
import java.util.Objects;

public class DeliveryPerson {

    private System system;

    public String getFirstName() {
        return firstName;
    }

    private String firstName;
    private String lastName;
    private GroupOrder activeOrder;
    private ArrayList<GroupOrder> orderHistory;
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


    public void validDelivery(GroupOrder groupOrderorder) {
        for (Order order : groupOrderorder.orders) {
            order.validateDelivery(Status.DELIVERED);
            order.changeStatusValidation(Status.DELIVERY_CONFIRMED);
        }
        this.isAvailable = true;
        activeOrder = new GroupOrder();

    }
}