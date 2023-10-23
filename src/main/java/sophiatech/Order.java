package sophiatech;

import java.util.Date;
import java.util.ArrayList;

public class Order {

    private String location;
    private Date date;
    private ArrayList<Product> productList;
    private Status status;

    public Order(String location, Date date, ArrayList<Product> productList){
        this.location = location;
        this.date = date;
        this.productList = productList;
        this.status = Status.IN_PREPARATION;
    }

    public void changeStatus(Status st){
        this.status = st;
    }

    public Status getStatus(){
        return this.status;
    }

    public String getLocation(){
        return this.location;
    }

    public Date getDate(){
        return this.date;
    }

    public ArrayList<Product> getProductList(){
        return this.productList;
    }
}