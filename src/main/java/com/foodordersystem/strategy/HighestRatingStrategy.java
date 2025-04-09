package main.java.com.foodordersystem.strategy;
import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class HighestRatingStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(Order order, List<Restaurant> restaurants) {
        return restaurants.stream()
                .filter(restaurant -> canFulfillOrder(order, restaurant))
                .max(Comparator.comparingDouble(Restaurant::getRating))
                .orElse(null);
    }

    private boolean canFulfillOrder(Order order, Restaurant restaurant) {
        if (!restaurant.canAcceptOrder()) {
            return false;
        }
        Map<String, Integer> menu = restaurant.getMenu();

        for (String item : order.getItems().keySet()) {
            if (!menu.containsKey(item)) {
                return false;
            }
        }
        return true;
    }
}