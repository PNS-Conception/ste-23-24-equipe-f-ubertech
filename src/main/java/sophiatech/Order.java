package sophiatech;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Order extends OrderComponent {

    public String location;
    private Customer customer;
    private ArrayList<Product> productList;
    private boolean delayRecorded;
    private long expectedDeliveryTime;

    private boolean isAlreadyUsedForDiscount;


    public Order(String location, LocalTime hour, ArrayList<Product> productList){
        this.location = location;
        this.hour_order = hour;
        this.productList = productList;
        this.status = Status.PENDING_PREPARATION;
        this.validationByDeliveryPerson = false;
        this.validationByCustomer = false;
        this.isAlreadyUsedForDiscount = false;
        this.delayRecorded=false;
    }

    public Order(Customer customer, String location, LocalTime hour, ArrayList<Product> productList){
        this(location, hour, productList);
        this.customer = customer;
        this.id = generateUniqueId();
        this.customer = customer;
        this.isAlreadyUsedForDiscount = false;
        calculateTotalPrice();









    }
    private int generateUniqueId() {
        String uuidString = UUID.randomUUID().toString();
        int hashCode = uuidString.hashCode();
        return Math.abs(hashCode);
      //  return UUID.randomUUID().toString();
    }

    public Order(Customer customer, String location, LocalTime hour){
        this.location = location;
        this.hour_order = hour;
        this.customer = customer;
        this.status = Status.PENDING_PREPARATION;
        this.validationByDeliveryPerson = false;
        this.validationByCustomer = false;
        this.isAlreadyUsedForDiscount = false;
        this.delayRecorded=false;
        this.totalPrice = 0.0;
        this.productList = new ArrayList<>();
    }

    /*private String generateUniqueId() {
        return UUID.randomUUID().toString();
    }*/
  
    public double calculateTotalPrice(){
        double total = 0;
        for(Product p: productList){
            total += p.getPrice();
        }
        totalPrice= total;
        return totalPrice;
    }

    @Override
    public void validDelivery() {
        validateDelivery(Status.DELIVERED);
        changeStatusValidation(Status.DELIVERY_CONFIRMED);
    }

    public boolean isValidationByCustomer() {
        return validationByCustomer;
    }


    public Customer getCustomer() {
        return customer;
    }


    //TODO Ne pas oublier de faire une machine a états traitant les cas légaux de changement d'état d'une order.

    @Override
    public boolean equals(Object obj) { //equals if everything is equals except for date. If date changes by an hour then it is considered the same order
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Order otherOrder = (Order) obj;

        // Compare location and productList
        boolean locationEquals = Objects.equals(this.location, otherOrder.location);
        boolean productListEquals = Objects.equals(this.productList, otherOrder.productList);

        // Compare status
        boolean statusEquals = this.status == otherOrder.status;

        // Compare dates, considering an hour difference
        boolean dateEquals = this.hour_order.equals(otherOrder.getHour());

        return locationEquals && productListEquals && statusEquals && dateEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, hour_order, productList, status);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("Ordered on " + hour_order + " to be delivered at " + location + ". contains : \n[\n");
        for (Product product : productList) {
            description.append("\t").append(product).append("\n");
        }
        description.append("]");
        description.append(status);
        return description.toString();
    }

    public void validateDelivery(Status status) {
        this.status = status;
        this.validationByDeliveryPerson = true;
        customer.incrementOrderNotDelayed();
    }
    public void validateOrder() {
        this.validationByCustomer = true;
    }
    public Status getStatus() {
        return this.status;
    }

    public String getLocation(){
        return this.location;
    }

    public void setDelayRecorded(boolean delayRecorded) {
        this.delayRecorded = delayRecorded;
    }
    public ArrayList<Product> getProductList(){
        return this.productList;
    }

    public void setTotalPrice(double total) {
        this.totalPrice = total;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }
    public long getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }
    public void setExpectedDeliveryTime(long expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }
    public Restaurant getRestaurant() {
        return this.productList.get(0).getRestaurant();
    }

    public boolean isAlreadyUsedForDiscount() {
        return isAlreadyUsedForDiscount;
    }

    public void setAlreadyUsedForDiscount(boolean alreadyUsedForDiscount) {
        isAlreadyUsedForDiscount = alreadyUsedForDiscount;
    }

    public void addProduct(Product product){
        this.productList.add(product);
        this.totalPrice+=product.getPrice();
    }

    public void addPrice(double priceToAdd){
        this.totalPrice+=priceToAdd;
        BigDecimal bd = new BigDecimal(this.totalPrice);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.totalPrice = bd.doubleValue();
    }
    public void setPrice(double price){
        this.totalPrice=price;
        BigDecimal bd = new BigDecimal(this.totalPrice);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        this.totalPrice = bd.doubleValue();
    }


}