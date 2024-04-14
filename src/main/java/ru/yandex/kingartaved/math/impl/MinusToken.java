package ru.yandex.kingartaved.math.impl;

import ru.yandex.kingartaved.math.Tokenable;

public class MinusToken implements Tokenable {
    private final String TOKEN = "-";

    private final int PRIORITY = 2; // изменить на тот который в реале.

    @Override
    public String getToken() {
        return TOKEN;
    }
    @Override
    public int getPriority() {
        return PRIORITY;
    }

}
