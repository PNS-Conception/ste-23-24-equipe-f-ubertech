package sophiatech;

import java.util.Date;
import java.util.ArrayList;

public class Order {

    private String location;
    private Date date;
    private ArrayList<Product> productList;
    private Status status;

    public Order(location, date, productList){
        this.location = location;
        this.date = date;
        this.productList = productList;
        this.status = Status.IN_PREPATION;
    }

    public void changeStatus(Status st){
        this.status = st;
    }
}