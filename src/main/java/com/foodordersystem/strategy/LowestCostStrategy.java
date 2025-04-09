package main.java.com.foodordersystem.strategy;
import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;

import java.util.List;
import java.util.Map;

public class LowestCostStrategy implements SelectionStrategy {
    @Override
    public Restaurant selectRestaurant(Order order, List<Restaurant> restaurants) {
        Restaurant selected = null;
        int lowestCost = Integer.MAX_VALUE;

        for (Restaurant restaurant : restaurants) {
            if (restaurant.canAcceptOrder() && canFulfillOrder(order, restaurant)) {
                int cost = calculateOrderCost(order.getItems(), restaurant.getMenu());
                if (cost < lowestCost) {
                    lowestCost = cost;
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

    private int calculateOrderCost(Map<String, Integer> items, Map<String, Integer> menu) {
        int cost = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            cost += menu.get(entry.getKey()) * entry.getValue();
        }
        return cost;
    }
}