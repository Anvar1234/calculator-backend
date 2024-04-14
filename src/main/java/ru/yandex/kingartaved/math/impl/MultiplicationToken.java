package ru.yandex.kingartaved.math.impl;

import ru.yandex.kingartaved.math.Tokenable;

public class MultiplicationToken implements Tokenable {
    private final String TOKEN = "*";

    private final int PRIORITY = 3; // изменить на тот который в реале.

    @Override
    public String getToken() {
        return TOKEN;
    }
    @Override
    public int getPriority() {
        return PRIORITY;
    }

}
