package ru.yandex.kingartaved.validator.impl;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.preparer.impl.ExpressionPreparer;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionValidatorTest {
    @Test
    public void checkLastTokenTest1() {
        String expression = "1+22*3/2-1+(){}[]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean result = validator.checkLastTokenForTest();
        System.out.println("result : " + result);
    }

    @Test
    public void checkLastTokenTest2() {
        String expression = "(1+2)";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean result = validator.checkLastTokenForTest();
        System.out.println("result : " + result);
    }

    //_________________________________________________________________________
    @Test
    public void isValidTokensTest() {
        String expression = "1+22*3/2-1+(){}[]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        assertTrue(validator.isValidTokensForTest());
    }

    //_________________________________________________________________________
    @Test
    public void isBracketsOrderCorrectTest1() {
        String expression = "[]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }

    @Test
    public void isBracketsOrderCorrectTest2() {
        String expression = "{]{){([)}]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }

    @Test
    public void isBracketsOrderCorrectTest3() {
        String expression = "1+22*3/2-1+(){}[]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }

    @Test
    public void isBracketsOrderCorrectNegativeTest1() {
        String expression = "[";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        System.out.println("actual1 : " + actual);
        assertNotEquals(true, actual);
    }

    @Test
    public void isBracketsOrderCorrectNegativeTest2() {
        String expression = "{[)]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertNotEquals(false, actual);
    }

    @Test
    public void isBracketsOrderCorrectNegativeTest3() {
        String expression = "]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        System.out.println("actual3 : " + actual);
        assertNotEquals(true, actual);
    }

    //---------------------------------------------------------------------
    @Test
    public void isValidExpressionTest() {
        String expression = "1+22.2*3/2-1+(){}[]";
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isValidExpressionForTest();
        assertTrue(actual);
    }
}
