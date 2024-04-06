package ru.yandex.kingartaved.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static final Properties APP_PROPERTIES = new Properties();

    //статический блок инициализации, чтобы при запуске приложения срабатывал метод loadTokenProperties и
    //проперти прогружались до выполнения основного кода
    static {
        loadTokenProperties();
    }
    private static void loadTokenProperties() {
        try (FileReader fileReader = new FileReader("src/main/resources/app.properties")) {
            APP_PROPERTIES.load(fileReader);
        } catch (IOException excptn) {
            System.out.println(excptn.getMessage());
        }
    }

    public static String get(String key){
        return APP_PROPERTIES.getProperty(key);
    }
}
