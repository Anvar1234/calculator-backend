package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class CreatorTest {

    @Test
    public void createBracketMap() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { //Map<String, String>
       Map<String, String> bracketsMap = Creator.createBracketMap();
        System.out.println("Map : " + bracketsMap);
    }


}
