package sophiatech;

import java.time.LocalTime;
import java.util.ArrayList;

public abstract class OrderComponent {
    protected int id;
    protected LocalTime hour_order;
    protected double totalPrice;
    protected Status status;
    protected boolean validationByDeliveryPerson;
    protected boolean validationByCustomer;


    public int getId(){return id;}
    public LocalTime getHour(){ return hour_order;}
    public abstract double calculateTotalPrice();
    public void changeStatus(Status st){
        this.status = st;
    }
    public void changeStatusValidation(Status st){
        if(this.validationByCustomer && this.validationByDeliveryPerson){
            this.status = st;
        }
    }
    public abstract void validDelivery();



}
