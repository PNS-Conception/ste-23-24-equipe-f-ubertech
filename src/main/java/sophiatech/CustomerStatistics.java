package sophiatech;

import java.util.ArrayList;
import java.util.Date;

public class CustomerStatistics {
    private Customer customer;
    private Date creationDate;
    private int numberOrders;
    private double totalPriceSpent;

    public CustomerStatistics (Customer customer) {
        this.customer = customer;
        this.creationDate = new Date();
        this.numberOrders = 0;
        this.totalPriceSpent = 0.0;
    }

    public double getAverageSpent() {
        if (numberOrders == 0)
            return 0;
        return this.totalPriceSpent / this.numberOrders;
    }

    public double getTotalPriceSpent() {
        return this.totalPriceSpent;
    }

    public int getNumberOrders() {
        return this.numberOrders;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public boolean addOrder(Order order) {  //returns true if the order corresponded to the customer, false otherwise
        if (order.getCustomer() == this.customer) {
            this.numberOrders++;
            this.totalPriceSpent += order.getTotalPrice();
            return true;
        }
        return false;
    }

    public boolean addGroupOrder(GroupOrder groupOrder) {   //returns true if at least one order corresponded to the customer, false otherwise
        Boolean anOrderWasAdded = false;
        for (Order order : groupOrder.orders) {
            boolean currentVal = this.addOrder(order);
            if (currentVal) anOrderWasAdded = true;
        }
        return anOrderWasAdded;
    }

    public void updateStats() {     //recalculation of the stats
        this.totalPriceSpent = 0;
        this.numberOrders = 0;

        ArrayList<GroupOrder> customerOrders = this.customer.getHistory();
        for (GroupOrder groupOrder : customerOrders) {
            this.addGroupOrder(groupOrder);
        }
    }


}
