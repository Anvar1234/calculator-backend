package ru.yandex.kingartaved.math.impl;

import ru.yandex.kingartaved.math.Operation;
import ru.yandex.kingartaved.math.Tokenable;

import java.util.Deque;

public class DivisionToken implements Tokenable, Operation {
    private final String TOKEN = "/";

    private final int PRIORITY = 3; // изменить на тот который в реале.

    @Override
    public String getToken() {
        return TOKEN;
    }
    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public Deque<Double> doOperation(Deque<Double> resultStack) {
        double divider = resultStack.pop();
        double result = resultStack.pop() / divider;
        resultStack.push(result);
        return resultStack;
    }
}
