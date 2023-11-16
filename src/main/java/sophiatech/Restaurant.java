package sophiatech;


import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Restaurant {
    System system;
    ArrayList<Product> products;

    private String name;
    private String location;
    private ArrayList<GroupOrder> activeOrders;
    private ArrayList<GroupOrder> orderHistory;
    private Hours hours;
    private int discountDuration;

    public Restaurant(String name, String location, Hours hours, int discountDuration) {
        this.name = name;
        this.location = location;
        this.hours = hours;

        this.system = System.getInstance();

        this.products = new ArrayList<>();

        this.activeOrders = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
        this.discountDuration = discountDuration;
    }

    public Hours getHours() {
        return this.hours;
    }

    public String getName() {
        return this.name;
    }

    public void addProduct(Product product) {
        if (product.getRestaurant() != this) {
            throw new RuntimeException("the product that you sent is not linked to this restaurant");
        }
        this.products.add(product);
    }

    public void addOrder(GroupOrder groupOrder) {
        acceptOrder(groupOrder);
        this.activeOrders.add(groupOrder);
        this.orderHistory.add(groupOrder);
    }

    public ArrayList<GroupOrder> getActiveOrders() {
        return this.activeOrders;
    }

    public void acceptOrder(GroupOrder groupOrder) {
        for (Order order : groupOrder.orders) {
            order.changeStatus(Status.IN_PREPARATION);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    public void denyOrder(GroupOrder groupOrder) {
        for (Order order : groupOrder.orders) {
            order.changeStatus(Status.CANCELED);
        }
    }

    public void finishOrder(GroupOrder groupOrder) {
        for (Order order : groupOrder.orders) {
            order.changeStatus(Status.PREPARED);
        }
    }
    public String getLocation(){
        return this.location;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Restaurant){
            Restaurant r = (Restaurant) obj;
            return r.getLocation().equals(getLocation()) && r.getName().equals(getName());
        }
        return false;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setHours(Hours hours) {
        this.hours = hours;
    }

    public long getDiscountDuration() {
        return discountDuration;
    }
}
