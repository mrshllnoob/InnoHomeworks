package inno.l4.homework;

import java.io.*;
import java.util.Collection;
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
        this.setSourcePath(src);
        this.setDictPath(out);
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
        try (FileReader fin = new FileReader(new File(getSourcePath()));
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

    /**
     * Записывает коллекцию слов в файл.
     *
     * @param coll коллекция строк
     * @param path путь до формируемого файла
     * @param dictSize кол-во слов в формируемом файле
     */
    public static void outputDictionaryIntoFile(Collection<String> coll, String path, int dictSize) {
        try (FileOutputStream fos = new FileOutputStream(new File(path));
                PrintWriter out = new PrintWriter(fos)) {
            int i = 1;
            String result = "";
            for (String elem : coll) {
                if (i==dictSize)
                    break;
                result += elem + " ";
                i += 1;
            }
            out.write(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DictionaryCreator dictionaryCreator = new DictionaryCreator();
        TreeSet<String> coll = dictionaryCreator.produceDictionaryCollection();
        int i = 1;
        for(String elem : coll)
            System.out.println(elem + " " + i++);
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getDictPath() {
        return dictPath;
    }

    public void setDictPath(String dictPath) {
        this.dictPath = dictPath;
    }
}
