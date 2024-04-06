package ru.yandex.kingartaved.utils;

import ru.yandex.kingartaved.utils.PropertiesUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DirAnalizator {

    public List<Class<?>> getClasses() throws IOException, ClassNotFoundException {
        //Получаем список<String> ссылок на классы:
        Path pathToImpl = Path.of(PropertiesUtil.get("app.impl.relative.path"));
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
