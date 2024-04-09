package ru.yandex.kingartaved.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Utils {

    //todo: создать мапу с приоритетами и символами.
    public static final Map<String, String> BRACKETS;

    static {
        try {
            BRACKETS = Creator.getBracketMap();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    public static String removeAllSpaces(String expression) {
        return expression.replaceAll(" ", "").trim();
    }


    public static boolean isNumber(String expression) {
        try {
            Integer.parseInt(String.valueOf(expression.charAt(0)));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isNotEmpty(String expression) {
        return !expression.isEmpty();
    }

    public static String addSpaces(String expression) {
        //TODO: в регех попробовать добавлять символы также из списка валидных символов. Сейчас,
        // например, здесь не указаны квадратные скобки, но в списке валидных они вроде есть. Скорее всего этот мтеод не нужен будет.
//строка для замены всех символов: (, ), +, -, *, /, ^ в строке exp на сами символы, предварённые пробелами и после символа добавляется пробел.
        String result = expression.replaceAll("([\\(\\)\\+\\-\\*\\/\\^])", " $1 ");
//замена всех последовательностей пробелов (или других пробельных символов) на один пробел. Плюс тримим пробелы в начале и в конце строки.
        result = result.replaceAll("\\s+", " ").trim();
        return result;
    }
}
