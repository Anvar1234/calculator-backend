package ru.yandex.kingartaved.converter.impl;

import ru.yandex.kingartaved.converter.Converterable;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.utils.Getter;
import ru.yandex.kingartaved.validator.Validatorable;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static ru.yandex.kingartaved.utils.Utils.isNumeric;

public class RPNConverter implements Converterable {
    private final List<String> preparedExpression;

    /**
     * Класс, используемый для перевода пользовательского выражения в обратную польскую нотацию (ОПН).
     */

    public RPNConverter(Preparable preparable, Validatorable validator) {
        if (validator.isValidExpression()) {
            this.preparedExpression = preparable.getPreparedExpression();
        } else {
            throw new RuntimeException("Выражение не валидно!");
        }
    }
    /**
     * Публичный метод для получения пользовательского выражения в виде ОПН.
     */
    @Override
    public List<String> getConverted() {
        return getConvertedExpression();
    }

    /**
     * Приватный метод для получения пользовательского выражения в виде ОПН.
     */
    private List<String> getConvertedExpression() {

        Deque<String> operators = new ArrayDeque<>(); //стек операторов
        List<String> convertedExpression = new ArrayList<>(); //коллекция вывода

        for (String item : preparedExpression) {
            //Если элемент массива число, то в выводной список:
            if (isNumeric(item)) {
                convertedExpression.add(item);
            }
            //Если элемент массива открывающая скобка:
            else if (Getter.getPriority(item) == 1) {
                //добавляем элемент в стек операторов:
                operators.push(item);
            } else if (Getter.getPriority(item) == -1) {//Если элемент массива закр скобка ")", то:
                //todo Условие ниже (первый if) скорее всего тоже можно убрать.
                if (!operators.isEmpty()) {//если стек операторов не пуст, то:
                    while (Getter.getPriority(operators.peek()) != 1) {//пока не встретим на вершине стека откр скобку //todo: посмотреть на тесты, здесь if заменил на while.
                        //добавляем в выводной список элементы из стека операторов и удаляем их (poll):
                        convertedExpression.add(operators.poll());
                    }
                    //иначе удаляем открывающую скобку (последний эл-нт): до этого было operators.remove("(");
                    operators.remove(operators.peek()); //.poll(); //todo: вот здесь может быть тонкий момент.
                }
            }

            //Блок else когда входит оператор:
            else {
                int incomingPriority = Getter.getPriority(item);

                //Если Стек операторов пуст или верхний элемент Стека это открывающая скобка:
                if (operators.isEmpty() || Getter.getPriority(operators.peek()) == 1) {
                    operators.push(item);

                    //Если входящий priority > приоритета последнего элемента Стэка:
                } else if (incomingPriority > Getter.getPriority(operators.peek())) {
                    operators.push(item);
                }

                //Условие "Если входящий приоритет <= приоритета верхнего элемента Стэка":
                else if (incomingPriority <= Getter.getPriority(operators.peek())) {

                    //то пока стек не пуст И элемент не явл открывающей скобкой И входящий приоритет <= приоритета элемента на вершине стека:
                    while (!operators.isEmpty() && Getter.getPriority(operators.peek()) != 1 && incomingPriority <= Getter.getPriority(operators.peek())) {
                        convertedExpression.add(operators.poll());
                    }
                    operators.push(item);
                }
            }
        }
        while (!operators.isEmpty()) {
            convertedExpression.add(operators.poll());
        }
        return convertedExpression;
    }

    public List<String> getConvertedExpressionForTest() {
        return this.getConvertedExpression();
    }
}
