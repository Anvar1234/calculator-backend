package ru.yandex.kingartaved.preparator.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.preparator.Preparatorable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionPreparerTest {


    //TODO: тест должен быть независим и локализован. И еще предполагаю,
    // что позитивные тесты должны рассматривать разные условия, то есть разные условия if в цикле for:
    //например, первый if - это если символ это число и (после всего) if буфер не пуст:
    @Test
    public void getExpressionMembersTest1() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String expression = "11.1+ 1";
        List<String> expectedList = List.of("11.1", "+", "1");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        System.out.println("actual : " + actual);
        assertEquals(expectedList, actual);
    }

    //иначе, если входит не число и если буфер не пуст:
    @Test
    public void getExpressionMembersTest2() {
        String expression = "1-";
        List<String> expectedList = List.of("1", "-");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        System.out.println("actual : " + actual);
        Assertions.assertEquals(expectedList, actual);
    }

    //иначе, если входит не число (унарный минус или скобка в самом начале) и буфер пуст:
    @Test
    public void getExpressionMembersTest3() {
        String expression = "-(1+1)";
        List<String> expectedList = List.of("-", "(", "1", "+", "1", ")");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        System.out.println("actual : " + actual);
        Assertions.assertEquals(expectedList, actual);
    }

    //после всего, если буфер не пуст (например, последним из выражения в условие входит число):
    @Test
    public void getExpressionMembersTest4() {
        String expression = "-1+11";
        List<String> expectedList = List.of("-", "1", "+", "11");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        System.out.println("actual : " + actual);
        Assertions.assertEquals(expectedList, actual);
    }

    //TODO: я думаю негативные тесты должны быть простыми, например с простым выражением, которое должно дать очевидное решение, но не дает.
    @Test
    public void getExpressionMembersNegativeTest() {
        String expression = "1";
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        List<String> unexpectedList = List.of(" ");
        Assertions.assertNotEquals(unexpectedList, actual);
    }

    @Test
    public void unaryMinusHandlerTest1() {
        String expression = "-11";
        ExpressionPreparer preparer = new ExpressionPreparer(expression);
        List<String> expected = List.of("0", "-", "11");
        List<String> actual = preparer.unaryMinusHandlerForTest();
        System.out.println("actual : " + actual);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void unaryMinusHandlerTest2() {
        String expression = "1-2-(-1+1)";
        ExpressionPreparer preparer = new ExpressionPreparer(expression);
        List<String> expected = List.of("1", "-", "2", "-", "(", "0", "-", "1", "+", "1", ")");
        List<String> actual = preparer.unaryMinusHandlerForTest();
        System.out.println("actual : " + actual);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void unaryMinusHandlerNegativeTest() {
        String expression = "-1";
        ExpressionPreparer preparer = new ExpressionPreparer(expression);
        List<String> unexpected = List.of("-", "1");
        List<String> actual = preparer.unaryMinusHandlerForTest();
        System.out.println("actual : " + actual);
        Assertions.assertNotEquals(unexpected,actual);
    }
}

