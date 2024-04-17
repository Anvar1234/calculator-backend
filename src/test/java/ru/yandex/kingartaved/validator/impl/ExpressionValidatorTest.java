package ru.yandex.kingartaved.validator.impl;

import org.junit.jupiter.api.Assertions;
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
        boolean actual = validator.checkLastTokenForTest();
        Assertions.assertTrue(actual);
    }
    @Test
    public void checkLastTokenTest2() {
        String expression = "1+2";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.checkLastTokenForTest();
        Assertions.assertTrue(actual);
    }
    @Test
    public void checkLastTokenNegativeTest() {
        String expression = "1+";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.checkLastTokenForTest();
        assertNotEquals(true, actual);
    }

    //_________________________________________________________________________
    @Test
    public void isValidTokensTest() {
        String expression = "1+22*3/2-1+(){}[]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        assertTrue(validator.isValidTokensForTest());
    }
    @Test
    public void isValidTokensNegativeTest() {
        String expression = "1+22*3/2@-1+(){}[]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        assertNotEquals(true, validator.isValidTokensForTest());
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
        assertNotEquals(true, actual);
    }

    //---------------------------------------------------------------------
    @Test
    public void isValidExpressionTest() {
        String expression = "1+22.2*3/2-1^3+(){}[]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isValidExpressionForTest();
        assertTrue(actual);
    }
}
