package main.java.com.foodordersystem.strategy;
import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;

import java.util.List;

public interface SelectionStrategy {
    Restaurant selectRestaurant(Order order, List<Restaurant> restaurants);
}