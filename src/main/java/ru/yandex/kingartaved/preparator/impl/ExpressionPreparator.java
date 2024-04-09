package ru.yandex.kingartaved.preparator.impl;


import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.utils.Creator;
import ru.yandex.kingartaved.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Класс-обработчик входящего пользовательского выражения.
 * Подготавливает выражение: удаляя лишние пробелы, разделяя на действительные члены математического выражения,
 * а также обрабатывая наличие унарного минуса.
 */
public class ExpressionPreparator implements Preparatorable {

    private static final Map<String, String> brackets = Utils.BRACKETS;

    private final String expression;

    public ExpressionPreparator(String expression) {
        this.expression = Utils.removeAllSpaces(expression);//сразу же на входе подчищаем выражение от пробелов.
    }

    @Override
    public List<String> getPreparedExpression() {
        return unaryMinusHandler();
    }

    /**
     * Внутренний метод для составления из входного String выражения без пробелов списка,
     * состоящего из членов выражения, а не отдельных символов, в случае с числами.
     */
    private List<String> getExpressionMembers() { //TODO: сделать приватным потом, и переписать тесты
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
        if (!sbBuffer.isEmpty()) members.add(sbBuffer.toString());
        return members;
    }

    public List<String> getExpressionMembersForTest() {
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
        if (!sbBuffer.isEmpty()) members.add(sbBuffer.toString());
        return members;
    }


    /**
     * Метод проверки в пользовательском выражении наличия унарного минуса и его замены.
     * Переопределенный метод интерфейса.
     */
    private List<String> unaryMinusHandler() { //TODO: сделать приватным потом, и переписать тесты
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

    public List<String> unaryMinusHandlerForTest() {
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



