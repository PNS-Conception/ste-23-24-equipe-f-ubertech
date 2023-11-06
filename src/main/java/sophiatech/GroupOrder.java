package sophiatech;

import java.util.ArrayList;

public class GroupOrder {
    public ArrayList<Order> orders = new ArrayList();

    private int id;
    private boolean isOpen;


    public GroupOrder (ArrayList<Order> orders) {
        this.orders = orders;
        this.isOpen = false;

    }
    public GroupOrder() {
        this(new ArrayList<>());
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
}
