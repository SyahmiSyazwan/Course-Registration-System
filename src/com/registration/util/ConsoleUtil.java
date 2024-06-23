package com.registration.util;

import java.util.Scanner;

public class ConsoleUtil {
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception ex) {
            System.out.println("Error clearing the screen: " + ex.getMessage());
        }
    }

    public static void Pause(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press any key to continue...");
                    scanner.nextLine(); // Pause
    }
}
