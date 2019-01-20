package inno.l4.homework;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Класс содержит методы для генерации текста случайного содержания.
 * Поле (@code dictionary) предназначено для хранения коллекции элементов
 * словаря размером равном (@code dictSize) полю класса. Для поля (@code dictSize)
 * отсутствуют геттер и сеттер, так как изменять его или получать извне класса
 * не имеет смысла. Поле (@code resultsPath) содержит путь до папки назначения,
 * куда генерируются текстовые файлы методом (@code generateDankTextFiles).
 * Поле (@code resultFileSize) содержит значение, указывающее на кол-во абзацев
 * в генерируемом тексте. Поле (@code probabilityDivider) является элементом
 * вычисления вероятности появления каждого слова из словаря в генерируемом тексте.
 */
public class TextGenerator {

    private Collection<String> dictionary = new ArrayList<>();
    private int dictSize = 1000;
    private String resultsPath = "/home/sa/IdeaProjects/InnoHomeworks/src/inno/l4/homework/gens/";
    private int resultFileSize = 5;
    private int probabilityDivider = 1;

    /**
     * Конструктор класса принимает на вход коллекцию элементов и выбирает из нее
     * случайные элементы в кол-ве равном полю (@code doctSize). Выбранные элементы
     * помещаются в поле (@code dictionary).
     * @param coll коллекция элементов
     */
    public TextGenerator(Collection<String> coll, int value) {
        setDictSize(value);
        setDictionary(coll);
    }

    /**
     * Помещает в поле (@code dictionary) коллекцию элементов
     * фиксированного значением поля класса (@code dictSize) размера.
     * @param coll коллекция элементов
     */
    public void setDictionary(Collection<String> coll) {
        Random rand = new Random();
        for(int i = 0; i< getDictSize(); i++) {
            int randomIndex = rand.nextInt(coll.size()-1);
            int counter = 0;
            Iterator iter = coll.iterator();
            while (iter.hasNext()) {
                if (counter==randomIndex) {
                    System.out.println();
                    this.getDictionary().add(String.valueOf(iter.next()));
                    break;
                }
                iter.next();
                counter++;
            }
        }
    }

    /**
     * Get-метод для поля (@code dictionary).
     * @return поле-коллекция класса
     */
    public Collection<String> getDictionary() {
        return this.dictionary;
    }

    /**
     * Get-метод для поля (@code resultsPath).
     * @return строка-путь до папки, хранящей генерируемые файлы
     */
    public String getResultsPath() {
        return this.resultsPath;
    }

    /**
     * Set-метод для поля (@code resultsPath).
     * @param resultsPath путь до папки с генерируемыми файлами
     */
    public void setResultsPath(String resultsPath) {
        this.resultsPath = resultsPath;
    }

    /**
     * Get-метод для поля (@code resultFileSize).
     * @return кол-во абзацев в генерируемых файлах
     */
    public int getResultFileSize() {
        return resultFileSize;
    }

    /**
     * Set-метод для поля (@code resultFileSize).
     * @param resultFileSize кол-во абзацев в генерируемых файлах
     */
    public void setResultFileSize(int resultFileSize) {
        this.resultFileSize = resultFileSize;
    }

    /**
     * Get-метод для поля (@code probabilityDivider).
     * @return значение для вычисления вероятности
     */
    public int getProbabilityDivider() {
        return probabilityDivider;
    }

    /**
     * Set-метод для поля (@code probabilityDivider).
     * @param probabilityDivider значение для вычисления вероятности
     */
    public void setProbabilityDivider(int probabilityDivider) {
        this.probabilityDivider = probabilityDivider;
    }

    public int getDictSize() {
        return dictSize;
    }

    public void setDictSize(int dictSize) {
        this.dictSize = dictSize;
    }

    /**
     * Генерирует определенное в параметрах кол-во файлов, содержащих
     * сгенерированный текст. Генерация текста контролируется параметрами
     * метода и осуществляется из слов переданной методу коллекции (@code words).
     * @param path путь до папки, куда следует генерировать файлы
     * @param filesCount количество генерируемых файлов
     * @param filesSize кол-во абзацев в генерируемых файлов
     * @param words словарь
     * @param probability значение для вычисления вероятности вхождения слова в генерируемый текст
     */
    public static final void generateDankTextFiles(String path, int filesCount,
                                              int filesSize, Collection<String> words, int probability) {
        for (int i = 0; i<filesCount; i++) {
            File newFile = new File(path + i + ".txt");
            writeIntoFile(newFile, filesSize, words, probability);
        }
    }

