package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirAnalizatorTest {
    @Test
    public void getClassesTest() throws IOException, ClassNotFoundException {
        DirAnalizator analizator = new DirAnalizator();
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.RoundOpenBracket"));
        Assertions.assertEquals(expected,  analizator.getClasses());
    }

    @Test
    public void getClassesNegativeTest() throws IOException, ClassNotFoundException {
        DirAnalizator analizator = new DirAnalizator();
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.preparator.impl.ExpressionPreparator"));
        Assertions.assertNotEquals(expected,  analizator.getClasses());
    }



}
