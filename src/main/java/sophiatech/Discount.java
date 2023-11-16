package sophiatech;

import java.util.Date;

public class Discount {
    private Restaurant restaurant;
    private int percentage;
    private Date expirationDate;

    public Discount(Restaurant restaurant, int percentage, Date expirationDate) {
        this.restaurant = restaurant;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

}
