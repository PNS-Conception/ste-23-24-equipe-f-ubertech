package sophiatech;

import java.util.Date;
import java.util.ArrayList;
import java.util.Objects;

public class Order {

    private String location;
    private Date date;
    private ArrayList<Product> productList;
    private Status status;
    private boolean validationByDeliveryPerson;
    private boolean validationByCustomer;

    public Order(String location, Date date, ArrayList<Product> productList){
        this.location = location;
        this.date = date;
        this.productList = productList;
        this.status = Status.IN_PREPARATION;
        this.validationByDeliveryPerson = false;
        this.validationByCustomer = false;
    }

    public boolean isValidationByCustomer() {
        return validationByCustomer;
    }

    public void changeStatus(Status st){
        this.status = st;
    }
    public void changeStatusValidation(Status st){
        if(this.validationByCustomer && this.validationByDeliveryPerson){
            this.status = st;
        }

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
        boolean dateEquals = Math.abs(this.date.getTime() - otherOrder.date.getTime()) <= 60 * 60 * 1000; // 1 hour in milliseconds

        return locationEquals && productListEquals && statusEquals && dateEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, date, productList, status);
    }

    public void validateDelivery(Status status) {
        this.status = status;
        this.validationByDeliveryPerson = true;
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

    public Date getDate(){
        return this.date;
    }

    public ArrayList<Product> getProductList(){
        return this.productList;
    }
}