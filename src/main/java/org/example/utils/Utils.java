package org.example.utils;

import java.util.Scanner;

public final class Utils {

    private static final Scanner scanner = new Scanner(System.in);

    private Utils() {
    }

    public static int getIntFromInput(String message) {
        System.out.println(message);

        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String readLine() {
        return scanner.nextLine();
    }

}
