package com.ui;

import com.services.AddTransaction;
import com.services.ShowBalance;
import com.services.ShowCategories;
import com.services.ShowTransaction;

public enum MenuItem {
    SHOW_BALANCE("Show balance", ShowBalance.INSTANCE),
    SHOW_TRANSACTIONS("Show transactions", ShowTransaction.INSTANCE),
    ADD_TRANSACTION("Add transaction", AddTransaction.INSTANCE),
    SHOW_CATEGORIES("Show categories", ShowCategories.INSTANCE),
    EXIT("Exit", null),
    SHOW_TRANSACTIONS_TWO("Show transactions 2", ShowTransaction.INSTANCE);

    private final String label;

    private final Object item;

    MenuItem(String label, Object item) {
        this.label = label;
        this.item = item;
    }

    public void execute() {
        if (item == null) {
            System.exit(0);
        }

        switch (item) {
            case AddTransaction i -> i.execute();
            case ShowBalance i -> i.execute();
            case ShowTransaction i -> i.execute();
            case ShowCategories i -> i.execute();
            default -> System.out.println("No action defined for this menu item.");
        }
    }

    @Override
    public String toString() {
        return label;
    }
}
