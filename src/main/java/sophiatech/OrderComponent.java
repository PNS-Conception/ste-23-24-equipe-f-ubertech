package sophiatech;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderComponent {
    protected int id;
    protected LocalTime hour_order;
    protected double totalPrice;
    protected Status status;
    protected boolean validationByDeliveryPerson;
    protected boolean validationByCustomer;
    protected Customer customer;
    protected String location;
    protected boolean isAlreadyUsedForDiscount;


    public abstract double calculateTotalPrice();
    public abstract Restaurant getRestaurant();
    public abstract List<Product> getProductList();
    public abstract void validDelivery();


    public int getId(){return id;}
    public LocalTime getHour(){ return hour_order;}

    public Customer getCustomer() {
        return customer;
    }

    public void changeStatus(Status st){
        this.status = st;
    }
    public void changeStatusValidation(Status st){
        if(this.validationByCustomer && this.validationByDeliveryPerson){
            this.status = st;
        }
    }



    public void validateDelivery(Status status) {
        this.status = status;
        this.validationByDeliveryPerson = true;
        customer.incrementOrderNotDelayed();
    }

    public String getLocation(){
        return this.location;
    }

    public Status getStatus() {
        return this.status;
    }

    public void validateOrder() {
        this.validationByCustomer = true;
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }



    // Ces deux prochaines méthodes à revoir avec Lorenzo
    public boolean isAlreadyUsedForDiscount() {
        return isAlreadyUsedForDiscount;
    }

    public void setAlreadyUsedForDiscount(boolean alreadyUsedForDiscount) {
        isAlreadyUsedForDiscount = alreadyUsedForDiscount;
    }




}
