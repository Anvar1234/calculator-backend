package ru.yandex.kingartaved.preparator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Preparatorable {

    List<String> getPreparedExpression() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
