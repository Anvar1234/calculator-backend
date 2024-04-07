package ru.yandex.kingartaved.math.impl.brackets;

import ru.yandex.kingartaved.math.Bracketable;
import ru.yandex.kingartaved.math.Tokenable;

public class CurlyOpenBracket implements Bracketable, Tokenable {
    private final String TOKEN = "{";

    private final int PRIORITY = 2; // изменить на тот который в реале.

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
