package ru.yandex.kingartaved.math.impl.brackets;

import ru.yandex.kingartaved.math.OpenBracketable;
import ru.yandex.kingartaved.math.Tokenable;

public class RoundOpenBracket implements OpenBracketable, Tokenable {
    private final String TOKEN = "(";

    private final int PRIORITY = 2;

    @Override
    public String getToken() {
        return TOKEN;
    }
    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public String getOpenBracket() {
        return getToken();
    }

}
