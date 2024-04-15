package ru.yandex.kingartaved.math;

import java.util.Deque;

public interface Operation {
    Deque<Double> doOperation(Deque<Double> resultStack);//todo: скорее всего нужно поменять сигнатуру - сделать, чтобы принимал не операнды, а коллекцию ОПН, чтобы можно было попнуть.
}
