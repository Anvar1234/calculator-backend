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

/**
 * Класс для получения базовых сущностей, используемых в дальнейшем, например: список валидных токенов,
 * экземпляра класса по токену, приоритета токена.
 */
public class Getter {
    public static final Set<String> VALID_TOKENS;

    static {
        VALID_TOKENS = getValidTokens();
    }

    /**
     * Публичный метод для получения экземпляра класса по токену-оператору.
     */
    public static Optional<Operation> getOperationInstance(String token) {
        return getOperationInstanceByToken(token);
    }

    /**
     * Публичный метод для получения приоритета по токену.
     */
    public static int getPriority(String token) {
        return getPriorityByToken(token);
    }

    /**
     * Приватный метод для получения списка Tokenable КЛАССОВ-ТОКЕНОВ из директории.
     */
    private static List<Class<Tokenable>> getTokenableClasses() {
        //Получаем список<String> ссылок на классы:
        Path pathToImpl = Path.of(PropertiesUtil.get("app.impl.path"));
        try (Stream<Path> streamClassPaths = Files.list(pathToImpl)) {
            List<String> stringClassDirs = streamClassPaths.map(Path::toString).toList();
            //Работаем со списком ссылок на классы в папке impl, приводим в удобоваримый вид для класса Class
            //(удаляем .java, заменяем / на точки) и загоняем все это дело в список:
            List<Class<Tokenable>> classes = new ArrayList<>();
            String regex = PropertiesUtil.get("app.string.to.replace");
            String suffix = PropertiesUtil.get("app.class.suffix.to.replace");
            for (String s : stringClassDirs) {
                String sCopy;
                sCopy = s.replace('\\', '.')
                        .replace(regex, "")
                        .replace(suffix, "")
                        .trim();
                Path p = Path.of(s);
                if (!Files.isDirectory(p) && Tokenable.class.isAssignableFrom(Class.forName(sCopy))) {//проверяем, не является ли значение в списке классов директорией И принадлежит ли к типу Tokenable.
                    classes.add((Class<Tokenable>) Class.forName(sCopy));//TODO: ВОПРОС - приведение можно использовать? И почему Идея выделяет строку?
                }
            }
            return classes;
        } catch (IOException | ClassNotFoundException e) {
            //TODO: норм ли так обрабатывать исключения?
            throw new RuntimeException("There was an error in specifying the path or class name."); //Ошибка в указании пути или имени классов.
        }
    }

    public static List<Class<Tokenable>> getTokenableClassesForTest() {
        return getTokenableClasses();
    }

    /**
     * Приватный метод для получения списка ЭКЗЕМПЛЯРОВ классов-токенов (Tokenable).
     */
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
            throw new RuntimeException("Class instances cannot be instantiated.");//Невозможно создать экземпляры классов.
        }
    }

    public static List<Tokenable> getTokenableInstancesForTest() {
        return getTokenableInstances();
    }

    /**
     * Приватный метод для получения множества валидных токенов.
     */
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

    /**
     * Приватный метод для получения приоритета токена.
     */
    private static int getPriorityByToken(String token) {
        List<Tokenable> tokenables = getTokenableInstances();
        for (Tokenable instance : tokenables) {
            if (token.equals(instance.getToken())) {
                return instance.getPriority();
            }
        }
        //если эквивалента входящей строки не найдено, то это число, и чтобы его идентифицировать, вернем заведомо максимально большое целое число.
        return Integer.MAX_VALUE;  //TODO: ВОПРОС - корректно возвращать такое значение? Лучше возвращать null? Optional? Или что?
    }

    public static int getPriorityByTokenForTest(String token) {
        return getPriority(token);
    }

    /**
     * Приватный метод для получения экземпляра класса по токену-оператору.
     */
    private static Optional<Operation> getOperationInstanceByToken(String token) {
        List<Tokenable> tokenables = getTokenableInstances();
        for (Tokenable instance : tokenables) {
            if (instance != null &&
                    token.equals(instance.getToken()) &&
                    instance instanceof Operation) { //TODO: ВОПРОС - так норм или instanceof это не надо?
                return Optional.of((Operation) instance); //TODO: ВОПРОС - приведение норм или не надо?
            }
        }
        return Optional.empty();
    }
}
