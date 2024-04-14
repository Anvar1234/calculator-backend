package ru.yandex.kingartaved.utils;

import ru.yandex.kingartaved.math.Tokenable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Getter {
    public static final Set<String> VALID_TOKENS;

    static {
        try {
            VALID_TOKENS = Getter.getValidTokens();
        } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    //метод для получения списка Tokenable КЛАССОВ-ТОКЕНОВ из директории:
    public static List<Class<Tokenable>> getTokenableClasses() throws IOException, ClassNotFoundException { //todo: сделать потом приватным.
        //Получаем список<String> ссылок на классы:
        Path pathToImpl = Path.of(PropertiesUtil.get("app.impl.path"));
        Stream<Path> streamClassPaths = Files.list(pathToImpl);
        List<String> stringClassDirs = streamClassPaths.map(Path::toString).toList();
        streamClassPaths.close(); //не забываем закрыть поток, эксепшены пробрасываем выше

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
//            System.out.println("classesList : " + classes);
        return classes;
    }

    //метод для получения списка ЭКЗЕМПЛЯРОВ классов-токенов:
    public static List<Tokenable> getTokenableInstances() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<Tokenable> tokenableInstances = new ArrayList<>();
        for (Class<Tokenable> cl : getTokenableClasses()) {
            Constructor<Tokenable> constructor = cl.getConstructor();
            tokenableInstances.add(constructor.newInstance());
        }
        return tokenableInstances;
    }

    private static Set<String> getValidTokens() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Set<String> additionalTokens = Set.of(PropertiesUtil.get("app.numberTokens.to.add").trim().split(" "));
        Set<String> validTokens = new HashSet<>();
        for (Tokenable t : getTokenableInstances()) {
            validTokens.add(t.getToken());
        }
        validTokens.addAll(additionalTokens);
        return validTokens;
    }

    public static int getPriorityOfToken(String token) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        List<Tokenable> tokenables = getTokenableInstances();
        for (Tokenable instance : tokenables) {
            if (token.equals(instance.getToken())) {
                return instance.getPriority();
            }
        }
        //если эквивалента входящей строки не найдено, то это число, и чтобы его идентифицировать, вернем заведомо максимально большое целое число.
        return Integer.MAX_VALUE;  //todo: корректно возвращать такое значение? Лучше возвращать null? Или что?
    }

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
