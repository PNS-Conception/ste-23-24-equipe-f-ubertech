package sophiatech;

import java.util.ArrayList;
import java.util.Date;

public class RestaurantStatistics {
    private Restaurant restaurant;
    private Date creationDate;
    private int numberProducts;
    private double totalPriceProducts;
    private int numberOrders;
    private double totalPriceOrders;
    private double totalPriceOffDiscount;   //total price that the restaurant "lost" due to discounts


    public RestaurantStatistics(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.creationDate = new Date();
        this.numberProducts = 0;
        this.totalPriceProducts = 0.0;
        this.numberOrders = 0;
        this.totalPriceOrders = 0.0;
        this.totalPriceOffDiscount = 0.0;

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









    public void addProduct(Product product) {
        this.numberProducts ++;
        this.totalPriceProducts += product.getPrice();
    }

    public void addOrder(Order order) {
        this.numberOrders ++;
        this.totalPriceOrders += order.getTotalPrice();

        java.lang.System.out.println("numberOrders " + numberOrders + "   " + "totalPriceOrders " + totalPriceOrders);

        double maxPrice = 0.0;  //price before discounts
        for (Product product : order.getProductList()) {
            maxPrice += product.getPrice();
        }
        this.totalPriceOffDiscount += (maxPrice - order.getTotalPrice());
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

        for (Product product : restaurantProductList) {
            this.addProduct(product);
        }

        for (GroupOrder groupOrder : restaurantGroupOrderList) {
            this.addGroupOrder(groupOrder);
        }


    }
}
