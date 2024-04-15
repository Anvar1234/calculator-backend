package ru.yandex.kingartaved.converter;

import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.converter.impl.RPNConverter;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.preparer.impl.ExpressionPreparer;
import ru.yandex.kingartaved.validator.impl.ExpressionValidator;

import java.util.List;

public class RPNConverterTest {
    @Test
    public void getConvertedExpressionTest1(){
        String expression = "1-(1+2)-3+4-5*7";
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConvertedExpression();
        System.out.println("convertedExpression : " + convertedExpression);
    }
    @Test
    public void getConvertedExpressionTest2(){
        String expression = "1*(2-(3-4))";
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConvertedExpression();
        System.out.println("convertedExpression : " + convertedExpression);
    }
    @Test
    public void getConvertedExpressionTest3(){
        String expression = "-(-1-(1+2))"; //todo: где-то косяк что-ли, отличается от выражения в старом калькуляторе, но результат вроде правильный.
        Preparable preparator = new ExpressionPreparer(expression);
        System.out.println(preparator);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        Converterable converterable = new RPNConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConvertedExpression();
        System.out.println("convertedExpression : " + convertedExpression);
    }
}
