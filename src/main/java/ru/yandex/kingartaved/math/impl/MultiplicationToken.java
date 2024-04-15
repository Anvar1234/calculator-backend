package ru.yandex.kingartaved.math.impl;

import ru.yandex.kingartaved.math.Operation;
import ru.yandex.kingartaved.math.Tokenable;

import java.util.Deque;

public class MultiplicationToken implements Tokenable, Operation {
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

    @Override
    public Deque<Double> doOperation(Deque<Double> resultStack) {
        double result = resultStack.pop() * resultStack.pop();
        resultStack.push(result);
        return resultStack;
    }
}
