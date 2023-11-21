package sophiatech;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;


public class Customer {
    private System system;

    private String firstName;
    private String lastName;
    private UserType userType;

    private String favouriteLocation;

    private ArrayList<Discount> discounts;

    private ArrayList<GroupOrder> orderHistory;

    private ArrayList<Product> pendingOrder;

    private GroupOrder activeOrder;
    private int delayCounter;
    private boolean isBanned;
    public Customer(String fn, String ln, System sys, UserType userType){
        this.firstName = fn;
        this.lastName = ln;
        this.userType = userType;
        this.system = sys;
        this.pendingOrder = new ArrayList<>();
        this.activeOrder = new GroupOrder();
        this.orderHistory = new ArrayList<>();
        this.delayCounter=3;
        this.isBanned=false;
        discounts = new ArrayList<>();
    }

    public Customer(String fn, String ln, UserType userType){
        this.firstName = fn;
        this.lastName = ln;
        this.userType = userType;
        this.system = System.getInstance();
        this.pendingOrder = new ArrayList<>();
        this.activeOrder = new GroupOrder();
        this.orderHistory = new ArrayList<>();
        this.delayCounter=3;
        this.isBanned=false;
        discounts = new ArrayList<>();
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
        return this.payForOrder(this.favouriteLocation);
    }

    public Order payForOrder(String location) {
        double total = 0;
        for (Product p : pendingOrder) {
            total += p.getPrice();
        }
        switch (this.userType) {
            case STUDENT:
                total =total - total* 0.05;
                break;
            case FACULTY:
                total =total - total* 0.03;
                break;
            case STAFF:
                total = total - total* 0.04;
                break;
        }
        for (Discount d : discounts) {
            if (d.getExpirationDate().isBefore(LocalDate.now().plusDays(1))){
                discounts.remove(d);
                if(discounts.size()==0) break;
            } else if (d.getRestaurant() == pendingOrder.get(0).getRestaurant()) {
                java.lang.System.out.println("Discount applied: "+total);
                total = total - total * d.getPercentage() / 100;
                java.lang.System.out.println("Discount applied: "+total);
            }
        }

        double discountV1 = pendingOrder.get(0).getRestaurant().getCustomerDiscountV1(this);
        java.lang.System.out.println("DiscountV1 = " + discountV1);
        total = total - total * discountV1;

        if ((this.system.getPaymentService().pay(total))) { //if payment is successfull
            Order order = new Order(this, location, LocalTime.now(), pendingOrder);
            GroupOrder groupOrder = new GroupOrder();
            groupOrder.orders.add(order);
            order.setTotalPrice(total);


            this.addOrder(groupOrder);
            checkEligibleToDiscount(pendingOrder.get(0).getRestaurant());
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

    private void checkEligibleToDiscount(Restaurant restaurant) {
        int counter=0;
        for(GroupOrder o: this.orderHistory){
            if(o.orders.get(0).getRestaurant()==restaurant && !(o.orders.get(0).isAlreadyUsedForDiscount())){
                counter++;
                if(counter==restaurant.getStreakForDiscount()){
                    for(GroupOrder or: this.orderHistory) {
                        if (or.orders.get(0).getRestaurant() == restaurant && !(or.orders.get(0).isAlreadyUsedForDiscount())) {
                            or.orders.get(0).setAlreadyUsedForDiscount(true);
                        }
                    }
                    this.addDiscount(restaurant);
                    break;
                }
            }
        }
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

    public UserType getUserType() {
        return userType;
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

    public void addDiscount(Restaurant restaurant) {
        long discountDuration = restaurant.getDiscountDuration();
        LocalDate currentDate = LocalDate.now();
        LocalDate expirationDate = currentDate.plusDays(discountDuration);

        // Ajout du nouveau discount à la liste
        discounts.add(new Discount(restaurant, restaurant.getDiscount(), expirationDate));
    }


    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }
}