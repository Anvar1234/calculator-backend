package ru.yandex.kingartaved.validator.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.preparator.impl.ExpressionPreparator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionValidatorTest {
    @Test
    public void isBracketsOrderCorrectTest1(){
        String expression = "[]";
        Preparatorable preparator = new ExpressionPreparator(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }
    @Test
    public void isBracketsOrderCorrectTest2(){
        String expression = "{]{){([)}]";
        Preparatorable preparator = new ExpressionPreparator(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest1(){
        String expression = "[";
        Preparatorable preparator = new ExpressionPreparator(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertNotEquals(true, actual);
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest2(){
        String expression = "{[)]";
        Preparatorable preparator = new ExpressionPreparator(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertNotEquals(false, actual);
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest3(){
        String expression = "]";
        Preparatorable preparator = new ExpressionPreparator(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertNotEquals(true, actual);
    }

}
