package sophiatech;

import java.time.LocalTime;

public class RestaurantRealSubject implements RestaurantSubject{
    private Restaurant restaurant;

    public RestaurantRealSubject(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean checkAvailableSlot(LocalTime borne_inf, LocalTime borne_sup) {
        int slot_capacity = restaurant.getCapacity();
        for (GroupOrder go : restaurant.getActiveOrders()) {
            if (go.getHour().isAfter(borne_inf) && go.getHour().isBefore(borne_sup)) {
                slot_capacity--;
            }
        }
        java.lang.System.out.print("LA CAPACITE EST DE : " + slot_capacity);
        return slot_capacity > 0;

    }
}
