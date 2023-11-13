package sophiatech;
import java.util.ArrayList;

public class DeliveryPerson {

    private System system;
    private String firstName;
    private String lastName;
    private GroupOrder activeOrder;
    private ArrayList<GroupOrder> orderHistory;
    private boolean isAvailable;
    private Order currentOrder;

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

    public void assignOrder(Order order) {
        this.currentOrder = order;
        this.currentOrder.setExpectedDeliveryTime(calculateExpectedDeliveryTime()); // Set the expected delivery time
        java.lang.System.out.println("Order assigned to delivery person.");
    }

    // Calculate and set the expected delivery time
    private long calculateExpectedDeliveryTime() {
        long preparationTime = 30 * 60 * 1000; // Example: 30 minutes in milliseconds for preparation
        long currentTime = java.lang.System.currentTimeMillis(); // Get current time in milliseconds
        return currentTime + preparationTime;
    }

    // In this method, the delay is detected when current Time > expected Delivery Time
    public void observeUserDelay() {
        if (currentOrder != null) {
            long currentTime = java.lang.System.currentTimeMillis(); // Get current time in milliseconds
            long expectedDeliveryTime = currentOrder.getExpectedDeliveryTime(); // Fetch expected delivery time of the order

            if (currentTime > expectedDeliveryTime) {
                currentOrder.setDelayRecorded(true);
                java.lang.System.out.println("Delay observed for the assigned order.");
            }
        }
    }

    public void reportUserDelay(Order order) {
        if (order != null) {
            order.setDelayRecorded(true);
            java.lang.System.out.println("User delay reported and associated with the order.");
        }
    }
}