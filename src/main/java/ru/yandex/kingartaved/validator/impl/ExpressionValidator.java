package ru.yandex.kingartaved.validator.impl;

import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.utils.Getter;
import ru.yandex.kingartaved.utils.Utils;
import ru.yandex.kingartaved.validator.Validatorable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static ru.yandex.kingartaved.utils.Getter.VALID_TOKENS;

public class ExpressionValidator implements Validatorable {
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


    /**
     * The public result method for checking the validity of an expression.
     */
    @Override
    public boolean isValidExpression() {
        try {
            return isValidTokens() && checkLastToken() && isBracketsOrderCorrect();
        } catch (Exception e) {
            throw new RuntimeException("Выражение не валидно!");
        }
    }

    public boolean isValidExpressionForTest() {
        return isValidExpression();
    }


    /**
     * Метод проверки, что выражение заканчивается не оператором, а числом или закрывающей скобкой.
     */
    private boolean checkLastToken() {
        int lastTokenIndex = preparedExpression.size() - 1;
        String lastToken = preparedExpression.get(lastTokenIndex);
        return (Getter.getPriorityOfToken(lastToken) == Integer.MAX_VALUE ||
                Getter.getPriorityOfToken(lastToken) == -1); //порядок проверки ВАЖЕН! Если сначала проверяется наличие класса, то если в конце выражения стоит число, то выбрасывается моё рантайм исключение, которое сообщает что такого класса (что справедливо) нет.
    }
    public boolean checkLastTokenForTest() {
        return checkLastToken();
    }


    /**
     * A method for checking the correct placement of parentheses in an expression.
     */
    private boolean isBracketsOrderCorrect() {
        int count = 0;
        for (String s : preparedExpression) {
            if (count >= 0) { //при наличии закрывающей скобки до открывающей, баланс уйдет в минус.
                if (Getter.getPriorityOfToken(s) == 1) { //если элемент списка - любая открывающая скобка, то:
                    count++;
                } else if (Getter.getPriorityOfToken(s) == -1) { //если элемент списка - любая закрывающая скобка, то:
                    count--;
                }
            }
        }
        return count == 0; //по итогу, если для каждой откр скобки есть пара с закрывающей, то баланс будет соблюден, count будет равно 0.
    }

    public boolean isBracketsOrderCorrectForTest()  {
        return isBracketsOrderCorrect();
    }

    /**
     * Method for checking if a custom expression contains only valid tokens.
     */
    private boolean isValidTokens() {
        for (String s : preparedExpression) {
            if (!Utils.isNumeric(s) && !VALID_TOKENS.contains(s)) return false;
        }
        return true;
    }

    public boolean isValidTokensForTest() {
        return isValidTokens();
    }
}
