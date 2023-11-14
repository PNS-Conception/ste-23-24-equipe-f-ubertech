package sophiatech;

public class RestaurantEmployee {
    private boolean isOwner;
    private String firstName;
    private String lastName;
    private Restaurant restaurant;


    public Restaurant getRestaurant() {
        return restaurant;
    }

    public RestaurantEmployee(String fn, String ln, boolean isOwner, Restaurant r){
        this.firstName = fn;
        this.lastName = ln;
        this.isOwner = isOwner;
        this.restaurant = r;
    }

    public void finishOrder(Order order) {
        order.changeStatus(Status.PREPARED);

    }


}
