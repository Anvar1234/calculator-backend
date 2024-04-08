package ru.yandex.kingartaved.preparator;

import java.util.List;

public interface Preparatorable {

    /**
     * Метод проверки в пользовательском выражении наличия унарного минуса и его замены.
     * В любом классе, реализующем этот интерфейс, должен быть такой обработчик.
     */
    List<String> unaryMinusHandler();

}
