package ru.yandex.kingartaved.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.kingartaved.converter.impl.ReversePolishNotationConverter;
import ru.yandex.kingartaved.preparer.Preparable;
import ru.yandex.kingartaved.preparer.impl.ExpressionPreparer;
import ru.yandex.kingartaved.validator.impl.ExpressionValidator;

import java.util.List;

public class ReversePolishNotationConverterTest {
    @Test
    public void getConvertedExpressionTest1(){
        String expression = "1-(1+2)-3+4-5*7";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        ReversePolishNotationConverter converterable = new ReversePolishNotationConverter(preparator, validator);
        List<String> convertedExpression = converterable.getConvertedExpressionForTest();
        Assertions.assertEquals("+", convertedExpression.get(3));
        Assertions.assertEquals(convertedExpression.size()-1, convertedExpression.lastIndexOf("-"));
    }

    @Test
    public void getConvertedExpressionTest2(){
        String expression = "-(-1-(1+2)/2)";
        Preparable preparator = new ExpressionPreparer(expression);
        ExpressionValidator validator = new ExpressionValidator(preparator);
        ReversePolishNotationConverter converterable = new ReversePolishNotationConverter(preparator, validator);
        List<String> actualConverted = converterable.getConvertedExpressionForTest();
        List<String> expected = List.of("0","0","1","-", "1","2","+","2","/","-","-");
        Assertions.assertEquals(expected, actualConverted);
    }
}
