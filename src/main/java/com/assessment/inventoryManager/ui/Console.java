package com.assessment.inventoryManager.ui;

import java.util.Scanner;

public class Console {

    public MenuOption displayMenuOption() {
        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%s. %s%n", i + 1, options[i].getMessage());
        }
    }



}
