package ru.yandex.kingartaved.math.impl;

import ru.yandex.kingartaved.math.Operation;
import ru.yandex.kingartaved.math.Tokenable;

import java.util.Deque;

public class ExponentiationToken implements Tokenable, Operation {
    private final String TOKEN = "^";

    private final int PRIORITY = 4; // изменить на тот который в реале.

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
        double pow = resultStack.pop();
        double result = Math.pow(resultStack.pop(), pow);
        resultStack.push(result);
        return resultStack;
    }
}
