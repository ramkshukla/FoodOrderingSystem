package main.java.com.foodordersystem.service;
import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;


public interface FoodOrderService {
    void onboardRestaurant(Restaurant restaurant);

    void updateMenu(String restaurantName, String item, int price);

    String placeOrder(Order order);

    void markOrderAsCompleted(String restaurantName);
}
