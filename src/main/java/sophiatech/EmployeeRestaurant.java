package sophiatech;

public class EmployeeRestaurant extends User{

    private System system;
    private boolean IsOwner;



    public EmployeeRestaurant(int nb, String name, String first_name) {
        super(nb, name, first_name);
    }
    public EmployeeRestaurant(String name, String first_name) {
        super(0, name, first_name); // A voir avec Quentin !!
    }


}
