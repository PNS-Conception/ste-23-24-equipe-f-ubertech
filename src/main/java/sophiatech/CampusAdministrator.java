package sophiatech;

public class CampusAdministrator {

    private System system;

    public CampusAdministrator(System sys){
        this.system = sys;
    }

    public void addRestaurant(Restaurant r){
        this.system.addRestaurant(r);
    }

}