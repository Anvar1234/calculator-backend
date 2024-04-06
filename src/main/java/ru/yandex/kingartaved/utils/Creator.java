package ru.yandex.kingartaved.utils;

import ru.yandex.kingartaved.math.Bracketable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creator {
    private static final List<Class<?>> bracketClasses;

    static {
        try {
            bracketClasses = DirAnalizator.getBracketClasses();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final List<Class<?>> classes;

    static {
        try {
            classes = DirAnalizator.getClasses();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Class<?>> getClasses() {
        return classes;
    }

    public static List<Class<?>> getBracketClasses() { //сделать потом приватным, возможно.
        return bracketClasses;
    }

    public static void createBracketMap() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {  //сделать потом возвр Map<String, String>
        Map<String, String> bracketsMap = new HashMap<>();
        List<Class<?>> bracketClasses = getBracketClasses();
        String suffixOpen = PropertiesUtil.get("app.openbracket.suffix");
        String suffixClosing = PropertiesUtil.get("app.closingbracket.suffix");

        int count = 0;
        //Для информации: у нас всегда минимум 2 класса в пакете brackets.
        for (int i = 0; i < bracketClasses.size(); i++) {
            Class<?> classToValue = bracketClasses.get(i);//не забыть увеличить count.
            if (classToValue.getSimpleName().contains("Open")) {//если класс содержит Опен, то это по-любому значение, а не ключ, тогда:
                System.out.println("count1 : " + i);
                System.out.println("classToValue : " + classToValue);
                String nameWithoutSuffixOpen = classToValue.getSimpleName().replaceAll(suffixOpen, ""); //получили например "Round".
                System.out.println("nameWithoutSuffixOpen : " + nameWithoutSuffixOpen);
                while (count < bracketClasses.size()) {
                    Class<?> classToKey = bracketClasses.get(count++);
                    if (classToKey.getSimpleName().contains("Closing")) {//если класс содержит Closing, то это по-любому ключ, а не значение, тогда:
                        System.out.println("classToKey : " + classToKey);
                        String nameWithoutSuffixClosing = classToKey.getSimpleName().replaceAll(suffixClosing, ""); // тоже получили Round.
                        System.out.println("nameWithoutSuffixClosing : " + nameWithoutSuffixClosing);
                        if (nameWithoutSuffixOpen.equals(nameWithoutSuffixClosing)) { //например Round(OpenBracket) = Round(ClosingBracket) && что-то еще нужно сравнивать!
                            Constructor<?> valueConstructor = classToValue.getConstructor();
                            Bracketable valueObj = (Bracketable) valueConstructor.newInstance();
                            String value = valueObj.getBracket();
                            Constructor<?> keyConstructor = classToKey.getConstructor();
                            Bracketable keyObj = (Bracketable) keyConstructor.newInstance();
                            String key = keyObj.getBracket();
                            bracketsMap.put(key, value);
                        }
                    }
                }
            }
            count = 0;
        }
        System.out.println("Map : " + bracketsMap);
    }
}

