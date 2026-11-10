package com.services;

public class AddTransaction {

    public static final AddTransaction INSTANCE = new AddTransaction();

    private AddTransaction() {
    }

    public void execute() {
        System.out.println("Executing AddTransaction service...");
        // TODO 10.11.2026
    }
}
