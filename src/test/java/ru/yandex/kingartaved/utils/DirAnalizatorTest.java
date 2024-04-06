package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirAnalizatorTest {
    //для одного конкретного класса:
    @Test
    public void getClassesTest1() throws IOException, ClassNotFoundException {
        DirAnalizator analizator = new DirAnalizator();
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.RoundOpenBracket"));
        Assertions.assertEquals(expected.get(0),  analizator.getClasses().get(0));
    }
    //TODO: для всего списка сделать:

    @Test
    public void getClassesNegativeTest() throws IOException, ClassNotFoundException {
        DirAnalizator analizator = new DirAnalizator();
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.preparator.impl.ExpressionPreparator"));
        Assertions.assertNotEquals(expected.get(0),  analizator.getClasses().get(0));
    }



}
