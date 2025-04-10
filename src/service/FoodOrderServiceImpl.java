package service;


import exception.RestaurantNotFoundException;
import model.Order;
import model.Restaurant;
import model.SelectionStrategyType;
import model.User;
import strategy.HighestRatingStrategy;
import strategy.LowestCostStrategy;
import strategy.SelectionStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodOrderServiceImpl implements FoodOrderService {
    private final List<Restaurant> restaurants = new ArrayList<>();
    private final Map<String, User> users = new HashMap<>();

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
        User user = users.get(order.getUser());
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + order.getUser());
        }

        SelectionStrategy strategy = getStrategy(order.getStrategyType());
        Restaurant selectedRestaurant = strategy.selectRestaurant(order, restaurants);

        if (selectedRestaurant == null) {
            return "Cannot assign the order: None of the restaurants can fulfil the order.";
        }
        for (String item : order.getItems().keySet()) {
            if (!selectedRestaurant.getMenu().containsKey(item)) {
                return "Selected restaurant cannot fulfill the order (missing item: " + item + ")";
            }
        }

        double cost = calculateOrderCost(order.getItems(), selectedRestaurant.getMenu());

        if (user.getWalletBalance() < cost) {
            return "Insufficient wallet balance: Order cost is " + cost + ", but wallet balance is " + user.getWalletBalance();
        }

        user.deductFromWallet(cost);
        selectedRestaurant.incrementOrders();

        return "Order assigned to " + selectedRestaurant.getName();
    }



    @Override
    public void markOrderAsCompleted(String restaurantName) {
        Restaurant restaurant = findRestaurantByName(restaurantName);
        restaurant.decrementOrders();

    }

    @Override
    public void createUser(User user) {
        if (users.containsKey(user.getName())) {
            throw new IllegalArgumentException("User already exists");
        }
        users.put(user.getName(), user);
    }

    @Override
    public void addToUserWallet(String userName, double amount) {
        User user = users.get(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        user.addToWallet(amount);
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

    private double calculateOrderCost(Map<String, Integer> items, Map<String, Integer> menu) {
        return items.entrySet().stream()
                .mapToDouble(entry -> menu.get(entry.getKey()) * entry.getValue())
                .sum();
    }
}