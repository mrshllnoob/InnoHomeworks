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
        if (dirPath == null || Files.exists(Paths.get(dirPath)) == false
                || Files.isDirectory(Paths.get(dirPath))==false)
            return new String[] {""};
        String[] files = new File(dirPath).list();
        if (files == null)
            return new String[] {""};
        String[] concedPaths = files == null ? new String[] {""} : new String[files.length];
        for (int i=0; i<concedPaths.length; i++) {
            if (!Files.exists(Paths.get(dirPath + files[i]))) {
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
        if (path == null || !Files.exists(Paths.get(path)) || !Files.isDirectory(Paths.get(path)))
            return new String[] {""};
        StringBuilder text = new StringBuilder();
        ReadersCreator rc = new ReadersCreator();
        text.append(rc.createReaderOnLink(path).getTextFromResource());
        return text.toString().replaceAll("[\\W_]", " ").split(" ");
    }

}
