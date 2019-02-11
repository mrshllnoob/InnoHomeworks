package inno.l5.homework;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Класс осуществляющий запись данных в текстовый файл.
 *
 * @author Tsapin Anton
 */
public class ResultWriter {

    private String path;

    public ResultWriter(String path) {
        setPath(path);
    }

    /**
     * Записывает переданный список в файл соответствующий полю
     * класса path.
     *
     * @param list список StringBuilder-ов
     */
    public void writeListIntoFile(List<String> list) {
        try(FileOutputStream fos = new FileOutputStream(getPath());
            OutputStreamWriter bout = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(bout)) {
            for (String elem : list)
                pw.write(elem + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    /**
     * Проверяет переданный путь на валидность.
     * Если переданная строка является null или
     * ее значение содержит некорректный путь, то
     * файл размещается в пользовательской директории
     * с именем result.txt
     *
     * @param path
     */
    public void setPath(String path) {
        if (path != null && Files.exists(Paths.get("" + path)))
            this.path = path;
        else
            this.path = System.getProperty("user.dir") + File.separator + "result.txt";
    }
}
