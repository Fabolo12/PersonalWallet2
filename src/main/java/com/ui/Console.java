package com.ui;

import java.util.Random;

public class Console {
    public void show() {
        System.out.println("Welcome to the Personal Wallet Application!");
        System.out.println("Please select an option from the menu:");

        for (MenuItem item : MenuItem.values()) {
            System.out.println(item.ordinal() + 1 + ". " + item);
        }

        final int userChoise = new Random().nextInt(MenuItem.values().length) + 1;
        System.out.println("User selected option: " + userChoise);

        MenuItem.values()[userChoise - 1].execute(); // simulating user input for demonstration purposes
    }
}
