package inno.l5.homework.classes;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ResultWriter {

    private String path;

    public ResultWriter(String path) {
        this.setPath(path);
    }

    public ResultWriter() {
        this.path = null;
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
        this.path = path;
    }
}
