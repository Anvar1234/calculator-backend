package ru.yandex.kingartaved.validator.impl;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparator.Preparatorable;
import ru.yandex.kingartaved.preparator.impl.ExpressionPreparer;
import ru.yandex.kingartaved.utils.Getter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionValidatorTest {
    @Test
    public void checkLastTokenTest1() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "1+22*3/2-1+(){}[]";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean result = validator.checkLastTokenForTest();
        System.out.println("result : " + result);
    }
    @Test
    public void checkLastTokenTest2() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "(1+2)";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean result = validator.checkLastTokenForTest();
        System.out.println("result : " + result);
    }
    //_________________________________________________________________________
    @Test
    public void isValidTokensTest() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "1+22*3/2-1+(){}[]";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        assertTrue(validator.isValidTokensForTest());
    }
    //_________________________________________________________________________
    @Test
    public void isBracketsOrderCorrectTest1() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "[]";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }
    @Test
    public void isBracketsOrderCorrectTest2() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "{]{){([)}]";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }
    @Test
    public void isBracketsOrderCorrectTest3() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "1+22*3/2-1+(){}[]";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertTrue(actual);
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest1() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "[";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        System.out.println("actual1 : " + actual);
        assertNotEquals(true, actual);
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest2() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "{[)]";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        assertNotEquals(false, actual);
    }
    @Test
    public void isBracketsOrderCorrectNegativeTest3() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "]";
        Preparatorable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isBracketsOrderCorrectForTest();
        System.out.println("actual3 : " + actual);
        assertNotEquals(true, actual);
    }
    //---------------------------------------------------------------------
    @Test
    public void isValidExpressionTest() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "1+22.2*3/2-1+(){}[]"; //ошибка в методе isValidTokens. Если входит не отдельный токен, а напр число, то конечно его нет в VALID_TOKENS.
        Preparatorable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        boolean actual = validator.isValidExpressionForTest();
        assertTrue(actual);
    }
}
