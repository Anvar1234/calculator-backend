package ru.yandex.kingartaved.preparator.impl;

import ru.yandex.kingartaved.math.Bracketable;
import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class ExpressionPreparator implements Preparatorable {
    private Bracketable openBracketable;

    private final String expression;

    public ExpressionPreparator(String expression) {
        this.expression = Utils.removeSpaces(expression);//сразу же подчищаем от пробелов.
    }


    //метод для составления списка из входного String выражения:
    public List<String> getExpressionTerms() { //TODO: для посимвольного чтения можно использовать поток и Scanner для обработки.
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
    //TODO: сравнивать скобки интерфейсом OpenBracketable.
//    @Override
//    public List<String> unaryMinusHandler() {
//        List<String> members = getExpressionTerms();
//        List<String> members2 = new ArrayList<>();
//        for (int i = 0; i < members.size(); i++) {//надо какое-то условие типа: если это минус, и это первый символ, или после него идет открывающая скобка, то замена.
//            if (members.get(i).equals("-") && (i == 0 || members.get(i).equals(openBracketable.getOpenBracket()))) {
//                members2.add("0");
//                members2.add("-");
//            }
//        }
//    }


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

