package inno.l5.homework;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс (@code ResourceReader) содержит ряд статических методов
 * для работы с текстовыми ресурсами и текстовыми файлами.
 *
 * Методы данного класса позволяют получать текстовую информацию
 * с текстовых файлов на компьютере, по веб-ссылке и с открытых
 * ftp-серверов. Анализировать полученную информациюю посредством
 * многопоточной обработки.
 *
 * @author Tsapin Anton
 */
public class ResourceReader {
    /**
     * Метод ищет элементы массива переданных слов words в
     * массиве текстовых ресурсов sources и результаты успешного
     * поиска записывает в файл res.
     *
     * Метод запускает некоторое число
     * потоков, ограниченное объектом класса ExecutorService. В каждом
     * потоке идет сбор текстовых данных с указанных ресурсов посредством метода
     * parseResource() и затем происходит активация блокировки потока и вызов метода
     * storeMatches(), результат которого добавляется в коллекцию. После этого
     * блокировка снимается. По окончанию обработки всех ресурсов результаты поиска
     * записываются в файл по адресу res.
     *
     * @param sources массив адресов текстовых файлов
     * @param words массив слов для поиска
     * @param res адрес файла, в который записываются результаты сравнения
     * @throws IOException
     */
    public static void getOccurencies(String[] sources, String[] words, String res) {
        Lock lock = new ReentrantLock();
        ArrayList<StringBuilder> collected = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i=0; i<sources.length; i++) {
            int finalI = i;
            Runnable worker = new Runnable() {
                @Override
                public void run() {
                    try {
                        String resource = parseResource(sources[finalI]);
                        lock.lock();
                        collected.add(storeMatches(resource, words));
                        lock.unlock();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(worker);
        }
        executorService.shutdown();
        try {
            writeCollectionIntoFile(res,collected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getOccurenciesUsingCallable(String[] sources, String[] words, String res) {
        List<Future<StringBuilder>> results = new ArrayList<>();
        int i = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (i=0;i<sources.length;i++) {
            int finalI = i;
            Future<StringBuilder> future = executorService.submit(new Callable<StringBuilder>() {
                @Override
                public StringBuilder call() throws Exception {
                    return storeMatches(parseResource(sources[finalI]), words);
                }
            });
            results.add(future);
        }
        executorService.shutdown();
        try {
            writeCollectionIntoFile(res,results);
        } catch (IOException|InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * Записывает данные из коллекции в файл.
     *
     * Элементы коллекции при записи в файл разделяются
     * символом новой строки.
     *
     * @param res адрес формируемого файла
     * @param collection коллекция записываемых данных
     * @throws IOException
     */
    private static void writeCollectionIntoFile(String res, ArrayList<StringBuilder> collection) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(res);
                OutputStreamWriter bout = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(bout)) {
            for (StringBuilder match : collection)
                pw.println(match.toString() + "\n");
        }
    }

    private static void writeCollectionIntoFile(String res, List<Future<StringBuilder>> collection) throws IOException, ExecutionException, InterruptedException {
        try(FileOutputStream fos = new FileOutputStream(res);
            OutputStreamWriter bout = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(bout)) {
            for (Future<StringBuilder> match : collection)
                pw.println(match.get().toString() + "\n");
        }
    }

    /**
     * Осуществляет сбор текстовой информации с различных
     * текстовых ресурсов.
     *
     * При сборе данных символы новой строки удаляются,
     * таким образом данные представлены в однострочном формате
     * и их становится легче обрабатывать методом storeMatches.
     *
     * @param resource адрес файла в файловой системе, либо ссылка на текстовый документ
     *                 в сети.
     * @return собранные данные в строчном представлении
     * @throws IOException
     */
    public static String parseResource(String resource) throws IOException {

        StringBuilder result = new StringBuilder();

        if ((resource.contains("http://") && (resource.startsWith("http://")))
                || (resource.contains("https://") && resource.startsWith("https://"))) {
            try (Scanner scanner = new Scanner(new URL(resource).openStream(),
                    StandardCharsets.UTF_8.toString())) {
                scanner.useDelimiter("\\A");
                while (scanner.hasNext())
                    result.append(scanner.nextLine());
            }
            return result.toString();
        }

        if (resource.contains("ftp://") && resource.startsWith("ftp://")) {
            URLConnection conn = new URL(resource).openConnection();
            try(InputStream in = conn.getInputStream();
                InputStreamReader is = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(is)) {
                String line;
                while((line = reader.readLine()) != null)
                    result.append(line);
            }
            return result.toString();
        }

        else {
            try (FileReader fin = new FileReader(new File(resource));
                 BufferedReader in = new BufferedReader(fin)) {
                String line;
                while ((line = in.readLine()) != null)
                    result.append(line);
            }
            return result.toString();
        }
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
    private static StringBuilder storeMatches(String text, String[] words) {
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


    /**
     * Служебные метод buildArrayOfSourcesPaths() предназначен
     * для формирования массива адресов файлов, содержащихся в папке,
     * чей путь был передан в виде строки в параметре.
     *
     * @param dirPath путь до папки с файлами.
     * @return
     */
    public static String[] buildArrayOfSourcesPaths(String dirPath) {
        File dir = new File(dirPath);
        String[] files = dir.list();
        String[] concedPaths = new String[files.length];
        for (int i=0; i<files.length; i++)
            concedPaths[i] = dirPath + files[i];
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
    public static String[] getWordsFromFile(String path) {
        String text = null;
        try (FileReader fin = new FileReader(new File(path));
                BufferedReader in = new BufferedReader(fin)) {
            String line;
            while((line = in.readLine())!=null) {
                text += line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.replaceAll("[\\W_]", " ").split(" ");
    }
}
