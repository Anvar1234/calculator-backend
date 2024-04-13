package ru.yandex.kingartaved.math.impl;

import ru.yandex.kingartaved.math.Tokenable;

public class DotDelimiterToken implements Tokenable {
    private final String TOKEN = ".";

    private final int PRIORITY = 0;

    @Override
    public String getToken() {
        return TOKEN;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }
}
