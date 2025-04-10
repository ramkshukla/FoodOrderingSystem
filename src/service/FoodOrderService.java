package service;

import model.Order;
import model.Restaurant;
import model.User;


public interface FoodOrderService {
    void onboardRestaurant(Restaurant restaurant);
    void updateMenu(String restaurantName, String item, int price);
    String placeOrder(Order order);
    void markOrderAsCompleted(String restaurantName);
    void createUser(User user);
    void addToUserWallet(String userName, double amount);
}
