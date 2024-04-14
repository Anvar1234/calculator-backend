package ru.yandex.kingartaved.validator.impl;

import ru.yandex.kingartaved.math.Tokenable;
import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.utils.Getter;
import ru.yandex.kingartaved.utils.Utils.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ru.yandex.kingartaved.utils.Getter.VALID_TOKENS;

public class ExpressionValidator {
    //    private static final Map<String, String> brackets = Utils.BRACKETS;
    private final List<String> preparedExpression;

    /**
     * Данный класс необходим для проверки:
     * 1) корректности расстановки скобок,
     * 2) понимания, что выражение заканчивается не оператором, а скобкой или числом,
     * 3) валидности используемых токенов.
     */
    //В конструктор приходит очищенное от пробелов, проверенное на пустоту выражение в виде списка, разделенное на нормальные члены мат выражения и с замененным унарным минусом.
    public ExpressionValidator(Preparatorable preparatorable) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.preparedExpression = preparatorable.getPreparedExpression();
    }


//    /**
//     * The public result method for checking the validity of an expression.
//     */
//    @Override
//    public boolean isExpressionValid() throws RuntimeException {
//        if (isBracketsOrderCorrect()) {
//            return true;
//        } else throw new RuntimeException("Parentheses are incorrect!");
//    }
//

    /**
     * Метод проверки, что выражение заканчивается не оператором, а числом или скобкой.
     */
    private boolean checkLastToken() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int lastTokenIndex = preparedExpression.size() - 1;
        String lastToken = preparedExpression.get(lastTokenIndex);
        return (Getter.getPriorityOfToken(lastToken) == Integer.MAX_VALUE ||
                Getter.getPriorityOfToken(lastToken) == -1); //порядок проверки ВАЖЕН! Если сначала проверяется наличие класса, то если в конце выражения стоит число, то выбрасывается моё рантайм исключение, которое сообщает что такого класса (что справедливо) нет.
    }
    public boolean checkLastTokenForTest() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return checkLastToken();
    }


    /**
     * A method for checking the correct placement of parentheses in an expression.
     */
    private boolean isBracketsOrderCorrect() throws RuntimeException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int count = 0;
        if (isValidTokens()) { //todo: добавить сюда это условие, когда будут все классы-токены.
            for (String s : preparedExpression) {
                if (count >= 0) { //при наличии закрывающей скобки до открывающей, баланс уйдет в минус.
                    if (Getter.getPriorityOfToken(s) == 1) { //если элемент списка - любая открывающая скобка, то:
                        count++;
                    } else if (Getter.getPriorityOfToken(s) == -1) { //если элемент списка - любая закрывающая скобка, то:
                        count--;
                    }
                }
            }
        }
        return count == 0; //по итогу, если для каждой откр скобки есть пара с закрывающей, то баланс будет соблюден, count будет равно 0.
    }

    public boolean isBracketsOrderCorrectForTest() throws RuntimeException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return isBracketsOrderCorrect();
    }

    /**
     * Method for checking if a custom expression contains only valid tokens.
     */
    private boolean isValidTokens() throws RuntimeException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        for (String s : preparedExpression) {
            if (VALID_TOKENS.contains(s)) return false;
        }
        return true;
    }

    public boolean isValidTokensForTest() throws RuntimeException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return isValidTokens();
    }
}
