package inno.l5.homework;

import java.io.IOException;

/**
 * Класс предназначен для тестирования класса (@code ResourceReader).
 *
 * В main-методе вызывается статический метод (@code getOccurencies())
 * класса (@code ResourceReader). Его аргументами являются:
 * 1. Результат вызова статического метода класса (@code ResourceReader),
 * который возвращает массив адресов файлов в указанной папке в виде String[]
 * 2. Результат вызова статического метода класса (@code ResourceReader),
 * которыый возвращает массив слов из указанного файла в виде String[].
 * 3. Путь до файла куда должен быть сохранен результат вызова метода
 * (@code getOccurencies()).
 *
 * @author Tsapin Anton
 */
public class MultithreadingExecutor {

    public static void main(String[] args) {
        try {
            ResourceReader.getOccurencies(
                    ResourceReader.buildArrayOfSourcesPaths("src/inno/l5/homework/smallsrc/"),
                    ResourceReader.getWordsFromFile("src/inno/l5/homework/dictionary.txt"),
                    "src/inno/l5/homework/res.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
