package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
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
        Map<String, String> expectedMap = Map.of("{","}",")", "(",   "]", "[");
        Assertions.assertNotEquals(expectedMap, actualMap);
    }


}
