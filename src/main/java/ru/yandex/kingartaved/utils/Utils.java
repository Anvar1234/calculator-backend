package ru.yandex.kingartaved.utils;

import java.util.Scanner;

public class Utils {

    public static String removeAllSpaces(String expression) {
        return expression.replaceAll(" ", "").trim();
    }

    public static boolean isNumeric(String expression) {
        try {
            Integer.parseInt(String.valueOf(expression.charAt(0)));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
