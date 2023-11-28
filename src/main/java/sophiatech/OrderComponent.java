package sophiatech;

import java.time.LocalTime;
import java.util.ArrayList;

public abstract class OrderComponent {
    protected int id;
    protected LocalTime hour_order;
    protected double totalPrice;


    public int getId(){
        return id;
    }
    public LocalTime getHour(){ return hour_order;}
    public abstract double calculateTotalPrice();


}
