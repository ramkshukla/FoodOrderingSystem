package main.java.com.foodordersystem.model;


public class User {
    private String name;
    private double walletBalance;

    public User(String name, double walletBalance) {
        if (name == null || name.isEmpty() || walletBalance < 0) {
            throw new IllegalArgumentException("Invalid user details");
        }
        this.name = name;
        this.walletBalance = walletBalance;
    }

    public String getName() {
        return name;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void deductFromWallet(double amount) {
        if (amount <= 0 || amount > walletBalance) {
            throw new IllegalArgumentException("Insufficient balance or invalid amount");
        }
        walletBalance -= amount;
    }

    public void addToWallet(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid top-up amount");
        }
        walletBalance += amount;
    }
}