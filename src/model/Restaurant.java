package model;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    private String name;
    private double rating;
    private int maxNoOfOrders;
    private int currentOrders;
    private Map<String, Integer> menu;

    public Restaurant(String name, double rating, int maxNoOfOrders) {
        if (name == null || name.isEmpty() || rating < 0 || maxNoOfOrders <= 0) {
            throw new IllegalArgumentException("Invalid restaurant details");
        }
        this.name = name;
        this.rating = rating;
        this.maxNoOfOrders = maxNoOfOrders;
        this.currentOrders = 0;
        this.menu = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getRating() {
        return rating;
    }

    public int getMaxNoOfOrders() {
        return maxNoOfOrders;
    }

    public int getCurrentOrders() {
        return currentOrders;
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    public void addMenuItem(String item, int price) {
        if (item == null || item.isEmpty() || price <= 0) {
            throw new IllegalArgumentException("Invalid menu item or price");
        }
        menu.put(item, price);
    }

    public void updateMenuItem(String item, int price) {
        if (!menu.containsKey(item)) {
            throw new IllegalArgumentException("Item does not exist in menu");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        if (menu.containsKey(item)) {
            menu.put(item, price);
        }
    }

    public boolean canAcceptOrder() {
        return currentOrders < maxNoOfOrders;
    }

    public void incrementOrders() {
        if (!canAcceptOrder()) {
            throw new IllegalStateException("Cannot accept more orders");
        }
        currentOrders++;
    }

    public void decrementOrders() {
        if (currentOrders > 0) {
            currentOrders--;
        }
    }
}