package inno.l5.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Класс (@code MultithreadingMatchesCollector) содержит ряд методов
 * для мультипоточной обработки и анализа текстовых данных из различных
 * источников. Сбор текстовой информации с предоставленных ресурсов
 * осуществляется при помощи фабричного классов реализующих интерфейс
 * ResourceReader.
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
    public List<String> getOccurenciesUsingCallable(String[] sources, String[] words) throws ExecutionException, InterruptedException {
        List<StringBuilder> results = new ArrayList<>();
        int i = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ReadersCreator fab = new ReadersCreator();
        for (i=0;i<sources.length;i++) {
            int finalI = i;
            Future<StringBuilder> future = executorService.submit(
                    () -> storeMatches(fab.createReaderOnLink(sources[finalI])
                                            .getTextFromResource(), words));
            results.add(future.get());
        }
        executorService.shutdown();
        return collectionToString(results);
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
                            result.append(elem + System.lineSeparator());
                        }
                    }
        }
        return result;
    }

    /**
     * Размещает информацию переданную в параметре-списке
     * в возвращаемой коллекции.
     *
     * @param sbList лист фьючеров с текстовыми данными
     * @return возвращает коллекцию элементов извлеченных из фьючеров.
     */
    public List<String> collectionToString(List<StringBuilder> sbList) {
        List result = new ArrayList();
        for (StringBuilder el : sbList) {
            result.add(el.toString());
        }
        return result;
    }

}
