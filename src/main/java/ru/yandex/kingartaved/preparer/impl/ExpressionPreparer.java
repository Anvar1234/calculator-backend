package ru.yandex.kingartaved.preparer.impl;


import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.utils.Getter;
import ru.yandex.kingartaved.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Класс-подготовщик входящего пользовательского выражения для дальнейшего использования.
 * Подготавливает выражение, удаляя лишние пробелы, разделяя на действительные члены математического выражения,
 * а также обрабатывая наличие унарного минуса.
 */
public class ExpressionPreparer implements Preparable {
    private final String expression;

    public ExpressionPreparer(String expression) {
        String expressionWithoutSpaces = Utils.removeAllSpaces(expression);
        //сразу же на входе подчищаем выражение от пробелов и проверяем на пустоту.
        if (!expressionWithoutSpaces.isEmpty()) {
            this.expression = expressionWithoutSpaces;
        } else {
            throw new RuntimeException("Введено пустое выражение!");
        }
    }

    /**
     * Метод для получения подготовленного выражения.
     */
    @Override
    public List<String> getPreparedExpression() {
        return unaryMinusHandler();
    }

    /**
     * Приватный метод для составления из входного выражения без пробелов списка,
     * состоящего из членов выражения, а не отдельных токенов, например, в случае с числами - значением списка будут
     * не цифры, а "склеенное" из них число.
     */
    private List<String> convertExpressionIntoMembers() {
        List<String> members = new ArrayList<>();
        String[] stringTokens = expression.split("");

        StringBuilder sbBuffer = new StringBuilder();

        for (String s : stringTokens) {
            if (Utils.isNumeric(s) || Getter.getPriority(s) == 0) {
                sbBuffer.append(s);
            } else if (!sbBuffer.isEmpty()) {
                members.add(sbBuffer.toString()); //Копируем в результирующий список наше строку-число. Если буфер пустой, то ничего и не добавляется.
                sbBuffer.delete(0, sbBuffer.length());//Очищаем буфер. В методе delete второй индекс - не включается в диапазон.
                members.add(s);
            } else {
                members.add(s);
            }
        }
        if (!sbBuffer.isEmpty()) members.add(sbBuffer.toString());
        return members;
    }
    public List<String> convertExpressionIntoMembersForTest(){
        return convertExpressionIntoMembers();
    }

    /**
     * Метод проверки наличия в пользовательском выражении унарного минуса и его замены.
     */
    private List<String> unaryMinusHandler() {
        List<String> members = convertExpressionIntoMembers();
        List<String> handledMembers = new ArrayList<>();

        for (int i = 0; i < members.size(); i++) {
            //если элемент не минус, то добавляем его в выводную коллекцию.
            if (!members.get(i).equals("-")) {
                handledMembers.add(members.get(i));
                //иначе если элемент является первым в коллекции (i==0),
                // то в выводную коллекцию добавляем строки 0 и -.
            } else if (i == 0) {
                handledMembers.add("0");
                handledMembers.add("-");
                //иначе, если элемент "-" не первый, проверяем есть ли перед ним откр скобка (приоритет == 1), если да, то в выводную коллекцию добавляем строки 0 и -.
            } else if (Getter.getPriority(members.get(i - 1)) == 1) {
                handledMembers.add("0");
                handledMembers.add("-");
                //если минус - это не первый элемент и перед ним нет откр скобки,
                // то добавляем в выводную коллекцию.
            } else handledMembers.add("-");
        }
        return handledMembers;
    }
    public List<String> unaryMinusHandlerForTest(){
        return unaryMinusHandler();
    }
}



