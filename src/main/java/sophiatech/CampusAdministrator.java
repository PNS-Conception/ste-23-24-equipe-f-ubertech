package sophiatech;

public class CampusAdministrator {

    private System system;

    public CampusAdministrator(System sys){
        this.system = sys;
    }

    public void addRestaurant(Restaurant r){
        this.system.addRestaurant(r);
    }
    public void addDeliveryPerson(DeliveryPerson dp){ this.system.addDeliveryPerson(dp);}

}