    /**
     * Записывает в файл сгенерированный текст. Генерация текста
     * контролируется параметрами метода и осуществляется из слов
     * переданной методу коллекции (@code words).
     * @param newFile генерируемый файл
     * @param size кол-во абзацев
     * @param words словарь
     * @param probabilityDivider значение для вычисления вероятности вхождения слова в генерируемый текст
     */
    public static void writeIntoFile(File newFile, int size, Collection<String> words, int probabilityDivider) {
        try(FileOutputStream fos = new FileOutputStream(newFile);
                OutputStreamWriter bout = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(bout)) {
            String result = buildText(size, words, probabilityDivider);
            pw.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Генерирует случайный текст. Генерация текста
     * контролируется параметрами метода и осуществляется из слов
     * переданной методу коллекции (@code coll).
     * @param size кол-во абзацев
     * @param coll словарь
     * @param probabilityDivider значение для вычисления вероятности вхождения слова в генерируемый текст
     * @return сгенерированный текст
     */
    public static String buildText(int size, Collection<String> coll, int probabilityDivider) {
        String resultText = "";
        for (int i=1; i<=size; i++) {
            resultText += buildParagraph(coll,probabilityDivider);
        }
        return resultText;
    }

    /**
     * Генерирует случайный абзац текста. Генерация текста
     * контролируется параметрами метода и осуществляется из слов
     * переданной методу коллекции (@code coll).
     * @param coll словарь
     * @param probabilityDivider значение для вычисления вероятности вхождения слова в генерируемый текст
     * @return сгенерированный абзац
     */
    public static String buildParagraph(Collection<String> coll, int probabilityDivider) {
        int phrasesAmount = new Random().nextInt(19) + 1;
        String resultParagraph = "";
        for (int i=0; i<phrasesAmount; i++) {
            resultParagraph += generatePhrase(coll, probabilityDivider);
        }
        return resultParagraph + "\n\r";
    }

    /**
     * Генерирует случайное текстовое предложение. Для генерации используется метод
     * (@code takeWord), возвращающий случайные слова из словаря и склеивающий их
     * пробелами и запятыми. В конце каждого предложения случайным образом выставляется
     * ".", "!" либо "?".
     * @param words словарь
     * @param probabilityDivider значение для вычисления вероятности вхождения слова в генерируемый текст
     * @return сгенерированное предложение
     */
    public static String generatePhrase(Collection<String> words, int probabilityDivider) {
        String[] endings = {"!","?",".",".",".",".","."};
        String[] delimiters = {",", " "," ", " ", " "};
        Random rand = new Random();
        int wordsCount = rand.nextInt(14) + 1;
        String result = "";
        for(int i=0; i<wordsCount; i++) {
            result += takeWord(words, probabilityDivider) + delimiters[rand.nextInt(5)];
        }
        return (result.substring(0, 1).toUpperCase() +
                        result.substring(1)).replaceFirst("[, ]$", "")
                            + endings[rand.nextInt(6)] + " ";
    }

    /**
     * Возвращает случайное слово из словаря с определенной вероятностью.
     * Если вероятность, вычисленная для слова меньше 100%, то метод вызывается рекурсивно.
     * @param coll словарь
     * @param probabilityDivider значение для вычисления вероятности вхождения слова в генерируемый текст
     * @return случайное слово из словаря
     */
    public static String takeWord(Collection<String> coll, int probabilityDivider) {
        Iterator<String > iter = coll.iterator();
        Random random = new Random();
        int index = random.nextInt(coll.size() - 1);
        int counter = 0;
        String result = null;
        while (iter.hasNext()) {
            if (index == counter) {
                result = iter.next();
                break;
            }
            iter.next();
            counter++;
        }
        if ((random.nextInt(100) + 1.0/probabilityDivider * 100) >= 100)
            return result;
        else
            return takeWord(coll, probabilityDivider);

    }

    public static void main(String[] args) {
        DictionaryCreator dictionaryCreator = new DictionaryCreator();
        Collection<String> dictionary = dictionaryCreator.produceDictionaryCollection();
        TextGenerator textGenerator = new TextGenerator(dictionary, 1000);
        textGenerator.show();
        TextGenerator.generateDankTextFiles(textGenerator.getResultsPath(),
                                1, textGenerator.getResultFileSize(),
                                        textGenerator.getDictionary(), textGenerator.getProbabilityDivider());
    }

    public void show() {
        int i = 1;
        for(String elem : this.getDictionary())
            System.out.println(elem + " " + i++);
    }

}
