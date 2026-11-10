package com.services;

public class ShowTransaction {

    public static final ShowTransaction INSTANCE = new ShowTransaction();

    private ShowTransaction() {
    }

    public void execute() {
        System.out.println("Showing transaction...");
        // TODO 10.11.2026
    }
}
