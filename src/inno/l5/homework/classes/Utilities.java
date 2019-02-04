package inno.l5.homework.classes;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utilities {


    /**
     * Служебные метод buildArrayOfSourcesPaths() предназначен
     * для формирования массива адресов файлов, содержащихся в папке,
     * чей путь был передан в виде строки в параметре.
     *
     * @param dirPath путь до папки с файлами.
     * @return
     */
    public String[] buildArrayOfSourcesPaths(String dirPath) {
        File dir = new File(dirPath);
        if (dir.exists() == false || dir.list().length == 0)
            return new String[] {""};
        String[] files = dir.list();
        String[] concedPaths = new String[files.length];
        for (int i=0; i<files.length; i++) {
            if (Files.exists(Paths.get(dirPath + files[i])) == false) {
                concedPaths[i] = "";
                continue;
            }
            concedPaths[i] = dirPath + files[i];
        }
        return concedPaths;
    }

    /**
     * Считывает файл и сохраняет его содержимое сначала в строку,
     * а потом от полученной строки отбирает только слова и возвращает
     * массив полученных слов.
     *
     * @param path путь ло файла
     * @return массив слов из файла
     */
    public String[] getWordsFromFile(String path) {
        StringBuilder text = new StringBuilder();
        ReadersCreator rc = new ReadersCreator();
        text.append(rc.createReaderOnLink(path).getTextFromResource());
        return text.toString().replaceAll("[\\W_]", " ").split(" ");
    }

}
