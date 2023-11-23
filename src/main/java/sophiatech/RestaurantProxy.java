package sophiatech;

public class RestaurantProxy {
    private Restaurant restaurant;
    private RestaurantRealSubject restaurantRealSubject;

    public RestaurantProxy(RestaurantSubject restaurant) {
        this.restaurantRealSubject = (RestaurantRealSubject) restaurant;
    }

    public boolean checkAvailableSlot(java.time.LocalTime borne_inf, java.time.LocalTime borne_sup) {
        return restaurantRealSubject.checkAvailableSlot(borne_inf, borne_sup);
    }
}
