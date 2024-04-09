package ru.yandex.kingartaved.validator.impl;

import ru.yandex.kingartaved.preparator.Preparatorable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ExpressionValidator {
//    private final List<String> preparedExpression;
//
//    public ExpressionValidator(Preparatorable preparatorable) {
//        this.preparedExpression = preparatorable.getPreparedExpression();
//    }
//
//
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
//
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
