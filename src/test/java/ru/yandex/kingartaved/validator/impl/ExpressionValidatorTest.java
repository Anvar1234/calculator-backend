package ru.yandex.kingartaved.validator.impl;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.preparer.impl.ExpressionPreparer;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionValidatorTest {

    @Test
    public void isValidExpressionTest() {
        String expression = "(((2)-1)*20.2)^2/2";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isValidExpressionForTest();
        assertTrue(actual);
    }

    @Test
    public void isValidExpressionNegativeTest() {
        String expression = "(((2))) 2 + 2";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isValidExpressionForTest();
        assertFalse(actual);
    }

    //---------------------------------------------------------------------

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
        String expression = "[1]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }

    @Test
    public void isBracketsOrderCorrectTest2() {
        String expression = "{1]-{1)+{([1)}]";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }

    @Test
    public void isBracketsOrderCorrectTest3() {
        String expression = "(((2))) 2 + 2";
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
        String expression = "{[1)]";
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
        assertFalse(actual);
    }

    //---------------------------------------------------------------------
    @Test
    public void isNumbersValidPositionTest1(){
        String expression = "2";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isNumbersValidPositionsForTest();
        assertTrue(actual);
    }

    @Test
    public void isNumbersValidPositionTest2(){
        String expression = "2+1";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isNumbersValidPositionsForTest();
        assertTrue(actual);
    }

    @Test
    public void isNumbersValidPositionTest3(){
        String expression = "(2+1)";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isNumbersValidPositionsForTest();
        assertTrue(actual);
    }

    @Test
    public void isNumbersValidPositionNegativeTest1(){
        String expression = "(((2))) 2 + 2";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isNumbersValidPositionsForTest();
        assertFalse(actual);
    }
    @Test
    public void isNumbersValidPositionNegativeTest2(){
        String expression = "2(1)";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isNumbersValidPositionsForTest();
        assertFalse(actual);
    }

    //---------------------------------------------------------------------
    @Test
    public void isOperatorsValidPositionTest1(){
        String expression = "2+1";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isOperatorsValidPositionForTest();
        assertTrue(actual);
    }
    @Test
    public void isOperatorsValidPositionTest2(){
        String expression = "2+1*(1*9)/9-3^2";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isOperatorsValidPositionForTest();
        assertTrue(actual);
    }
    @Test
    public void isOperatorsValidPositionTest3(){
        String expression = "2+1*(1*9)   9-3^2"; //здесь ждем позитив, так как проверку на корректную расстановку чисел мы проверяем в другом методе.
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isOperatorsValidPositionForTest();
        assertTrue(actual);
    }

    @Test
    public void isOperatorsValidPositionTest4(){
        String expression = ")+(";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isOperatorsValidPositionForTest();
        assertTrue(actual);
    }

    @Test
    public void isOperatorsValidPositionNegativeTest1(){
        String expression = "+1";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isOperatorsValidPositionForTest();
        assertFalse(actual);
    }

    @Test
    public void isOperatorsValidPositionNegativeTest2(){
        String expression = "2+";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isOperatorsValidPositionForTest();
        assertFalse(actual);
    }
    @Test
    public void isOperatorsValidPositionNegativeTest3(){
        String expression = "(+)";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isOperatorsValidPositionForTest();
        assertFalse(actual);
    }

    //---------------------------------------------------------------------
    @Test
    public void notNearBracketsTest(){
        String expression = "((1))";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.notNearBracketsForTest();
        assertTrue(actual);
    }
    @Test
    public void notNearBracketsNegativeTest1(){
        String expression = "()";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.notNearBracketsForTest();
        assertFalse(actual);
    }
    @Test
    public void notNearBracketsNegativeTest2(){
        String expression = ")(";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.notNearBracketsForTest();
        assertFalse(actual);
    }
}
