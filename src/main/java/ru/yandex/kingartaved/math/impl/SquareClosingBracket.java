package ru.yandex.kingartaved.math.impl;

import ru.yandex.kingartaved.math.Bracketable;
import ru.yandex.kingartaved.math.Tokenable;

public class SquareClosingBracket implements Bracketable, Tokenable {
    private final String TOKEN = "]";

    private final int PRIORITY = -1; // изменить на тот который в реале.

    @Override
    public String getToken() {
        return TOKEN;
    }
    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getBracket() {
        return getToken();
    }

}
