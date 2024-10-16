
### Введение.
Структура проекта с прошлого не сильно поменялась, птму что по архитектурным паттернам я еще не читал особо, но значительно поменялась внутренняя логика, убраны жесткие привязки к токенам, больше работа с классами токенов, приоритетами. В классах оставлены комментарии для описания назначения и основных функций класса (типа документация). 

### Основные классы.
* ExpressionPreparer - класс-подготовщик входного пользовательского выражения для дальнейшего использования.
* ExpressionValidator - класс для проверки валидности выражения.
* ReversePolishNotationConverter - класс для конвертации пользовательского выражения в ОПН.
* ReversePolishNotationCalculator - класс для считывания выражения в виде ОПН и расчета результата.

### Вспомогательные (утильные) классы.
В пакете utils собраны утильные классы со статическими методами.
* Getter - наиболее важный утильный класс, в котором реализована логика получения различных базовых сущностей, используемых в работе программы далее. 
* Utils - простенькие статические методы, используемые в разных частях программы.
* PropertiesUtil - класс для получения доступа к свойствам (пропертям) приложения.
* ApplicationRunner - класс с единственным статическим фабричным методом, инкапсулирущим логику взаимодействия между различными частями приложения.

### Классы-токены.
В пакете math.impl хранятся классы-токены, которые предназначены для хранения символа, обозначающего токен, приоритета и действия (в случае токенов-операторов).
Числовой токен отсутствует - валидные цифры-символы хранятся в проперти файле, подгружаются в ходе выполнения к коллекции валидных символов, полученных чтением символов из списка классов-токенов в пакете math.impl.

### Особенности реализации калькулятора. 
Можно добавлять новые операции или символы лишь добавлением классов-токенов. В классах-токенах  для примера добавлены разные виды пар скобок (фигурные, круглые, квадратные). Их (классы) можно удалить и оставить, например, только круглые - логика не поломается.
Также проверено добавление нового класса-токена - возведения в степень.