package ru.yandex.kingartaved.utils;

import ru.yandex.kingartaved.math.Operation;
import ru.yandex.kingartaved.math.Tokenable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Getter {
    public static final Set<String> VALID_TOKENS;

    static {
        VALID_TOKENS = getValidTokens();
    }

    //метод для получения списка Tokenable КЛАССОВ-ТОКЕНОВ из директории:
    private static List<Class<Tokenable>> getTokenableClasses() { //todo: сделать потом приватным.
        //Получаем список<String> ссылок на классы:
        Path pathToImpl = Path.of(PropertiesUtil.get("app.impl.path"));
        try (Stream<Path> streamClassPaths = Files.list(pathToImpl)) {
            List<String> stringClassDirs = streamClassPaths.map(Path::toString).toList();
            //Работаем со списком ссылок на классы в папке impl, приводим в удобоваримый вид для класса Class
            //(удаляем .java, заменяем / на точки) и загоняем все это дело в список:
            List<Class<Tokenable>> classes = new ArrayList<>();
            String regex = PropertiesUtil.get("app.string.to.replace");
//        System.out.println("regex : " + regex);
            String suffix = PropertiesUtil.get("app.class.suffix.to.replace");
//        System.out.println("suffix : " + suffix);
            for (String s : stringClassDirs) {
                String sCopy;
                sCopy = s.replace('\\', '.')
                        .replace(regex, "")
                        .replace(suffix, "")
                        .trim();
//            System.out.println("s : " + sCopy);
                Path p = Path.of(s);
//            System.out.println("p : " + p);
                if (!Files.isDirectory(p) && Tokenable.class.isAssignableFrom(Class.forName(sCopy))) {//проверяем, не является ли значение в списке классов директорией И принадлежит ли к типу Tokenable.
                    classes.add((Class<Tokenable>) Class.forName(sCopy));//ошибка в том, что в список попадает и имя пакета brackets, нужно сделать проверку, что это именно файл.
                }
            }
            return classes;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка в указании пути или имени классов.");
        }
    }

    public static List<Class<Tokenable>> getTokenableClassesForTest() {
        return getTokenableClasses();
    }


    //метод для получения списка ЭКЗЕМПЛЯРОВ классов-токенов:
    private static List<Tokenable> getTokenableInstances() {
        List<Tokenable> tokenableInstances = new ArrayList<>();
        try {
            for (Class<Tokenable> cl : getTokenableClasses()) {
                Constructor<Tokenable> constructor = cl.getConstructor();
                tokenableInstances.add(constructor.newInstance());
            }
            return tokenableInstances;
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException("Невозможно создать экземпляры классов.");
        }
    }

    public static List<Tokenable> getTokenableInstancesForTest() {
        return getTokenableInstances();
    }

    private static Set<String> getValidTokens() {
        Set<String> additionalTokens = Set.of(PropertiesUtil.get("app.numberTokens.to.add").trim().split(" "));
        Set<String> validTokens = new LinkedHashSet<>();//линкедлист, чтобы порядок вставки был сохранен.
        for (Tokenable t : getTokenableInstances()) {
            validTokens.add(t.getToken());
        }
        validTokens.addAll(additionalTokens);
        return validTokens;
    }

    public static Set<String> getValidTokensForTest() {
        return getValidTokens();
    }

    public static int getPriority(String token) {
        List<Tokenable> tokenables = getTokenableInstances();
        for (Tokenable instance : tokenables) {
            if (token.equals(instance.getToken())) {
                return instance.getPriority();
            }
        }
        //если эквивалента входящей строки не найдено, то это число, и чтобы его идентифицировать, вернем заведомо максимально большое целое число.
        return Integer.MAX_VALUE;  //todo: корректно возвращать такое значение? Лучше возвращать null? Или что?
    }

    public static int getPriorityOfTokenForTest(String token) {
        return getPriority(token);
    }

    public static Optional<Operation> getInstance(String token) {
        List<Tokenable> tokenables = getTokenableInstances();
        for (Tokenable instance : tokenables) {
            if (token.equals(instance.getToken()) && instance instanceof Operation) {
                return Optional.of((Operation) instance);
            }
        }
        return Optional.empty();
    }


//    public static Deque<Double> getOperation(String token) {
//        List<Tokenable> tokenables = getTokenableInstances();
//        for (Tokenable instance : tokenables) {
//            if (instance instanceof Operation && token.equals(instance.getToken())) {
//                 ((Operation) instance).doOperation()
//
//            }
//
//        }
//        //если эквивалента входящей строки не найдено, то это число, и чтобы его идентифицировать, вернем заведомо максимально большое целое число.
//        return Integer.MAX_VALUE;  //todo: корректно возвращать такое значение? Лучше возвращать null? Или что?
//    }


//    public static String getSimpleClassNameOfInstanceOfToken(String token) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        List<Tokenable> tokenables = getTokenableInstances();
//        for (Tokenable instance : tokenables) {
//            if (token.equals(instance.getToken())) {
//                return instance.getClass().getSimpleName();
//            }
//        }
//        throw new RuntimeException(String.format("Такого - \"%s\" - токена не обнаружено.", token)); //todo: возможно лучше возвращать null или большое целое число.
//    }
}
