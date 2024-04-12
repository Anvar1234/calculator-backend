package ru.yandex.kingartaved.preparator.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExpressionPreparatorTest {
    //TODO: тест должен быть независим и локализован. И еще предполагаю,
    // что позитивные тесты должны рассматривать разные условия, то есть разные условия if в цикле for:
    //например, первый if - это если символ это число и (после всего) if буфер не пуст:
//    @Test
//    public void getExpressionMembersForTest1() {
//        String expression = "111";
//        List<String> expectedList = List.of("111");
//        ExpressionPreparator expressionPreparator = new ExpressionPreparator(expression);
//        List<String> actual = expressionPreparator.getExpressionMembersForTest();
//        Assertions.assertEquals(expectedList, actual, "Если false, то знай, что должно получиться 111 как число.");
//    }
//
//    //иначе, если входит не число и если буфер не пуст:
//    @Test
//    public void getExpressionMembersForTest2() {
//        String expression = "1-";
//        List<String> expectedList = List.of("1", "-");
//        ExpressionPreparator expressionPreparator = new ExpressionPreparator(expression);
//        List<String> actual = expressionPreparator.getExpressionMembersForTest();
//        Assertions.assertEquals(expectedList, actual);
//    }
//
//    //иначе, если входит не число (унарный минус или скобка в самом начале) и буфер пуст:
//    @Test
//    public void getExpressionMembersForTest3() {
//        String expression = "-(1+1)";
//        List<String> expectedList = List.of("-", "(", "1", "+", "1", ")");
//        ExpressionPreparator expressionPreparator = new ExpressionPreparator(expression);
//        List<String> actual = expressionPreparator.getExpressionMembersForTest();
//        Assertions.assertEquals(expectedList, actual);
//    }
//
//    //после всего, если буфер не пуст (например, последним из выражения в условие входит число):
//    @Test
//    public void getExpressionMembersForTest4() {
//        String expression = "-1+11";
//        List<String> expectedList = List.of("-", "1", "+", "11");
//        ExpressionPreparator expressionPreparator = new ExpressionPreparator(expression);
//        List<String> actual = expressionPreparator.getExpressionMembersForTest();
//        Assertions.assertEquals(expectedList, actual);
//    }
//
//    //TODO: я думаю негативные тесты должны быть простыми, например с простым выражением, которое должно дать очевидное решение, но не дает.
//    @Test
//    public void getExpressionMembersNegativeTest1() {
//        String expression = "1";
//        ExpressionPreparator expressionPreparator = new ExpressionPreparator(expression);
//        List<String> actual = expressionPreparator.getExpressionMembersForTest();
//        List<String> unexpectedList = List.of(" ");
//        Assertions.assertNotEquals(unexpectedList, actual);
//    }
//
//    @Test
//    public void unaryMinusHandlerTest1() {
//        String expression = "-11";
//        ExpressionPreparator preparator = new ExpressionPreparator(expression);
//        List<String> expected = List.of("0", "-", "11");
//        List<String> actual = preparator.unaryMinusHandlerForTest();
//        Assertions.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void unaryMinusHandlerTest2() {
//        String expression = "1-2-(-1+1)";
//        ExpressionPreparator preparator = new ExpressionPreparator(expression);
//        List<String> expected = List.of("1", "-", "2", "-", "(", "0", "-", "1", "+", "1", ")");
//        List<String> actual = preparator.unaryMinusHandlerForTest();
//        Assertions.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void unaryMinusHandlerNegativeTest() {
//        String expression = "-1";
//        ExpressionPreparator preparator = new ExpressionPreparator(expression);
//        List<String> expected = List.of("-", "1");
//        List<String> actual = preparator.unaryMinusHandlerForTest();
//        Assertions.assertNotEquals(expected,actual);
//    }
}

