package ru.yandex.kingartaved.preparator.impl;

import ru.yandex.kingartaved.math.Bracketable;
import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.utils.Creator;
import ru.yandex.kingartaved.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static ru.yandex.kingartaved.utils.Utils.addSpaces;


public class ExpressionPreparator implements Preparatorable {

    private static final Map<String, String> brackets;

    static {
        try {
            brackets = Creator.getBracketMap();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private final String expression;

    public ExpressionPreparator(String expression) {
        this.expression = Utils.removeAllSpaces(expression);//сразу же на входе подчищаем выражение от пробелов.
    }


    //метод для составления списка, состоящего из членов выражения, из входного String выражения:
    public List<String> getExpressionMembers() { //TODO: сделать приватным потом, и переписать тесты
        List<String> members = new ArrayList<>();
        String[] stringTokens = expression.split("");

        StringBuilder sbBuffer = new StringBuilder();
        for (String s : stringTokens) {
            char ch = s.charAt(0); //у нас всегда только один символ в строке после сплита, поэтому 0.
            if (Character.isDigit(ch)) {
                sbBuffer.append(ch);
            } else if (!sbBuffer.isEmpty()) {
                members.add(sbBuffer.toString()); //Копируем в результирующий список наше строку-число. Если буфер пустой, то ничего и не добавляется.
                sbBuffer.delete(0, sbBuffer.length());//Очищаем буфер. В методе delete второй индекс - не включается в диапазон.
                members.add(String.valueOf(ch));
            } else {
                members.add(String.valueOf(ch));
            }
        }
        if (!sbBuffer.isEmpty()) members.add(sbBuffer.toString());//из-за этого похоже ошибка при выражении "1-".
        return members;
    }


    //Метод проверки в пользовательском выражении наличия унарного минуса и его замены.
    @Override
    public List<String> unaryMinusHandler() {
        List<String> members = getExpressionMembers();
        List<String> members2 = new ArrayList<>();

        for (int i = 0; i < members.size(); i++) {
            //если элемент не минус, то добавляем его в выводную коллекцию.
            if (!members.get(i).equals("-")) {
                members2.add(members.get(i));
                //иначе если элемент является первым в коллекции (i==0),
                // то в выводную коллекцию добавляем строки 0 и -.
            } else if (i == 0) {
                members2.add("0");
                members2.add("-");
                //иначе, если элемент "-" не первый, проверяем есть ли перед ним откр скобка, если да, то в выводную коллекцию добавляем строки 0 и -.
            } else if (brackets.containsValue(members.get(i - 1))) {
                members2.add("0");
                members2.add("-");
                //если минус - это не первый элемент и перед ним нет откр скобки,
                // то добавляем в выводную коллекцию.
            } else members2.add("-");
        }
        return members2;
    }


}


/**
 * Метод проверки в пользовательском выражении наличия унарного минуса и его замены.
 */
//    @Override
//    public List<String> unaryMinusHandler() {
//
//        //получаем чищенное от пробелов выражение из метода этого класса.
//        String cleanExpression = removeSpaces();
//        //разделяем валидное выражение на токены и операнды с помощью addSpaces и далее сплитуем в список.
//        List<String> validExpression = new ArrayList<>(Arrays.asList(addSpaces(cleanExpression).split(" ")));
//        List<String> tempArray = new ArrayList<>();
//
//        for (int i = 0; i < validExpression.size(); i++) {
//            //если элемент не минус, то добавляем его в выводную коллекцию.
//            if (!validExpression.get(i).equals("-")) {
//                tempArray.add(validExpression.get(i));
//
//                //иначе если элемент является первым в коллекции (i==0),
//                // то в вывод коллекцию добавляем строки 0 и -.
//            } else if (i == 0) {
//                tempArray.add("0");
//                tempArray.add("-");
//
//                //иначе, если элемент "-" не первый, проверяем есть ли перед ним откр скобка, если
//                // да, то в вывод коллекцию добавляем строки 0 и -.
//            } else if (brackets.containsValue(validExpression.get(i - 1).charAt(0))) {
//                tempArray.add("0");
//                tempArray.add("-");
//
//                //если минус - это не первый элемент и перед ним нет откр скобки,
//                // то добавляем в выводную коллекцию.
//            } else tempArray.add("-");
//        }
//        return tempArray;
//    }

