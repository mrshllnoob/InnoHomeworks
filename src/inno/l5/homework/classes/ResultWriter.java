package inno.l5.homework.classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ResultWriter {

    private String path;

    public ResultWriter(String path) {
        setPath(path);
    }

    public void writeListIntoFile(List<StringBuilder> list) {
        try(FileOutputStream fos = new FileOutputStream(getPath());
            OutputStreamWriter bout = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(bout)) {
            for (StringBuilder elem : list)
                pw.write(elem.append("\n").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        if (path == null || !Files.exists(Paths.get(path)))
            this.setPath(System.getProperty("user.dir") + "result.txt");
        else
            this.path = path;
    }
}
