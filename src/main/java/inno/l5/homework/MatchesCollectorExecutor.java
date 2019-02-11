package inno.l5.homework;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Класс для запуска класса MultithreadingMatchesCollector.
 *
 * Внутри метода main данного класса используюстся утилитные методы
 * класса Utilities для построения массива слов из словаря и массива
 * адресов текстовых ресурсов. Также используется класс ResultWriter
 * для записи результатов работы в текстовый файл.
 *
 * @author Tsapin Anton
 */
public class MatchesCollectorExecutor {

    /**
     * Принимает 3 аргумента: путь до папки с ресурсами,
     * путь до файла-словаря и путь до формируемого файла
     * с совпадениями. Н-р (src/main/resources/l5/smallsrc
     *                      src/main/resources/l5/dictionary.txt
     *                      target/result.txt)
     *
     * @param args
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        if (args.length!=3) {
            System.out.println("Wrong number of args");
            return;
        }

        MultithreadingMatchesCollector mmc = new MultithreadingMatchesCollector();
        Utilities util = new Utilities();
        List<String> res = mmc.getOccurenciesUsingCallable(util.buildArrayOfSourcesPaths(args[0]),
                                        util.getWordsFromFile(args[1]));
        new ResultWriter(args[2]).writeListIntoFile(res);
    }

}
