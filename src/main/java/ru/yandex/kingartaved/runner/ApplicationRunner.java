package ru.yandex.kingartaved.runner;

import ru.yandex.kingartaved.converter.Converterable;
import ru.yandex.kingartaved.converter.impl.RPNConverter;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.preparer.impl.ExpressionPreparer;
import ru.yandex.kingartaved.service.Calculable;
import ru.yandex.kingartaved.service.impl.RPNCalculator;
import ru.yandex.kingartaved.utils.Getter;
import ru.yandex.kingartaved.utils.Utils;
import ru.yandex.kingartaved.validator.Validatorable;
import ru.yandex.kingartaved.validator.impl.ExpressionValidator;

public class ApplicationRunner {
    public static void runApplication() {
        System.out.println("The following characters are allowed: ");//Допустимы следующие символы:
        System.out.println(Getter.VALID_TOKENS);
        System.out.println();
        while (true) {
            String expression = Utils.prompt("Enter an expression or \"Q\" to exit: ");//Введите выражение или "Q" для выхода:
            if (expression.equalsIgnoreCase("q")) {
                return;
            } else {
                Preparable prepared = new ExpressionPreparer(expression);
                Validatorable validated = new ExpressionValidator(prepared);
                Converterable converted = new RPNConverter(prepared, validated);
                Calculable result = new RPNCalculator(converted);

                System.out.println("Результат : " + result.calculate());
            }
        }
    }
}
