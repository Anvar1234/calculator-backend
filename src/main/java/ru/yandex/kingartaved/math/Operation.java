package ru.yandex.kingartaved.math;

import java.util.Deque;

public interface Operation {
    Deque<Double> doOperation(Deque<Double> resultStack);
}
