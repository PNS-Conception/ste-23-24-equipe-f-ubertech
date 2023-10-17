package sophiatech;
import java.util.ArrayList;

public class Customer {

    private String firstName;
    private String lastName;

    private ArrayList<Product> pendingOrder;


    public Customer(String fn, String ln){
        this.firstName = fn;
        this.lastName = ln;

        this.pendingOrder = new ArrayList<>();
    }

    public void addProductToPendingOrder(Product p){
        this.pendingOrder.add(p);
    }

    public int getSizePendingOrder(){
        return this.pendingOrder.size();
    }
}