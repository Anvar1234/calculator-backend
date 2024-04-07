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
            bracketClasses = ClassGetter.getBracketClasses();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static final List<Class<?>> classes;

    static {
        try {
            classes = ClassGetter.getClasses();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Class<?>> getClasses() { //сделать потом приватным, возможно.
        return classes;
    }






    public static List<Class<?>> getBracketClasses() { //сделать потом приватным, возможно.
        return bracketClasses;
    }

    public static Map<String, String> getBracketMap() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return createBracketMap();
    }

    /**
     * Разделить метод на приватные подметоды, иначе сложно читать. То есть необходимо осуществить декомпозицию.
     * Также необходимо избавиться от знака "?", мы же знаем, что в коллекции будут только Bracketable объекты.
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static Map<String, String> createBracketMap() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {  //сделать потом приватным.
        Map<String, String> bracketsMap = new HashMap<>();
        List<Class<?>> bracketClasses = getBracketClasses();
        String suffixOpen = PropertiesUtil.get("app.openbracket.suffix");
        String suffixClosing = PropertiesUtil.get("app.closingbracket.suffix");

        int count = 0;
        //Для информации: у нас всегда минимум 2 класса в пакете brackets.
        for (int i = 0; i < bracketClasses.size(); i++) {
            Class<?> classToValue = bracketClasses.get(i);
            if (classToValue.getSimpleName().contains("Open")) {//если класс содержит Опен, то это по-любому значение, а не ключ, тогда:
                System.out.println("classToValue : " + classToValue);
                String nameWithoutSuffixOpen = classToValue.getSimpleName().replaceAll(suffixOpen, ""); //получили например "Round".
                System.out.println("nameWithoutSuffixOpen : " + nameWithoutSuffixOpen);
                while (count < bracketClasses.size()) {//прогоняем цикл по всем классам в пакете brackets, чтобы найти пару closing нашему классу get(i).
                    Class<?> classToKey = bracketClasses.get(count++);
                    if (classToKey.getSimpleName().contains("Closing")) {//если класс содержит Closing, то это по-любому ключ, а не значение, тогда:
                        System.out.println("classToKey : " + classToKey);
                        String nameWithoutSuffixClosing = classToKey.getSimpleName().replaceAll(suffixClosing, ""); // тоже получили Round.
                        System.out.println("nameWithoutSuffixClosing : " + nameWithoutSuffixClosing);
                        if (nameWithoutSuffixOpen.equals(nameWithoutSuffixClosing)) { //например Round(OpenBracket) = Round(ClosingBracket) && что-то еще нужно сравнивать!
                            Map<String, String> simpleMap = createSimpleMap(classToKey, classToValue);
                            for (Map.Entry<String, String> m : simpleMap.entrySet()){
                                bracketsMap.putIfAbsent(m.getKey(), m.getValue());//putIfAbsent Добавляет пары в мапу только если такого ключа в ней еще нет.
                            }
                        }
                    }
                }
            }
            count = 0;//обнуляем count.
        }
        return bracketsMap;
    }

    private static Map<String, String> createSimpleMap(Class<?> keyClass, Class<?> valueClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> valueConstructor = valueClass.getConstructor();
        Bracketable valueObj = (Bracketable) valueConstructor.newInstance();
        String value = valueObj.getBracket();
        Constructor<?> keyConstructor = keyClass.getConstructor();
        Bracketable keyObj = (Bracketable) keyConstructor.newInstance();
        String key = keyObj.getBracket();
        return Map.of(key, value);
    }
}

