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
 * 2) окончания выражения (заканчивается не оператором, а закрывающей скобкой или числом), //TODO: удалить, скорее всего
 * 3) валидности используемых токенов.
 * 4) валидности порядка расстановки чисел.
 * 5) валидности порядка расстановки операторов.
 * 6) наличия хотя бы одного числа.
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
            return isValidTokens() && isValidTokensSequence();
        } catch (Exception e) {
            //TODO: норм ли так обрабатывать исключения?
            throw new RuntimeException("The expression is not valid!");//Выражение не валидно!
        }
    }

    public boolean isValidExpressionForTest() {
        return isValidExpression();
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


    /**
     * Метод проверки пользовательского выражения на корректную расстановку токенов.
     */
    private boolean isValidTokensSequence() {
        try {
            return isBracketsOrderCorrect() &&
                    isNumbersValidPositions() &&
                    isOperatorsValidPosition();
        } catch (Exception e) {
            throw new RuntimeException("Token placement is incorrect!");//Расстановка токенов не верная!
        }
    }


    /**
     * Метод проверки корректной расстановки скобок в выражении.
     */
    private boolean isBracketsOrderCorrect() {
        return (arePairsOfBrackets() && notNearBrackets());
    }

    public boolean isBracketsOrderCorrectForTest() {
        return isBracketsOrderCorrect();
    }

    /**
     * Вспомогательный метод проверки парности скобок во всем выражении.
     */
    private boolean arePairsOfBrackets() {
        int balance = 0;
        for (String current : preparedExpression) {

            //проверка на парность скобок:
            //при наличии закрывающей скобки до открывающей, баланс уйдет в минус и больше в это условие не зайдет.
            if (balance >= 0) { // Больше - так как откр скобок может быть несколько.
                if (Getter.getPriority(current) == 1) { //если элемент списка - любая открывающая скобка, то:
                    balance++;
                } else if (Getter.getPriority(current) == -1) { //если элемент списка - любая закрывающая скобка, то:
                    balance--;
                }
            }
        }
        //по итогу, если для каждой откр скобки есть пара с закрывающей, то баланс будет соблюден, balance == 0.
        return balance == 0;
    }

    /**
     * Вспомогательный метод проверки идущих подряд открывающей скобки и закрывающей.
     * Не должно быть: () и )( .
     */
    private boolean notNearBrackets() {
        for (int i = 0; i < preparedExpression.size(); i++) {
            String current = preparedExpression.get(i);
            int lastIndex = preparedExpression.size() - 1;

            if (i < lastIndex) {//если элемент не последний
                String next = preparedExpression.get(i + 1);
                if (Getter.getPriority(current) == 1 && Getter.getPriority(next) == -1 ||
                        Getter.getPriority(current) == -1 && Getter.getPriority(next) == 1) {//если элемент - откр скобка, а следующий элемент - закр скобка ИЛИ наоборот.
                    return false;
                }
            }
        }
        return true;
    }

    public boolean notNearBracketsForTest() {
        return notNearBrackets();
    }

    /**
     * Основной метод проверки валидной расстановки операторов.
     */
    private boolean isOperatorsValidPosition() {
        for (int i = 0; i < preparedExpression.size(); i++) {
            String current = preparedExpression.get(i);
            if (!Utils.isNumeric(current) && Getter.getPriority(current) > 1) {//если элемент не число, а оператор - вот здесь вся прелесть того, если бы числа были классами-токенами.
                //Оператор не может стоять первым и последним.
                //Оператор может стоять:
                //*(после закрывающей скобки ИЛИ числа (MAX_VALUE))
                // ИИ (перед открывающей скобкой ИЛИ числом (MAX_VALUE))
                // вариант между скобками или между числами входит в верхние условия.


                //если элемент первый ИЛИ последний, то false.
                if (i == 0 || i == preparedExpression.size() - 1) {
                    return false;
                }
                if (i < preparedExpression.size() - 1) {//если элемент не последний
                    String previous = preparedExpression.get(i - 1);
                    String next = preparedExpression.get(i + 1);

                    //если (предыдущий элемент не закрыв скобка ИИ не число) ИИ (следующий - не откр скобка ИИ число):
                    if ((Getter.getPriority(previous) != -1 && Getter.getPriority(previous) != Integer.MAX_VALUE) &&
                            (Getter.getPriority(next) != 1 && Getter.getPriority(next) != Integer.MAX_VALUE)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isOperatorsValidPositionForTest() {
        return isOperatorsValidPosition();
    }

    /**
     * Основной метод проверки валидности расстановки чисел.
     * Использует вспомогательные методы isHaveANumber(), isValidNumberPosition().
     */
    private boolean isNumbersValidPositions() {
        if (!isHaveANumber()) return false; //проверим, есть ли в выражении числа вообще.
        for (int i = 0; i < preparedExpression.size(); i++) {
            //если элемент - число
            if (Utils.isNumeric(preparedExpression.get(i))) {
                //проверяем валидность
                if (!isValidNumberPosition(i)) return false;
            }
        }

        return true;
    }

    public boolean isNumbersValidPositionsForTest() {
        return isNumbersValidPositions();
    }


    /**
     * Вспомогательный метод проверки пользовательского выражения на валидную расстановку чисел.
     */
    private boolean isValidNumberPosition(int index) {
        //перед числом не может стоять закр скобка, но может - открывающая.
        //если значение - число, то 4 случая:
        //когда нет предыдущ и следующего, а только текущий.
        //если нет предыдущего, но есть следующий, и у следующего приоритет != 1 (то есть не откр скобка. Но закрывающая может быть).
        //если есть предыдущее и есть следующее, и у предыдущ приоритет !=-1, а у следующего != 1.
        //если нет следующего, но есть предыдущий, и у предыдущего приоритет != -1.


        //если это первый элемент и нет след элемента - это вообще единственный элемент.
        if (index == 0 && index == preparedExpression.size() - 1) {
            return true;
        }
        //если это первый элемент и есть след элемент
        if (index == 0 && index + 1 < preparedExpression.size()) {
            //и если след элемент равен открывающей скобке
            if (Getter.getPriority(preparedExpression.get(index + 1)) == 1) {
                return false;
            }
        }
        //если есть пред и след элемент:
        if (index > 0 && index < preparedExpression.size() - 1) {
            String previous = preparedExpression.get(index - 1);
            String next = preparedExpression.get(index + 1);
            //если есть пред элемент равный закр скобке и след равный открывающей:
            if (Getter.getPriority(previous) == -1 || Getter.getPriority(next) == 1) {
                return false;
            }
        }
        //если это последний элемент ИИ есть пред элемент:
        if (index == preparedExpression.size() - 1 && index > 0) {
            //и он равен закрывающей скобке:
            if (Getter.getPriority(preparedExpression.get(index - 1)) == -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Вспомогательный метод для проверки, что в выражении вообще есть числа.
     */
    private boolean isHaveANumber() {
        for (String s : preparedExpression) {
            if (Utils.isNumeric(s)) return true;
        }
        return false;
    }

}
