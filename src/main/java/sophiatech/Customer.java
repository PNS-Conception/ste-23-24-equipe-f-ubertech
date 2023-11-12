package sophiatech;

import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private System system;

    private String firstName;
    private String lastName;
    private String favouriteLocation;

    private ArrayList<GroupOrder> orderHistory;
    private ArrayList<GroupOrder> activeOrders;
    private ArrayList<Product> pendingOrder;


    public Customer(String fn, String ln){
        this.firstName = fn;
        this.lastName = ln;

        this.system = System.getInstance();
        this.pendingOrder = new ArrayList<>();
        this.activeOrders = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public String getFavouriteLocation() {
        return this.favouriteLocation;
    }


    public void addProductToPendingOrder(Product p) {
        if (pendingOrder.size() == 0) {
            this.pendingOrder.add(p);
            return;
        }
        //if all the orders are from the same restaurant
        if (pendingOrder.get(0).getRestaurant() == p.getRestaurant()) {
            this.pendingOrder.add(p);
        }
    }

    public int getSizePendingOrder(){
        return this.pendingOrder.size();
    }

    public Order payForOrder() {
        int total = 0;
        for (Product p : pendingOrder) {
            total += p.getPrice();
        }

        if ((this.system.getPaymentService().pay(total))) { //if payment is successfull

            Order order = new Order(this.favouriteLocation, new Date(), pendingOrder);
            GroupOrder groupOrder = new GroupOrder();
            groupOrder.orders.add(order);

            this.addOrder(groupOrder);

            this.pendingOrder.get(0).getRestaurant().addOrder(groupOrder);

            system.addGroupOrder(groupOrder);

            ArrayList<DeliveryPerson> availableDeliveryPersons = this.system.getAvailableDeliveryPerson();
            if (! availableDeliveryPersons.isEmpty())
                availableDeliveryPersons.get(0).addOrder(groupOrder);    //gives the order to the first available delivery person.
            else {
                system.addOrderWithoutDeliveryPerson(groupOrder);
            }



            this.pendingOrder = new ArrayList<>();  //flush the newly created order's products
            return order;
        }
        return null;
    }

    public void addOrder(GroupOrder order) {
        this.activeOrders.add(order);
        this.orderHistory.add(order);
    }

    public ArrayList<GroupOrder> getActiveOrders() {
        return this.activeOrders;
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

    public GroupOrder allowGroupOrder() {
        for (GroupOrder groupOrder : activeOrders) {
            groupOrder.setIsOpen(true);
        }
        return activeOrders.get(0);
    }
}