package main.java.com.foodordersystem;


import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;
import main.java.com.foodordersystem.model.SelectionStrategyType;
import main.java.com.foodordersystem.service.FoodOrderService;
import main.java.com.foodordersystem.service.FoodOrderServiceImpl;

import java.util.Map;

public class FoodOrder {
    static FoodOrderService service = new FoodOrderServiceImpl();
    public static void main(String[] args) {

        // Onboarding restaurants
        Restaurant r1 = new Restaurant("R1", 4.5, 5);
        r1.addMenuItem("Veg Biryani", 100);
        r1.addMenuItem("Chicken Biryani", 150);
        service.onboardRestaurant(r1);

        Restaurant r2 = new Restaurant("R2", 4.0, 5);
        r2.addMenuItem("Idli", 10);
        r2.addMenuItem("Dosa", 50);
        r2.addMenuItem("Veg Biryani", 80);
        r2.addMenuItem("Chicken Biryani", 175);
        service.onboardRestaurant(r2);

        Restaurant r3 = new Restaurant("R3", 4.9, 1);
        r3.addMenuItem("Idli", 15);
        r3.addMenuItem("Dosa", 30);
        r3.addMenuItem("Gobi Manchurian", 150);
        r3.addMenuItem("Chicken Biryani", 175);
        service.onboardRestaurant(r3);

        // Update Restaurant Menu
        // Operation: ADD
        r1.addMenuItem("Chicken65", 250);
        // Operation: UPDATE
        service.updateMenu("R2", "Chicken Biryani", 150);

        // Place Orders
        // Order 01
        Order order1 = new Order("Ashwin", Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order1)); 
        
        // Order 02
        Order order2 = new Order("Harish", Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order2)); 

        // Order 03
        Order order3 = new Order("Shruthi", Map.of("Veg Biryani", 3, "Dosa", 1), SelectionStrategyType.HIGHEST_RATING);
        System.out.println(service.placeOrder(order3));  

        // Update Orders
        // R3 marks Order 01 as COMPLETED
        service.markOrderAsCompleted("R3");

        // Place Orders
        // Order 04
        Order order4 = new Order("Harish", Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order4)); 

        // Order 05
        Order order5 = new Order("Diya", Map.of("Idli", 3, "Paneer Tikka", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order5));
    }
}