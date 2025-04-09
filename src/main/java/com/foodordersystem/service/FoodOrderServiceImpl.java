package main.java.com.foodordersystem.service;


import main.java.com.foodordersystem.exception.RestaurantNotFoundException;
import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;
import main.java.com.foodordersystem.model.SelectionStrategyType;
import main.java.com.foodordersystem.strategy.SelectionStrategy;
import main.java.com.foodordersystem.strategy.HighestRatingStrategy;
import main.java.com.foodordersystem.strategy.LowestCostStrategy;

import java.util.ArrayList;
import java.util.List;

import static main.java.com.foodordersystem.model.SelectionStrategyType.HIGHEST_RATING;
import static main.java.com.foodordersystem.model.SelectionStrategyType.LOWEST_COST;

public class FoodOrderServiceImpl implements FoodOrderService {
    private final List<Restaurant> restaurants = new ArrayList<>();

    @Override
    public void onboardRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    @Override
    public void updateMenu(String restaurantName, String item, int price) {
        Restaurant restaurant = findRestaurantByName(restaurantName);
        restaurant.updateMenuItem(item, price);
    }

    @Override
    public String placeOrder(Order order) {
        SelectionStrategy strategy = getStrategy(order.getStrategyType());
        Restaurant selectedRestaurant = strategy.selectRestaurant(order, restaurants);

        if (selectedRestaurant == null) {
            return "Cannot assign the order";
        }

        selectedRestaurant.incrementOrders();
        return "Order assigned to " + selectedRestaurant.getName();
    }

    @Override
    public void markOrderAsCompleted(String restaurantName) {
        Restaurant restaurant = findRestaurantByName(restaurantName);
        restaurant.decrementOrders();
    }

    private Restaurant findRestaurantByName(String name) {
        return restaurants.stream()
                .filter(r -> r.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found: " + name));
    }

    private SelectionStrategy getStrategy(SelectionStrategyType strategyType) {
        return switch (strategyType) {
            case LOWEST_COST -> new LowestCostStrategy();
            case HIGHEST_RATING -> new HighestRatingStrategy();
        };
    }
}