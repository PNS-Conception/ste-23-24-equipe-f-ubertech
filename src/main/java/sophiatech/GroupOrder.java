package sophiatech;

import java.time.LocalTime;
import java.util.ArrayList;

public class GroupOrder {
    public ArrayList<Order> orders = new ArrayList();
    private static int idCounter = 1; // Static counter for generating unique IDs
    private int id;
    private boolean isOpen;

    private LocalTime hour;


    public GroupOrder (ArrayList<Order> orders) {
        this.id = generateUniqueId();
        this.orders = orders;
        this.isOpen = false;

        if(!orders.isEmpty()) this.hour = orders.get(0).getHour();
        else this.hour = null;

    }
    public GroupOrder() {
        this(new ArrayList<>());
        this.hour = LocalTime.now();
    }

    public int getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    private synchronized int generateUniqueId() {
        return idCounter++;
    }

    public LocalTime getHour(){ return hour;}
}
