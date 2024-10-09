package ru.yandex.kingartaved.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.converter.Converterable;
import ru.yandex.kingartaved.converter.impl.ReversePolishNotationConverter;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.preparer.impl.ExpressionPreparer;
import ru.yandex.kingartaved.service.impl.ReversePolishNotationCalculator;
import ru.yandex.kingartaved.validator.impl.ExpressionValidator;

public class ReversePolishNotationCalculatorTest {
    @Test
    public void calculateTest1() {
        String expression = "(1+1)+5/2";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new ReversePolishNotationConverter(preparator, validator);
        ReversePolishNotationCalculator calculable = new ReversePolishNotationCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        Assertions.assertEquals(4.5, result);
    }

    @Test
    public void calculateTest2() {
        String expression = "-(-11.0-(1+2))";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new ReversePolishNotationConverter(preparator, validator);
        ReversePolishNotationCalculator calculable = new ReversePolishNotationCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        Assertions.assertEquals(14.0, result);
    }

    @Test
    public void calculateTest3() {
        String expression = "-(-11.0-(1+2))/2.5+(-5.5+2.7)";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new ReversePolishNotationConverter(preparator, validator);
        ReversePolishNotationCalculator calculable = new ReversePolishNotationCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        Assertions.assertEquals(2.8, result);
    }

    @Test
    public void calculateTest4() {
        String expression = "(10.0/2+2)*2^3-100*1.5";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new ReversePolishNotationConverter(preparator, validator);
        ReversePolishNotationCalculator calculable = new ReversePolishNotationCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        Assertions.assertEquals(-94, result);
    }

    @Test
    public void calculateTest5() {
        String expression = "(0/2+2)*2^3";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new ReversePolishNotationConverter(preparator, validator);
        ReversePolishNotationCalculator calculable = new ReversePolishNotationCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        Assertions.assertEquals(16.0, result);
    }

    @Test
    public void calculateTest6() {
        String expression = "-1*0^2/2+15.5";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new ReversePolishNotationConverter(preparator, validator);
        ReversePolishNotationCalculator calculable = new ReversePolishNotationCalculator(converterable);
        double result = calculable.calculateForTest().getFirst();
        Assertions.assertEquals(15.5, result);
    }
}

