package com.assessment.inventoryManager.ui;

import java.util.Scanner;

public class Console {
    Scanner console = new Scanner(System.in);
    boolean isRunning;

    public Console(Scanner console, boolean isRunning) {
        this.console = console;
        this.isRunning = isRunning;
    }


}


