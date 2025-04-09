package main.java.com.foodordersystem.strategy;
import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;

import java.util.List;
import java.util.Map;

public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(Order order, List<Restaurant> restaurants) {
        Restaurant selected = null;
        double highestRating = 0;

        for (Restaurant restaurant : restaurants) {
            if (restaurant.canAcceptOrder() && canFulfillOrder(order, restaurant)) {
                if (restaurant.getRating() > highestRating) {
                    highestRating = restaurant.getRating();
                    selected = restaurant;
                }
            }
        }
        return selected;
    }

    private boolean canFulfillOrder(Order order, Restaurant restaurant) {
        Map<String, Integer> menu = restaurant.getMenu();
        for (String item : order.getItems().keySet()) {
            if (!menu.containsKey(item)) {
                return false;
            }
        }
        return true;
    }
}