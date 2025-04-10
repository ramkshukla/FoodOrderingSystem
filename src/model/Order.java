package model;


import java.util.Map;

public class Order {
    private String user;
    private Map<String, Integer> items;
    private SelectionStrategyType strategyType;

    public Order(String user, Map<String, Integer> items, SelectionStrategyType strategyType) {
        if (user == null || user.isEmpty() || items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Invalid order details");
        }
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