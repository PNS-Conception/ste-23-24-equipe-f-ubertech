package sophiatech;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;

public class System {
    private static System instance; //stocks the only instance of the system

    private PaymentService paymentService;
    private ArrayList<Customer> listCustomer;
    private ArrayList<Restaurant> listRestaurant;
    private ArrayList<DeliveryPerson> listDeliveryPerson;

    private ArrayList<Order> ordersPendingDeliveryPersons;


    public System(){
        this.listCustomer = new ArrayList<>();
        this.listRestaurant = new ArrayList<>();
        this.listDeliveryPerson = new ArrayList<>();
        this.paymentService = new PaymentService();
        this.ordersPendingDeliveryPersons = new ArrayList<>();
    }

    public static System getInstance() {
        if (instance == null) {
            instance = new System();
        }
        return instance;
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
  
    public PaymentService getPaymentService() {
        return this.paymentService;
    }

    public ArrayList<DeliveryPerson> getAvailableDeliveryPerson() {
        ArrayList<DeliveryPerson> availableDeliveryPerson = new ArrayList<>();
        for (DeliveryPerson deliveryPerson : listDeliveryPerson) {
            if (deliveryPerson.getIsAvailable())
                availableDeliveryPerson.add(deliveryPerson);
        }

        //if there are orders pending for a delivery person, assigns the first one to the first available delivery person
        for (int i = 0; i < Math.min(ordersPendingDeliveryPersons.size(), availableDeliveryPerson.size()); i++) {
            availableDeliveryPerson.get(i).addOrder(ordersPendingDeliveryPersons.get(i));
            availableDeliveryPerson.remove(i);
            ordersPendingDeliveryPersons.remove(i);
        }

        return availableDeliveryPerson;
    }

    public void addOrderWithoutDeliveryPerson (Order o) {
        ordersPendingDeliveryPersons.add(o);
    }

    public void removeDeliveryPerson(DeliveryPerson dp){ listDeliveryPerson.remove(dp);}
}