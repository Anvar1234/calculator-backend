package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.math.Tokenable;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GetterTest {

    @Test
    public void getTokenableClassesTest() {

        List<Class<Tokenable>> classes = Getter.getTokenableClassesForTest();
        List<String> simpleNameClasses = new ArrayList<>();
        for (Class<Tokenable> cl : classes) {
            simpleNameClasses.add(cl.getSimpleName());
        }
        String expected = "CurlyOpenBracket";
        String actual = simpleNameClasses.get(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getTokenableInstancesTest() {
        List<Tokenable> instances = Getter.getTokenableInstancesForTest();
        String expected = "CurlyOpenBracket";
        String actual = instances.get(1).getClass().getSimpleName();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getValidTokensTest() {
        Set<String> validTokens = Getter.getValidTokensForTest();
        String expected = "{";
        Iterator<String> iterator = validTokens.iterator();
        String actual = "";
        for (int i = 0; i < 2; i++) {
            actual = iterator.next();
        }
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getPriorityOfTokenTest() {
        int expected = 2;
        int actual = Getter.getPriorityByTokenForTest("+");
        Assertions.assertEquals(expected, actual);
    }
}
