package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class ClassGetterTest {
    //для одного конкретного класса:
    @Test
    public void getClassesTest1() throws IOException, ClassNotFoundException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.Cl"));//todo: заменить на нормальный класс позже
        Assertions.assertEquals(expected.get(0), ClassGetter.getClasses().get(0));
    }
    //TODO: для всего списка классов сделать, как getBracketClassesTest2:

    @Test
    public void getClassesNegativeTest1() throws IOException, ClassNotFoundException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.preparator.impl.ExpressionPreparator"));
        Assertions.assertNotEquals(expected.get(0), ClassGetter.getClasses().get(0));
    }

    @Test
    public void getBracketClassesTest1() throws ClassNotFoundException, IOException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyOpenBracket"));
        Assertions.assertEquals(expected.get(0), ClassGetter.getBracketClasses().get(1));
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

        Assertions.assertEquals(expected, ClassGetter.getBracketClasses());
    }

    @Test
    public void getBracketClassesNegativeTest1() throws ClassNotFoundException, IOException {
        Class<?> expected = Class.forName("ru.yandex.kingartaved.math.impl.brackets.CurlyOpenBracket");
        Assertions.assertNotEquals(expected, ClassGetter.getBracketClasses().get(0));
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

        Assertions.assertNotEquals(expected, ClassGetter.getBracketClasses());
    }

}




