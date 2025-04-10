package strategy;
import model.Order;
import model.Restaurant;

import java.util.List;

public interface SelectionStrategy {
    Restaurant selectRestaurant(Order order, List<Restaurant> restaurants);
}