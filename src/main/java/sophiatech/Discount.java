package sophiatech;

import java.time.LocalDate;
import java.util.Date;

public class Discount {
    private Restaurant restaurant;
    private int percentage;
    private LocalDate expirationDate;

    public Discount(Restaurant restaurant, int percentage, LocalDate expirationDate) {
        this.restaurant = restaurant;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getPercentage() {
        return percentage;
    }
}
