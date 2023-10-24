package sophiatech;
import java.util.ArrayList;

public class System {

    private ArrayList<Customer> listCustomer;
    private ArrayList<Restaurant> listRestaurant;
    private ArrayList<DeliveryPerson> listDeliveryPerson;


    public System(){
        this.listCustomer = new ArrayList<>();
        this.listRestaurant = new ArrayList<>();
        this.listDeliveryPerson = new ArrayList<>();
    }

    public void addCustomer(Customer c){
        this.listCustomer.add(c);
    }

    public void addRestaurant(Restaurant r){
        this.listRestaurant.add(r);
    }

    public void addDeliveryPerson(DeliveryPerson dp){
        this.listDeliveryPerson.add(dp);
    }

    public ArrayList<Customer> getListCustomer() {
        return listCustomer;
    }

    public ArrayList<Restaurant> getListRestaurant() {
        return listRestaurant;
    }

    public ArrayList<DeliveryPerson> getListDeliveryPerson() {
        return listDeliveryPerson;
    }

}