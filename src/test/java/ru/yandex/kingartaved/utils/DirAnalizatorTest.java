package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class DirAnalizatorTest {
    //для одного конкретного класса:
    @Test
    public void getClassesTest1() throws IOException, ClassNotFoundException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.Cl"));//todo: заменить на нормальный класс позже
        Assertions.assertEquals(expected.get(0), DirAnalizator.getClasses().get(0));
    }
    //TODO: для всего списка классов сделать:

    @Test
    public void getClassesNegativeTest() throws IOException, ClassNotFoundException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.preparator.impl.ExpressionPreparator"));
        Assertions.assertNotEquals(expected.get(0), DirAnalizator.getClasses().get(0));
    }


    public void getBracketClassesTest() {
//        DirAnalizator analizator = new DirAnalizator();
//        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.brackets.RoundOpenBracket"));
//        Assertions.assertEquals(expected.get(0),  analizator.getClasses().get(0));
//    }

    }
}
