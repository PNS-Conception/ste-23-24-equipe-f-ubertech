package sophiatech;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;


public class Customer {
    private System system;

    private String firstName;
    private String lastName;

    private String favouriteLocation;

    private ArrayList<GroupOrder> orderHistory;

    private ArrayList<Product> pendingOrder;
    private GroupOrder activeOrder;
    private int delayCounter;
    private boolean isBanned;

    public Customer(String fn, String ln, System sys){
        this.firstName = fn;
        this.lastName = ln;

        this.system = sys;
        this.pendingOrder = new ArrayList<>();
        this.activeOrder = new GroupOrder();
        this.orderHistory = new ArrayList<>();
        this.delayCounter=3;
        this.isBanned=false;
    }

    public Customer(String fn, String ln){
        this.firstName = fn;
        this.lastName = ln;

        this.system = System.getInstance();
        this.pendingOrder = new ArrayList<>();
        this.activeOrder = new GroupOrder();
        this.orderHistory = new ArrayList<>();
        this.delayCounter=3;
        this.isBanned=false;
    }

    public String getFavouriteLocation() {
        return this.favouriteLocation;
    }

    public void setFavouriteLocation(String favouriteLocation) {
        this.favouriteLocation = favouriteLocation;
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
    public String getCustomerName(){
        return this.firstName + " " + this.lastName;
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
            Order order = new Order(this,this.favouriteLocation, new Date(), pendingOrder);
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
        this.activeOrder = order;
        this.orderHistory.add(order);
    }

    public GroupOrder getActiveOrder() {
        return this.activeOrder;
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
        this.activeOrder.setIsOpen(true);
        return activeOrder;
    }


    public void setHistory(ArrayList<GroupOrder> orderHistory){
        this.orderHistory = orderHistory;
    }

    public ArrayList<GroupOrder> getHistory(){
        return this.orderHistory;
    }

    public GroupOrder getOrderAtIndexHistory(int i){
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
    public void validDelivery(GroupOrder groupOrder){
        for (Order order : groupOrder.orders) {
            order.validateOrder();
            order.changeStatusValidation(Status.DELIVERY_CONFIRMED);
        }
        activeOrder = new GroupOrder();
    }

    public int getDelayCounter(){
        return delayCounter;
    }

    public void decrementerDelayCounter(){
        this.delayCounter--;
        if(this.delayCounter<=0){
            isBanned=true;
            this.delayCounter=0;
        }

    }

    public boolean isActive() {
        return !isBanned;
    }


}