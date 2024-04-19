package ru.yandex.kingartaved.preparer.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionPreparerTest {


    //первый if - это если символ это число и (после всего) if буфер не пуст:
    @Test
    public void getExpressionMembersTest1() {
        String expression = "11.1+ 1";
        List<String> expectedList = List.of("11.1", "+", "1");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        assertEquals(expectedList, actual);
    }

    //иначе, если входит не число и если буфер не пуст:
    @Test
    public void getExpressionMembersTest2() {
        String expression = "1-";
        List<String> expectedList = List.of("1", "-");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        assertEquals(expectedList, actual);
    }

    //иначе, если входит не число (унарный минус или скобка в самом начале) и буфер пуст:
    @Test
    public void getExpressionMembersTest3() {
        String expression = "-(1+1)";
        List<String> expectedList = List.of("-", "(", "1", "+", "1", ")");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        assertEquals(expectedList, actual);
    }

    //после всего, если буфер не пуст (например, последним из выражения в условие входит число):
    @Test
    public void getExpressionMembersTest4() {
        String expression = "-1+11";
        List<String> expectedList = List.of("-", "1", "+", "11");
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        assertEquals(expectedList, actual);
    }


    @Test
    public void getExpressionMembersNegativeTest() {
        String expression = "1";
        ExpressionPreparer expressionPreparer = new ExpressionPreparer(expression);
        List<String> actual = expressionPreparer.convertExpressionIntoMembersForTest();
        List<String> unexpectedList = List.of(" ");
        assertNotEquals(unexpectedList, actual);
    }

    @Test
    public void unaryMinusHandlerTest1() {
        String expression = "-1     1";
        ExpressionPreparer preparer = new ExpressionPreparer(expression);
        List<String> expected = List.of("0", "-", "11");
        List<String> actual = preparer.unaryMinusHandlerForTest();
        assertEquals(expected,actual);
    }

    @Test
    public void unaryMinusHandlerTest2() {
        String expression = "1-2-(-1+1)";
        ExpressionPreparer preparer = new ExpressionPreparer(expression);
        List<String> expected = List.of("1", "-", "2", "-", "(", "0", "-", "1", "+", "1", ")");
        List<String> actual = preparer.unaryMinusHandlerForTest();
        assertEquals(expected,actual);
    }

    @Test
    public void unaryMinusHandlerNegativeTest() {
        String expression = "-1";
        ExpressionPreparer preparer = new ExpressionPreparer(expression);
        List<String> unexpected = List.of("-", "1");
        List<String> actual = preparer.unaryMinusHandlerForTest();
        assertNotEquals(unexpected,actual);
    }
}

