package main.java.com.foodordersystem;

import main.java.com.foodordersystem.model.Order;
import main.java.com.foodordersystem.model.Restaurant;
import main.java.com.foodordersystem.model.SelectionStrategyType;
import main.java.com.foodordersystem.model.User;
import main.java.com.foodordersystem.service.FoodOrderService;
import main.java.com.foodordersystem.service.FoodOrderServiceImpl;

import java.util.Map;

public class FoodOrder {
    static FoodOrderService service = new FoodOrderServiceImpl();

    public static void main(String[] args) {
        User user1 = new User("Ashwin", 500.0);
        User user2 = new User("Harish", 300.0);
        User user3 = new User("Shruthi", 700.0);
        User user4 = new User("Diya", 200.0);

        service.createUser(user1);
        service.createUser(user2);
        service.createUser(user3);
        service.createUser(user4);

        service.addToUserWallet("Ashwin", 500.0);
        service.addToUserWallet("Harish", 300.0);
        service.addToUserWallet("Shruthi", 700.0);
        service.addToUserWallet("Diya", 200.0);

        System.out.println("==== Onboarding Restaurants ====");

        Restaurant r1 = new Restaurant("R1", 4.5, 5);
        r1.addMenuItem("Veg Biryani", 100);
        r1.addMenuItem("Chicken Biryani", 150);
        r1.addMenuItem("Dosa", 60);
        service.onboardRestaurant(r1);
        System.out.println("Added: " + r1.getName());

        Restaurant r2 = new Restaurant("R2", 4.0, 5);
        r2.addMenuItem("Idli", 10);
        r2.addMenuItem("Dosa", 50);
        r2.addMenuItem("Veg Biryani", 80);
        r2.addMenuItem("Chicken Biryani", 175);
        service.onboardRestaurant(r2);
        System.out.println("Added: " + r2.getName());

        Restaurant r3 = new Restaurant("R3", 4.9, 1);
        r3.addMenuItem("Idli", 15);
        r3.addMenuItem("Dosa", 30);
        r3.addMenuItem("Gobi Manchurian", 150);
        r3.addMenuItem("Chicken Biryani", 175);
        service.onboardRestaurant(r3);
        System.out.println("Added: " + r3.getName());

        System.out.println("\n==== Updating Restaurant Menus ====");

        r1.addMenuItem("Chicken65", 250);
        System.out.println("Menu updated for R1: Added Chicken65");

        service.updateMenu("R2", "Chicken Biryani", 150);
        System.out.println("Menu updated for R2: Updated price of Chicken Biryani");

        System.out.println("\n==== Placing Orders ====");

        Order order1 = new Order("Ashwin", Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order1));

        Order order2 = new Order("Harish", Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order2));

        Order order3 = new Order("Shruthi", Map.of("Veg Biryani", 3, "Dosa", 1), SelectionStrategyType.HIGHEST_RATING);
        System.out.println(service.placeOrder(order3));

        System.out.println("\n==== Updating Orders ====");
        service.markOrderAsCompleted("R3");
        System.out.println("R3 marks Order 01 as COMPLETED");

        System.out.println("\n==== Placing More Orders ====");

        Order order4 = new Order("Harish", Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order4));


        Order order5 = new Order("Diya", Map.of("Idli", 3, "Paneer Tikka", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order5));
}
}