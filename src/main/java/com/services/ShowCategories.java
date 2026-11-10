package com.services;

public class ShowCategories {

    public static final ShowCategories INSTANCE = new ShowCategories();

    private ShowCategories() {
    }

    public void execute() {
        System.out.println("Showing categories...");
        // TODO 10.11.2026
    }
}
