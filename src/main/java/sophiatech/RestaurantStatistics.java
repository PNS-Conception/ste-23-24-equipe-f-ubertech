package sophiatech;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RestaurantStatistics {
    private Restaurant restaurant;
    private LocalTime creationDate;
    private int numberProducts;
    private double totalPriceProducts;
    private int numberOrders;
    private double totalPriceOrders;
    private double totalPriceOffDiscount;   //total price that the restaurant "lost" due to discounts
    private int[] ordersAtTime; //indexes the time at which the orders are placed
    private Map<Product, Integer> numberProductOrdered;


    public RestaurantStatistics(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.creationDate = LocalTime.now();
        this.numberProducts = 0;
        this.totalPriceProducts = 0.0;
        this.numberOrders = 0;
        this.totalPriceOrders = 0.0;
        this.totalPriceOffDiscount = 0.0;
        ordersAtTime = new int[24];
        numberProductOrdered = new HashMap<>();

        this.updateStats();
    }

    public int getNumberProducts() {
        return this.numberProducts;
    }

    public double getAveragePriceForProduct() {
        if (this.numberProducts == 0)
            return this.numberProducts;
        return this.totalPriceProducts / this.numberProducts;
    }

    public int getNumberOrders() {
        return this.numberOrders;
    }

    public double getAveragePriceForOrder() {
        if (this.numberOrders == 0)
            return this.numberOrders;
        return this.totalPriceOrders / this.numberOrders;
    }

    public double getTotalPriceForOrders() {
        return this.totalPriceOrders;
    }

    public double getTotalPriceDiscounted() {
        return this.totalPriceOffDiscount;
    }

    public double getAveragePriceDiscounted() {
        if (this.numberOrders == 0)
            return 0;
        return this.totalPriceOffDiscount / this.numberOrders;
    }

    public double[] getPercentageOrdersAtTime() {
        double [] percents = new double[24];

        for (int i = 0; i < 24; i++) {
            percents[i] += ordersAtTime[i];
            percents[i] = percents[i] / numberOrders;
        }
        return percents;
    }

    public Map<Product, Integer> getOrderedProductCount() { //returns the number of time a product was bought
        return this.numberProductOrdered;
    }



    public void addProduct(Product product) {
        this.numberProducts ++;
        this.totalPriceProducts += product.getPrice();
        numberProductOrdered.put(product, 0);
    }

    public void addOrder(Order order) {
        this.numberOrders ++;
        this.totalPriceOrders += order.getTotalPrice();

        double maxPrice = 0.0;  //price before discounts
        for (Product product : order.getProductList()) {
            if (!numberProductOrdered.containsKey(product))
                this.numberProductOrdered.put(product, 1);
            else
                this.numberProductOrdered.put(product, this.numberProductOrdered.get(product)+1);
            maxPrice += product.getPrice();
        }
        this.totalPriceOffDiscount += (maxPrice - order.getTotalPrice());

        this.ordersAtTime[order.getHour().getHour()] ++;    //adds an order to the corresponding time thus the corresponding index


    }

    public void addGroupOrder(GroupOrder groupOrder) {
        java.lang.System.out.println("adding groupOrder : " + groupOrder + "contains :");
        for (Order order : groupOrder.orders) {
            java.lang.System.out.println("\t" + order);
            if (order.getRestaurant().equals(this.restaurant)) {
                this.addOrder(order);
            }
        }
    }

    public void updateStats() {
        ArrayList<Product> restaurantProductList = restaurant.getProducts();
        ArrayList<GroupOrder> restaurantGroupOrderList = restaurant.getOrderHistory();

        this.numberOrders = restaurantGroupOrderList.size();
        this.numberProducts = restaurantProductList.size();
        this.totalPriceProducts = 0.0;
        this.totalPriceOrders = 0.0;
        this.totalPriceOffDiscount = 0.0;
        ordersAtTime = new int[24];
        numberProductOrdered = new HashMap<>();

        for (Product product : restaurantProductList) {
            this.addProduct(product);
        }

        for (GroupOrder groupOrder : restaurantGroupOrderList) {
            this.addGroupOrder(groupOrder);
        }
    }
}
