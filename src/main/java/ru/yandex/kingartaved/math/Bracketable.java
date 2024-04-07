package ru.yandex.kingartaved.math;

public interface Bracketable {
    String getBracket(); //без метода мы не сможем в Creator вытащить символ из классов скобок, так как иначе нам
    // же нужно указать тип скобок точно там где приводим в методе createSimpleMap(), типа это RoundOpen, RoundClosing и тд.
}
