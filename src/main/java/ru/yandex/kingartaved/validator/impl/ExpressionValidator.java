package ru.yandex.kingartaved.validator.impl;

import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.utils.Getter;
import ru.yandex.kingartaved.utils.Utils;
import ru.yandex.kingartaved.validator.Validatorable;

import java.util.List;

import static ru.yandex.kingartaved.utils.Getter.VALID_TOKENS;
/**
 * Данный класс содержит методы для проверки:
 * 1) корректности расстановки скобок,
 * 2) окончания выражения (заканчивается не оператором, а закрывающей скобкой или числом),
 * 3) валидности используемых токенов.
 * В конструктор приходит очищенное от пробелов, проверенное на пустоту выражение в виде списка,
 * разделенное на нормальные члены мат выражения и с замененным унарным минусом.
 */
public class ExpressionValidator implements Validatorable {
    private final List<String> preparedExpression;

    public ExpressionValidator(Preparable preparable) {
        this.preparedExpression = preparable.getPreparedExpression();
    }

    /**
     * Результирующий публичный метод для проверки валидности пользовательского выражения.
     */
    @Override
    public boolean isValidExpression() {
        try {
            return isValidTokens() && checkLastToken() && isBracketsOrderCorrect();
        } catch (Exception e) {
            throw new RuntimeException("The expression is not valid!");//Выражение не валидно!
        }
    }
    public boolean isValidExpressionForTest() {
        return isValidExpression();
    }


    /**
     * Метод проверки окончания выражения, что выражение заканчивается не оператором, а числом или закрывающей скобкой.
     */
    private boolean checkLastToken() {
        int lastTokenIndex = preparedExpression.size() - 1;
        String lastToken = preparedExpression.get(lastTokenIndex);
        return (Getter.getPriority(lastToken) == Integer.MAX_VALUE ||
                Getter.getPriority(lastToken) == -1); //порядок проверки ВАЖЕН! Если сначала проверяется наличие класса, то если в конце выражения стоит число, то выбрасывается моё рантайм исключение, которое сообщает что такого класса (что справедливо) нет.
    }
    public boolean checkLastTokenForTest() {
        return checkLastToken();
    }


    /**
     * Метод проверки правильности расстановки скобок в выражении.
     */
    private boolean isBracketsOrderCorrect() {
        int balance = 0;
        for (String s : preparedExpression) {
            if (balance >= 0) { //при наличии закрывающей скобки до открывающей, баланс уйдет в минус.
                if (Getter.getPriority(s) == 1) { //если элемент списка - любая открывающая скобка, то:
                    balance++;
                } else if (Getter.getPriority(s) == -1) { //если элемент списка - любая закрывающая скобка, то:
                    balance--;
                }
            }
        }
        return balance == 0; //по итогу, если для каждой откр скобки есть пара с закрывающей, то баланс будет соблюден, balance будет равен 0.
    }
    public boolean isBracketsOrderCorrectForTest()  {
        return isBracketsOrderCorrect();
    }


    /**
     * Метод проверки пользовательского выражения на наличие только валидных токенов.
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
