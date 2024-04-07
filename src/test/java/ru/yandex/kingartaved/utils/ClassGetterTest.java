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
    //TODO: для всего списка классов сделать:

    @Test
    public void getClassesNegativeTest() throws IOException, ClassNotFoundException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.preparator.impl.ExpressionPreparator"));
        Assertions.assertNotEquals(expected.get(0), ClassGetter.getClasses().get(0));
    }

    @Test
    public void getBracketClassesTest1() throws ClassNotFoundException, IOException {
        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.brackets.RoundOpenBracket"));
        Assertions.assertEquals(expected.get(0), ClassGetter.getBracketClasses().get(1));
    }
    @Test
    public void getBracketClassesTest() throws ClassNotFoundException, IOException {//todo: переделать метод, этот просто для проверки чего-то писал
//        List<Class<?>> expected = DirAnalizator.getBracketClasses();
//        for(Class c : expected){
//            System.out.println(c.getSimpleName());
//        }
    }



}
