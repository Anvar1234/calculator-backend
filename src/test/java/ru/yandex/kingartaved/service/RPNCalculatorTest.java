package ru.yandex.kingartaved.service;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.converter.Converterable;
import ru.yandex.kingartaved.converter.impl.RPNConverter;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.preparer.impl.ExpressionPreparer;
import ru.yandex.kingartaved.service.impl.RPNCalculator;
import ru.yandex.kingartaved.validator.impl.ExpressionValidator;

import java.util.List;

public class RPNCalculatorTest {
    @Test
    public void calculateTest1() {
        String expression = "(1+1)+5/2";//4.5
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConverted();
        System.out.println("convertedExpression : " + convertedExpression);
        RPNCalculator calculable = new RPNCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        System.out.println("result : " + result);
    }
    @Test
    public void calculateTest2() {
        String expression = "-(-11.0-(1+2))"; //14.0
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConverted();
        System.out.println("convertedExpression : " + convertedExpression);
        RPNCalculator calculable = new RPNCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        System.out.println("result : " + result);
    }
    @Test
    public void calculateTest3() {
        String expression = "-(-11.0-(1+2))/2.5+(-5.5+2.7)"; //2.8
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConverted();
        System.out.println("convertedExpression : " + convertedExpression);
        RPNCalculator calculable = new RPNCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        System.out.println("result : " + result);
    }

    @Test
    public void calculateTest4() {
        String expression = "(10.0/2+2)*2^3-100*1.5";//193
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConverted();
        System.out.println("convertedExpression : " + convertedExpression);
        RPNCalculator calculable = new RPNCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        System.out.println("result : " + result);
    }
    @Test
    public void calculateTest5() {
        String expression = "(0/2+2)*2^3";//16
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConverted();
        System.out.println("convertedExpression : " + convertedExpression);
        RPNCalculator calculable = new RPNCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        System.out.println("result : " + result);
    }
}
