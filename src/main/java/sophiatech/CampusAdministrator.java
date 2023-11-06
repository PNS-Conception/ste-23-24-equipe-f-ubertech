package sophiatech;

public class CampusAdministrator {

    private System system;

    public CampusAdministrator(){
        this.system = System.getInstance();
    }

    public void addRestaurant(Restaurant r){
        this.system.addRestaurant(r);
    }

}