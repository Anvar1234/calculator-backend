package ru.yandex.kingartaved.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DirAnalizator {

    public static List<Class<?>> getClasses() throws IOException, ClassNotFoundException {
        //Получаем список<String> ссылок на классы:
        Path pathToImpl = Path.of(PropertiesUtil.get("app.impl.path"));
        Stream<Path> streamClassPaths = Files.list(pathToImpl);
        List<String> stringClassDirs = streamClassPaths.map(Path::toString).toList();
        streamClassPaths.close(); //не забываем закрыть поток, эксепшены пробрасываем выше

        //Работаем со списком ссылок на классы в папке impl, приводим в удобоваримый вид для класса Class
        //(удаляем .java, заменяем / на точки) и загоняем все это дело в список:
        List<Class<?>> classes = new ArrayList<>();
        String regex = PropertiesUtil.get("app.string.to.replace");
//        System.out.println("regex : " + regex);
        String suffix = PropertiesUtil.get("app.class.suffix.to.replace");
//        System.out.println("suffix : " + suffix);
        for (
                String s : stringClassDirs) {
            String sCopy;
            sCopy = s.replace('\\', '.')
                    .replace(regex, "")
                    .replace(suffix, "")
                    .trim();
            System.out.println("s : " + sCopy);
            Path p = Path.of(s);
            System.out.println("p : " + p);
            if (!Files.isDirectory(p)){
                classes.add(Class.forName(sCopy));//ошибка в том, что в список попадает и имя пакета brackets, нужно сделать проверку, что это именно файл.
            }
        }
        System.out.println("classesList : " + classes);
        return classes;
    }

    public static List<Class<?>> getBracketClasses() throws IOException, ClassNotFoundException {
        //Получаем список<String> ссылок на классы:
        Path pathToImpl = Path.of(PropertiesUtil.get("app.impl.brackets.path"));
        Stream<Path> streamClassPaths = Files.list(pathToImpl);
        List<String> stringClassDirs = streamClassPaths.map(Path::toString).toList();
        streamClassPaths.close(); //не забываем закрыть поток, эксепшены пробрасываем выше

        //Работаем со списком ссылок на классы в папке impl, приводим в удобоваримый вид для класса Class
        //(удаляем .java, заменяем / на точки) и загоняем все это дело в список:
        List<Class<?>> classes = new ArrayList<>();
        String regex = PropertiesUtil.get("app.string.to.replace");
//        System.out.println("regex : " + regex);
        String suffix = PropertiesUtil.get("app.class.suffix.to.replace");
//        System.out.println("suffix : " + suffix);
        for (
                String s : stringClassDirs) {
            String sCopy;
            sCopy = s.replace('\\', '.')
                    .replace(regex, "")
                    .replace(suffix, "")
                    .trim();
//            System.out.println("s : " + sCopy);
            classes.add(Class.forName(sCopy));
        }
        System.out.println("classesList : " + classes);
        return classes;
    }


}
