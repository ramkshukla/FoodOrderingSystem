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
        User ashwin = new User("Ashwin", 500.0);
        User harish = new User("Harish", 300.0);
        User shruthi = new User("Shruthi", 700.0);
        User diya = new User("Diya", 200.0);

        service.createUser(ashwin);
        service.createUser(harish);
        service.createUser(shruthi);
        service.createUser(diya);

        service.addToUserWallet(ashwin.getName(), 500.0);
        service.addToUserWallet(harish.getName(), 300.0);
        service.addToUserWallet(shruthi.getName(), 700.0);
        service.addToUserWallet(diya.getName(), 200.0);


        Restaurant r1 = new Restaurant("R1", 4.5, 5);
        r1.addMenuItem("Veg Biryani", 100);
        r1.addMenuItem("Chicken Biryani", 150);
        r1.addMenuItem("Dosa", 60);
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


        r1.addMenuItem("Chicken65", 250);

        service.updateMenu("R2", "Chicken Biryani", 150);


        Order order1 = new Order(ashwin.getName(), Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order1));

        Order order2 = new Order(harish.getName(), Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order2));

        Order order3 = new Order(shruthi.getName(), Map.of("Veg Biryani", 3, "Dosa", 1), SelectionStrategyType.HIGHEST_RATING);
        System.out.println(service.placeOrder(order3));

        service.markOrderAsCompleted("R3");


        Order order4 = new Order(harish.getName(), Map.of("Idli", 3, "Dosa", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order4));


        Order order5 = new Order(diya.getName(), Map.of("Idli", 3, "Paneer Tikka", 1), SelectionStrategyType.LOWEST_COST);
        System.out.println(service.placeOrder(order5)); 
}
}