package ru.yandex.kingartaved.utils;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.math.Tokenable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class GetterTest {

    @Test
    public void getClassesTest() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        List<Class<Tokenable>> classesList = Getter.getTokenableClasses();
        System.out.println("classesList : " + classesList);

        //создаем список instances экземпляров типа Tokenable:
        List<Tokenable> instances = new ArrayList<>();
        for (Class<Tokenable> cl : classesList) {
            Constructor<Tokenable> constructor = cl.getConstructor();
            instances.add(constructor.newInstance());
        }
        System.out.println("instances : " + instances);

        //пробуем получить все символы классов-токенов:
        Set<String> validTokens = new HashSet<>();
        for (Tokenable t : instances) {
            validTokens.add(t.getToken());
        }
        System.out.println("validTokens : " + validTokens);

        //допустим у нас есть сет цифр (так как других цифр быть не может и я думаю не надо создавать классы-токены для них),
        // сливаем воедино сет символов из классов-токенов и наш цифровой:
        Set<String> validNumberTokens = Set.of("0", "1", "2"); //получать это из проперти.
        validTokens.addAll(validNumberTokens);
        System.out.println("validTokens with nums: " + validTokens);

        //попробуем обратные действия - у нас есть список символов (типа нашего входного выражения), теперь
        // нужно по этому символу найти равнозначный ему объект типа Tokenable и дальше применить действие, которое в
        // классе-токене прописано (это сделать, когда создам классы-токены для операторов):
        List<Class<?>> classL = new ArrayList<>();
        for (String s : validTokens) {
            for (Tokenable ins : instances) {
                if (s.equals(ins.getToken())) {
                    classL.add(ins.getClass());
                }
            }
        }
        System.out.println("classL: " + classL);
    }

    @Test
    public void getValidTokensTest() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Set<String> validTokens = Getter.VALID_TOKENS;
        System.out.println("validTokens : " + validTokens);
    }

//    @Test
//    public void createBracketMapTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { //Map<String, String>
//        Map<String, String> actualMap = Creator.getBracketMap();
//        Map<String, String> expectedMap = Map.of(")", "(", "}", "{", "]", "[");
//        Assertions.assertEquals(expectedMap, actualMap);
//    }
//
//    @Test
//    public void createBracketMapNegativeTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException { //Map<String, String>
//        Map<String, String> actualMap = Creator.getBracketMap();
//        Map<String, String> expectedMap = Map.of("{", "}", ")", "(", "]", "[");
//        Assertions.assertNotEquals(expectedMap, actualMap);
//    }
//
//    @Test
//    public void getClassesTest() throws IOException, ClassNotFoundException {
//        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.Cl"));//todo: заменить на нормальный класс позже
//        Assertions.assertEquals(expected.get(0), Creator.ClassGetter.getClassesForTest().get(0));
//    }
//
//    @Test
//    public void getClassesNegativeTest() throws IOException, ClassNotFoundException {
//        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.preparator.impl.ExpressionPreparator"));
//        Assertions.assertNotEquals(expected.get(0), Creator.ClassGetter.getClassesForTest().get(0));
//    }
//
//    @Test
//    public void getBracketClassesTest1() throws ClassNotFoundException, IOException {
//        List<Class<?>> expected = List.of(Class.forName("ru.yandex.kingartaved.math.impl.CurlyOpenBracket"));
//        Assertions.assertEquals(expected.get(0), Creator.ClassGetter.getBracketClassesForTest().get(1));
//    }
//    @Test
//    public void getBracketClassesTest2() throws ClassNotFoundException, IOException {
//        List<Class<?>> expected = List.of(
//                Class.forName("ru.yandex.kingartaved.math.impl.CurlyClosingBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.CurlyOpenBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.RoundClosingBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.RoundOpenBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.SquareClosingBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.SquareOpenBracket"));
//
//        Assertions.assertEquals(expected, Creator.ClassGetter.getBracketClassesForTest());
//    }
//
//    @Test
//    public void getBracketClassesNegativeTest1() throws ClassNotFoundException, IOException {
//        Class<?> expected = Class.forName("ru.yandex.kingartaved.math.impl.CurlyOpenBracket");
//        Assertions.assertNotEquals(expected, Creator.ClassGetter.getBracketClassesForTest().get(0));
//    }
//
//    @Test
//    public void getBracketClassesNegativeTest2() throws ClassNotFoundException, IOException {
//        List<Class<?>> expected = List.of(
//                Class.forName("ru.yandex.kingartaved.math.impl.CurlyClosingBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.CurlyOpenBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.RoundOpenBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.RoundClosingBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.SquareOpenBracket"),
//                Class.forName("ru.yandex.kingartaved.math.impl.SquareClosingBracket"));
//
//        Assertions.assertNotEquals(expected, Creator.ClassGetter.getBracketClassesForTest());
//    }

}
