package ru.yandex.kingartaved.preparator.impl;


import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.utils.Getter;
import ru.yandex.kingartaved.utils.Utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс-обработчик входящего пользовательского выражения.
 * Подготавливает выражение: удаляя лишние пробелы, разделяя на действительные члены математического выражения,
 * а также обрабатывая наличие унарного минуса.
 */
public class ExpressionPreparator implements Preparatorable {

//    private static final Map<String, String> brackets = Utils.BRACKETS;

    private final String expression;

    public ExpressionPreparator(String expression) {
        //сразу же на входе подчищаем выражение от пробелов и проверяем на пустоту.
        if (!Utils.removeAllSpaces(expression).isEmpty()) {//todo: возможно удалить метод isNotEmpty из  класса Utils.
            this.expression = expression;
        } else {
            throw new RuntimeException("Пустое выражение!");
        }
    }

    @Override
    public List<String> getPreparedExpression() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return unaryMinusHandler();
    }

    /**
     * Внутренний метод для составления из входного String выражения без пробелов списка,
     * состоящего из членов выражения, а не отдельных символов, в случае с числами.
     */
    //todo: переписать метод так, чтобы он считывал приоритет, если =0, то это число или точка.
    //Так что в принципе для чисел можно все равно не создавать классы-токены, но для
    //разделителя (точка или запятая) создать нужно, так как в качестве разделителя пользователь может захотеть
    //использовать что-то другое.
    public List<String> getExpressionMembers() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { //TODO: сделать приватным потом, и переписать тесты
        List<String> members = new ArrayList<>();
        String[] stringTokens = expression.split("");

        StringBuilder sbBuffer = new StringBuilder();

        for (String s : stringTokens) {
            if (Utils.isNumeric(s) || Getter.getPriorityOfToken(s) == 0) {
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


    /**
     * Метод проверки в пользовательском выражении наличия унарного минуса и его замены.
     * Переопределенный метод интерфейса.
     */
    private List<String> unaryMinusHandler() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { //TODO: сделать приватным потом, и переписать тесты
        List<String> members = getExpressionMembers();
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
            } else if (Getter.getPriorityOfToken(members.get(i - 1)) == 1) {
                handledMembers.add("0");
                handledMembers.add("-");
                //если минус - это не первый элемент и перед ним нет откр скобки,
                // то добавляем в выводную коллекцию.
            } else handledMembers.add("-");
        }
        return handledMembers;
    }

}



