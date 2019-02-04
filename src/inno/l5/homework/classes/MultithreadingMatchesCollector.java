package inno.l5.homework.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Класс (@code MultithreadingMatchesCollector) содержит ряд статических методов
 * для работы с текстовыми ресурсами и текстовыми файлами.
 *
 * Методы данного класса позволяют получать текстовую информацию
 * с текстовых файлов на компьютере, по веб-ссылке и с открытых
 * ftp-серверов. Анализировать полученную информациюю посредством
 * многопоточной обработки.
 *
 * @author Tsapin Anton
 */
public class MultithreadingMatchesCollector {

    /**
     *  Метод ищет элементы массива переданных слов words в
     *  массиве текстовых ресурсов sources и результаты успешного
     *  поиска записывает в файл res.
     *
     *  Использует интерфейсы Callable и Future.
     *  @param sources массив ссылок
     * @param words массив слов
     */
    public List<StringBuilder> getOccurenciesUsingCallable(String[] sources, String[] words) {
        List<Future<StringBuilder>> results = new ArrayList<>();
        int i = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ReadersCreator fab = new ReadersCreator();
        for (i=0;i<sources.length;i++) {
            int finalI = i;
            Future<StringBuilder> future = executorService.submit(
                    () -> storeMatches(fab.createReaderOnLink(sources[finalI])
                                            .getTextFromResource(), words));
            results.add(future);
        }
        executorService.shutdown();
        return collectFuturesIntoList(results);
    }

    /**
     * В неколько потоков ищет слова из массива words в
     * строке text. При успешном поиске осуществляет блокирующую
     * запись предложения, содержащего элемент words в коллекцию
     * resultList. Возвращает resultList коллекцию по окончанию работы метода.
     *
     * @param text текстовая строка
     * @param words массив искомых слов
     * @return коллекция содержащая предложения текстовой строки text,
     *          в которых были найдены слова из массива words
     */
    public StringBuilder storeMatches(String text, String[] words) {
        StringBuilder result = new StringBuilder();
        for (int j=0; j<words.length; j++){
                    String[] arr = text.split("[!?.(...)]");
                    for (String elem : arr) {
                        if (elem.contains(" " + words[j] + " ")) {
                            result.append(elem + "|=>" + words[j] + System.lineSeparator());
                        }
                    }
        }
        return result;
    }

    public List<StringBuilder> collectFuturesIntoList(List<Future<StringBuilder>> futureList) {
        List result = new ArrayList();
        for (Future el : futureList) {
            try {
                result.add(el.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
