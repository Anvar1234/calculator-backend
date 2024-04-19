package ru.yandex.kingartaved.service.impl;

import ru.yandex.kingartaved.converter.Converterable;
import ru.yandex.kingartaved.service.Calculable;
import ru.yandex.kingartaved.utils.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static ru.yandex.kingartaved.utils.Utils.isNumeric;

public class RPNCalculator implements Calculable {
    private final List<String> convertedExpression;

    public RPNCalculator(Converterable converter) {
        this.convertedExpression = converter.getConverted();
    }

    @Override
    public Deque<Double> calculate() {
        Deque<Double> resultStack = new ArrayDeque<>();

        for (String element : convertedExpression) {
            //Условие "Если элемент массива число, то перевод в дабл и пушим в стек"
            if (isNumeric(element)) {
                resultStack.push(Double.parseDouble(element));
                //Иначе, если получение экземпляра не возвращает null, то на этом экземпляре вызываем метод doOperation():
            } else if (Getter.getOperationInstance(element).isPresent()) {
                Getter.getOperationInstance(element).get().doOperation(resultStack);
            }
        }
        return resultStack;
    }

    public Deque<Double> calculateForTest() {
        return calculate();
    }
}

