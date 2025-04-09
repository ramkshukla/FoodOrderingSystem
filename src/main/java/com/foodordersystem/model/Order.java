package main.java.com.foodordersystem.model;


import java.util.Map;

public class Order {
    private String user;
    private Map<String, Integer> items; // Item -> Quantity
    private SelectionStrategyType strategyType;

    public Order(String user, Map<String, Integer> items, SelectionStrategyType strategyType) {
        this.user = user;
        this.items = items;
        this.strategyType = strategyType;
    }

    public String getUser() {
        return user;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public SelectionStrategyType getStrategyType() {
        return strategyType;
    }
}