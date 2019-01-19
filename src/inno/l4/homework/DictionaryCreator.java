package inno.l4.homework;

import java.io.*;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс, который собирает слова из источника (@code sourcePath)
 * в TreeSet-коллекцию. Имеет два конструктора - первый, без аргументов,
 * использует значения в полях класса, второй - имеет два параметра.
 * (@code sourcePath) - путь до источника, (@code dictPath) - путь
 * до файла, в который можно отправить сгенерированный словарь.
 *
 * @author Tsapin Anton
 */
public class DictionaryCreator {

    private String sourcePath = "/home/sa/IdeaProjects/InnoHomeworks/src/inno/l4/homework/MarkTwen.txt";
    private String dictPath = "/home/sa/IdeaProjects/InnoHomeworks/src/inno/l4/homework/dictionary.txt";

    /**
     * Дефолтный конструктор, класс созданный таким конструктором
     * использует значения полей класса.
     */
    public DictionaryCreator() { }

    /**
     * Конструктор принимает значения путей входного и
     * генерируемого файлов.
     * @param src путь до файла с источником
     * @param out путь до генерируемого файла
     */
    public DictionaryCreator(String src, String out) {
        this.sourcePath = src;
        this.dictPath = out;
    }

    /**
     * При помощи регулярного выражения парсит источник, выбирая
     * лексемы без заглавных букв и спецсимволов. Отобранные слова
     * сохраняются в TreeSet-коллекции, попутно сортируясь и убирая
     * повторения.
     * @return словарь TreeSet-коллекция
     */
    public final TreeSet<String> produceDictionaryCollection() {
        TreeSet<String> collector = new TreeSet<>();
        try (FileReader fin = new FileReader(new File(sourcePath));
             BufferedReader in = new BufferedReader(fin)) {
             String line;
             while((line = in.readLine())!=null) {
                 String[] tempArray = line.split("\\s+");
                 Pattern regexp = Pattern.compile("[\\W_([A-Z])]");
                 for (String elem : tempArray) {
                     Matcher matcher = regexp.matcher(elem);
                     if (!matcher.find() && (elem.length()<=15)) {
                         collector.add(elem.replaceAll("\n", ""));
                     }
                 }
             }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return collector;
    }

    public static void main(String[] args) {
        DictionaryCreator dictionaryCreator = new DictionaryCreator();
        TreeSet<String> coll = dictionaryCreator.produceDictionaryCollection();
        int i = 1;
        for(String elem : coll)
            System.out.println(elem + " " + i++);
    }
}