package com.services;

public class ShowBalance {

    public static final ShowBalance INSTANCE = new ShowBalance();

    private ShowBalance() {
    }

    public void execute() {
        System.out.println("Showing balance...");
        // TODO 10.11.2026
    }
}
