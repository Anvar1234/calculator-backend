package ru.yandex.kingartaved.validator.impl;

import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.utils.Utils;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExpressionValidator {
    private static final Map<String, String> brackets = Utils.BRACKETS;
    private final List<String> preparedExpression;

    /**
     * Данный класс необходим для проверки:
     * 1) корректности расстановки скобок,
     * 2) понимания, что выражение заканчивается не оператором, а скобкой или числом,
     * 3) валидности используемых токенов.
     */
    //В конструктор приходит очищенное от пробелов, проверенное на пустоту выражение в виде списка, разделенное на нормальные члены мат выражения и с замененным унарным минусом.
    public ExpressionValidator(Preparatorable preparatorable) {
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
     * Method for checking the nesting of parentheses in a custom expression.
     * Дубликат метода из старого калькулятора. Тот старый мтеод ниже.
     * Суть метода в том, что нам главное чтобы были пары откр-закр скобка, а как они выглядят - нам без разницы.
     */
        private boolean isBracketsOrderCorrect() throws RuntimeException {
//            if (isValidTokens()) { //todo: добавить сюда это условие, когда будут все классы-токены.
            int count = 0;
            for(String s : preparedExpression){
                if(count >= 0) {
                    if(brackets.containsValue(s)){
                        count++;
                    } else if(brackets.containsKey(s)){
                        count--;
                    }
                }
            }
            return count == 0;
        }

    public boolean isBracketsOrderCorrectForTest() throws RuntimeException {
//            if (isValidTokens()) { //todo: добавить сюда это условие, когда будут все классы-токены.
        int count = 0;
        for(String s : preparedExpression){
            if(count >= 0) {
                if(brackets.containsValue(s)){
                    count++;
                } else if(brackets.containsKey(s)){
                    count--;
                }
            }
        }
        return count == 0;
    }

//    /**
//     * Method for checking the nesting of parentheses in a custom expression.
//     */
//    private boolean isBracketsOrderCorrect() throws RuntimeException {
//        if (isValidTokens()) {
//            Deque<Character> stack = new LinkedList<>();
//            for (char c : cleanExpression.toCharArray()) {
//                //если мапа содержит значение "с" (откр скобка), то пушим ее в стек.
//                if (brackets.containsValue(c)) {
//                    stack.push(c);
//                    //иначе если перед нами закрыв скобка (ключ "с"), то:
//                } else if (brackets.containsKey(c)) {
//                    //если стек пустой или последнее значение стека != значению по ключу (откр скобка),
//                    // что означает что каждой закрыв скобке должна соответствовать (быть в стеке) откр скобка:
//                    if (stack.isEmpty() || stack.pop() != brackets.get(c)) {
//                        return false;
//                    }
//                }
//            }
//            return stack.isEmpty(); //или tru?
//        } else throw new RuntimeException("Invalid characters used!");
//    }
//
//
//    /**
//     * Method for checking if a custom expression contains only valid tokens.
//     */
//    private boolean isValidTokens() throws RuntimeException {
//        if (isNotEmpty()) {
//            for (String item : cleanExpression.split("")) {
//                if (!tokens.contains(item)) return false;
//            }
//            return true;
//        } else throw new RuntimeException("The expression is empty!");
//    }
}
