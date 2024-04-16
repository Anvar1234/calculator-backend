package ru.yandex.kingartaved.utils;

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
}
