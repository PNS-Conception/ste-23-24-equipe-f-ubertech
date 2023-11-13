package sophiatech;

public class RestaurantEmployee {
    private boolean isOwner;
    private String firstName;
    private String lastName;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    private Restaurant restaurant;

    public RestaurantEmployee(String fn, String ln, boolean isOwner, Restaurant r){
        this.firstName = fn;
        this.lastName = ln;
        this.isOwner = isOwner;
        this.restaurant = r;
    }


}
