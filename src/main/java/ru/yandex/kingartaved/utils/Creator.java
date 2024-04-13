package ru.yandex.kingartaved.utils;

import ru.yandex.kingartaved.math.Tokenable;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Creator {

//    private static final List<Class<Tokenable>> classes;
//
//    static {
//        try {
//            classes = Getter.getTokenableClasses();
//        } catch (IOException | ClassNotFoundException e) {
//            throw new RuntimeException(e);//todo: нормально обработать потом
//        }
//    }

    //    public static Map<String, String> getBracketMap() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        return createBracketMap();
//    }
//
//    /**
//     * todo: Разделить метод на приватные подметоды, иначе сложно читать. То есть необходимо осуществить декомпозицию.
//     * Также необходимо избавиться от знака "?", мы же знаем, что в коллекции будут только Bracketable объекты.
//     */
//    private static Map<String, String> createBracketMap() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {  //сделать потом приватным.
//        Map<String, String> bracketsMap = new HashMap<>();
//        String suffixOpen = PropertiesUtil.get("app.openbracket.suffix");
//        String suffixClosing = PropertiesUtil.get("app.closingbracket.suffix");
//
//        int count = 0;
//        //Для информации: у нас всегда минимум 2 класса в пакете brackets (должно быть).
//        for (int i = 0; i < bracketClasses.size(); i++) {
//            Class<?> classToValue = bracketClasses.get(i);
//            if (classToValue.getSimpleName().contains("Open")) {//если класс содержит Опен, то это по-любому значение, а не ключ, тогда:
//                System.out.println("classToValue : " + classToValue);
//                String nameWithoutSuffixOpen = classToValue.getSimpleName().replaceAll(suffixOpen, ""); //получили например "Round".
//                System.out.println("nameWithoutSuffixOpen : " + nameWithoutSuffixOpen);
//                while (count < bracketClasses.size()) {//прогоняем цикл по всем классам в пакете brackets, чтобы найти пару closing нашему классу get(i).
//                    Class<?> classToKey = bracketClasses.get(count++);
//                    if (classToKey.getSimpleName().contains("Closing")) {//если класс содержит Closing, то это по-любому ключ, а не значение, тогда:
//                        System.out.println("classToKey : " + classToKey);
//                        String nameWithoutSuffixClosing = classToKey.getSimpleName().replaceAll(suffixClosing, ""); // тоже получили Round.
//                        System.out.println("nameWithoutSuffixClosing : " + nameWithoutSuffixClosing);
//                        if (nameWithoutSuffixOpen.equals(nameWithoutSuffixClosing)) { //например Round(OpenBracket) = Round(ClosingBracket) && что-то еще нужно сравнивать!
//                            Map<String, String> singleMap = createSingleMap(classToKey, classToValue);
//                            for (Map.Entry<String, String> m : singleMap.entrySet()) {
//                                bracketsMap.putIfAbsent(m.getKey(), m.getValue());//putIfAbsent Добавляет пары в мапу только если такого ключа в ней еще нет.
//                            }
//                        }
//                    }
//                }
//            }
//            count = 0;//обнуляем count.
//        }
//        return bracketsMap;
//    }
//
//    private static Map<String, String> createSingleMap(Class<?> keyClass, Class<?> valueClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        Constructor<?> valueConstructor = valueClass.getConstructor();
//        Bracketable valueObj = (Bracketable) valueConstructor.newInstance();
//        String value = valueObj.getBracket();
//        Constructor<?> keyConstructor = keyClass.getConstructor();
//        Bracketable keyObj = (Bracketable) keyConstructor.newInstance();
//        String key = keyObj.getBracket();
//        return Map.of(key, value);
//    }


    /**
     * Внутренний статический вспомогательный класс для создания списка классов-токенов.
     */

}
//        private static List<Class<?>> getBracketClasses() throws IOException, ClassNotFoundException {
//            //Получаем список<String> ссылок на классы:
//            Path pathToImpl = Path.of(PropertiesUtil.get("app.impl.brackets.path"));
//            Stream<Path> streamClassPaths = Files.list(pathToImpl);
//            List<String> stringClassDirs = streamClassPaths.map(Path::toString).toList();
//            streamClassPaths.close(); //не забываем закрыть поток, эксепшены пробрасываем выше
//
//            //Работаем со списком ссылок на классы в папке impl, приводим в удобоваримый вид для класса Class
//            //(удаляем .java, заменяем / на точки) и загоняем все это дело в список:
//            List<Class<?>> classes = new ArrayList<>();
//            String regex = PropertiesUtil.get("app.string.to.replace");
////        System.out.println("regex : " + regex);
//            String suffix = PropertiesUtil.get("app.class.suffix.to.replace");
////        System.out.println("suffix : " + suffix);
//            for (
//                    String s : stringClassDirs) {
//                String sCopy;
//                sCopy = s.replace('\\', '.')
//                        .replace(regex, "")
//                        .replace(suffix, "")
//                        .trim();
////            System.out.println("s : " + sCopy);
//                Path p = Path.of(s);
////            System.out.println("p : " + p);
//                if (!Files.isDirectory(p)) {
//                    classes.add(Class.forName(sCopy));//ошибка в том, что в список попадает и имя пакета brackets, нужно сделать проверку, что это именно файл.
//                }
//            }
//            System.out.println("classesList : " + classes);
//            return classes;
//        }


