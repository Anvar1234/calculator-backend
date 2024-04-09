package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class CreatorTest {

    @Test
    public void createBracketMapTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { //Map<String, String>
        Map<String, String> actualMap = Creator.getBracketMap();
        Map<String, String> expectedMap = Map.of(")", "(", "}", "{", "]", "[");
        Assertions.assertEquals(expectedMap, actualMap);
    }

    @Test
    public void createBracketMapNegativeTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { //Map<String, String>
        Map<String, String> actualMap = Creator.getBracketMap();
        Map<String, String> expectedMap = Map.of("{", "}", ")", "(", "]", "[");
        Assertions.assertNotEquals(expectedMap, actualMap);
    }

    @Test
    public void getClassesTest() throws IOException, ClassNotFoundException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.Cl"));//todo: заменить на нормальный класс позже
        Assertions.assertEquals(expected.get(0), Creator.ClassGetter.getClassesForTest().get(0));
    }

    @Test
    public void getClassesNegativeTest() throws IOException, ClassNotFoundException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.preparator.impl.ExpressionPreparator"));
        Assertions.assertNotEquals(expected.get(0), Creator.ClassGetter.getClassesForTest().get(0));
    }

    @Test
    public void getBracketClassesTest1() throws ClassNotFoundException, IOException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyOpenBracket"));
        Assertions.assertEquals(expected.get(0), Creator.ClassGetter.getBracketClassesForTest().get(1));
    }
    @Test
    public void getBracketClassesTest2() throws ClassNotFoundException, IOException {
        List<Class<?>> expected = List.of(
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyClosingBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyOpenBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.RoundClosingBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.RoundOpenBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.SquareClosingBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.SquareOpenBracket"));

        Assertions.assertEquals(expected, Creator.ClassGetter.getBracketClassesForTest());
    }

    @Test
    public void getBracketClassesNegativeTest1() throws ClassNotFoundException, IOException {
        Class<?> expected = Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyOpenBracket");
        Assertions.assertNotEquals(expected, Creator.ClassGetter.getBracketClassesForTest().get(0));
    }

    @Test
    public void getBracketClassesNegativeTest2() throws ClassNotFoundException, IOException {
        List<Class<?>> expected = List.of(
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyClosingBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyOpenBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.RoundOpenBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.RoundClosingBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.SquareOpenBracket"),
                Class.forName("ru.yandex.kingartaved.math.impl.brackets.SquareClosingBracket"));

        Assertions.assertNotEquals(expected, Creator.ClassGetter.getBracketClassesForTest());
    }

}
